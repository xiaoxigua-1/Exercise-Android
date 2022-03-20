package com.example.exerciseandroid

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.exerciseandroid.ui.login.LoginActivity
import java.sql.SQLClientInfoException


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = Intent()
        intent.setClass(this, LoginActivity::class.java)
        startActivity(intent)
        A(this)
        finish()
    }
}

const val database_name = "a.db"

class A(content: Context) : SQLiteOpenHelper(content, database_name, null, 1) {
    override fun onCreate(p0: SQLiteDatabase) {
        p0.execSQL("CREATE TABLE A (A TEXT, B INT)")
    }

    override fun onUpgrade(p0: SQLiteDatabase, p1: Int, p2: Int) {
        p0.execSQL("DROP TABLE IF EXISTS A")
    }

    fun insert() {
        val value = ContentValues().apply {
            put("", "")
        }
        writableDatabase.insert("A", null, value)
    }
}

