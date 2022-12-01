package com.example.diary


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class loginActivity : AppCompatActivity() {

    private  lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = Firebase.auth

        val currentUser = auth.currentUser
        if(currentUser != null){
            startActivity(Intent(this, MainActivity::class.java ))
            finish()
        }
        findViewById<Button>(R.id.loginButton).setOnClickListener {
            val email = findViewById<EditText>(R.id.idEditText).text.toString()
            val password = findViewById<EditText>(R.id.passwordEditText).text.toString()


            auth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this) {task ->
                    if(task.isSuccessful){

                        val user = auth.currentUser
                        Log.d("mytag","로그인 성공 ${user.toString()}")
                        startActivity(Intent(this,MainActivity::class.java))
                        finish()
                    }else{
                        Log.d("mytag", "로그인 실패 (사유 : ${task.exception}")
                        Toast.makeText(baseContext, "로그인 실패",Toast.LENGTH_SHORT).show()
                    }
                }
        }
        findViewById<Button>(R.id.signupButton).setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }
}

