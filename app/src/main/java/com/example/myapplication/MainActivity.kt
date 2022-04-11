package com.example.myapplication

import android.annotation.SuppressLint
import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Bundle
import android.provider.BaseColumns
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val db = MySQLite(this).writableDatabase
        val list = mutableListOf("")
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, list)
        val cursor = db.query("A", arrayOf("a"), "", null, null, null, null)

        with(cursor) {
            while (moveToNext()) {
                val item = getString(getColumnIndexOrThrow("a"))
                list.add(item)
            }
        }

        findViewById<Button>(R.id.button_123).setOnClickListener {
            val text = findViewById<EditText>(R.id.editTextTextPersonName).text.toString()
            Log.d("text", text)
            val values = ContentValues().apply {
                put("a", text)
            }
            db.insert("A", null, values)
            list.add(text)
            adapter.notifyDataSetChanged()
            val intent = Intent(AppWidgetManager.ACTION_APPWIDGET_UPDATE)
            intent.component = ComponentName(this, NewAppWidget::class.java)
            sendBroadcast(intent)
        }

        findViewById<Button>(R.id.button).setOnClickListener {
            startActivity(Intent(this, MainActivity2::class.java))
        }

        val listView = findViewById<ListView>(R.id.list_view_1)
        listView.adapter = adapter
        listView.setOnItemClickListener { _, _, i, _ ->
            db.delete("A", "a = ?", arrayOf(list[i]))
            list.removeAt(i)
            adapter.notifyDataSetChanged()
            val intent = Intent(AppWidgetManager.ACTION_APPWIDGET_UPDATE)
            intent.component = ComponentName(this, NewAppWidget::class.java)
            sendBroadcast(intent)
        }

        findViewById<Button>(R.id.button).setOnClickListener {
            startActivity(Intent(this, MainActivity2::class.java))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.test, menu)
        return super.onCreateOptionsMenu(menu)
    }

    @SuppressLint("SetTextI18n")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.mybutton) {
            val textView = findViewById<TextView>(R.id.text_view)
            textView.text = textView.text.toString() + "A"
        }
        return super.onOptionsItemSelected(item)
    }
}

class MySQLite(context: Context) : SQLiteOpenHelper(context, "abcd.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE A(a TEXT, ${BaseColumns._ID} INTEGER PRIMARY KEY)")
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
//        db.execSQL("DROP")
    }
}