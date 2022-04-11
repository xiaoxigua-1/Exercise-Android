package com.example.myapplication

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog


class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        findViewById<Button>(R.id.button2).setOnClickListener {
            val view = layoutInflater.inflate(R.layout.alert, null)
            val alert = AlertDialog.Builder(this)
                .setView(view)
                .show()
            view.findViewById<Button>(R.id.button3).setOnClickListener {
                alert.dismiss()
            }
            alert.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
    }
}