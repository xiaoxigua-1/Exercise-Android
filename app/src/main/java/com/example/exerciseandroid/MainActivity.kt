package com.example.exerciseandroid

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.exerciseandroid.ui.login.LoginActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = Intent()
        intent.setClass(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}