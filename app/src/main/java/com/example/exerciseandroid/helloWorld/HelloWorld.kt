package com.example.exerciseandroid.helloWorld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.exerciseandroid.R

class HelloWorld : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hello_world)
    }
}