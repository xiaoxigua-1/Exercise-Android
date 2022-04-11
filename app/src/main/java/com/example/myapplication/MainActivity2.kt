package com.example.myapplication

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import com.example.myapplication.databinding.ActivityMainBinding


class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        findViewById<Button>(R.id.button2).setOnClickListener {
            val alert = AlertDialog.Builder(this)
                .setView(R.layout.alert)
                .show()
            alert.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
    }
}