package com.example.diary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MypageActivity : AppCompatActivity() {

    private lateinit var auth:FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mypage)

        auth = Firebase.auth

        val currentUser = auth.currentUser
        if(currentUser == null){
            Toast.makeText(this, "사용자 정보가 없습니다. (로그인 필요)",Toast.LENGTH_SHORT).show()
            finish()
        }
        findViewById<TextView>(R.id.user_info).text ="""
            uid : ${currentUser?.uid}
            email: ${currentUser?.email}
            isEmailVerified: ${currentUser?.isEmailVerified}
            displayName: ${currentUser?.displayName}
        """.trimIndent()

        findViewById<Button>(R.id.logout_btn).setOnClickListener {
            auth.signOut()
            Toast.makeText(this, "로그아웃 완료",Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}