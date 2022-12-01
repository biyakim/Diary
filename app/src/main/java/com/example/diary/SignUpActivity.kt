package com.example.diary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class SignUpActivity : AppCompatActivity() {

    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        auth = Firebase.auth
        val signInBtn = findViewById<Button>(R.id.signup_okButton)

        signInBtn.setOnClickListener {
            val email = findViewById<EditText>(R.id.signupID).text.toString()
            val password = findViewById<EditText>(R.id.signupPassword).text.toString()

            signInBtn.isEnabled = false

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) {task ->
                    if (task.isSuccessful){
                        Toast.makeText(baseContext, "회원 가입 성공",Toast.LENGTH_SHORT).show()

                        val user = auth.currentUser
                        Log.d("mytag","회원 가입(=유저 생성) 성공 ${user.toString()}")

                        finish()
                    }else{
                        Log.w("mytag", "회원 가입 실패 (사유 : ${task.exception})",)
                        Toast.makeText(baseContext, "회원 가입 실패",
                            Toast.LENGTH_SHORT).show()
                        signInBtn.isEnabled = true
                    }
                }
        }
    }
}


