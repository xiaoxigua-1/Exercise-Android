package com.example.myapplication

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.BaseColumns
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog

class MainActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        val db = MySQL(this).writableDatabase
        val dataList = mutableListOf<Data>()
        val adapter = MyAdapter(this, dataList)
        val listView = findViewById<ListView>(R.id.listView)
        val addButton = findViewById<Button>(R.id.button4)
        val clearButton = findViewById<Button>(R.id.button3)

        listView.adapter = adapter

        val cur = db.query("DATA", arrayOf("code", "time", "date", "time_a", BaseColumns._ID), "", null, null, null, null)
        with(cur) {
            while (moveToNext()) {
                val code = getInt(getColumnIndexOrThrow("code"))
                val time = getString(getColumnIndexOrThrow("time"))
                val date = getString(getColumnIndexOrThrow("date"))
                val id = getLong(getColumnIndexOrThrow(BaseColumns._ID))
                val timeA = getInt(getColumnIndexOrThrow("time_a"))

                dataList.add(Data(code, date, time, timeA, id))
                adapter.notifyDataSetChanged()
                Log.v("a", code.toString())
            }
        }
        addButton.setOnClickListener {
            val view = layoutInflater.inflate(R.layout.adapter, null)
            val codeView = view.findViewById<EditText>(R.id.editTextTextPersonName2)
            val dateView = view.findViewById<EditText>(R.id.editTextTextPersonName3)
            val timeView = view.findViewById<EditText>(R.id.editTextTextPersonName4)
            val timeAView = view.findViewById<RadioGroup>(R.id.radioGroup)

            val alert = AlertDialog.Builder(this)
                .setView(view)
                .show()

            alert.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            view.findViewById<Button>(R.id.button9).setOnClickListener {
                val code = codeView.text.toString().toInt()
                val date = dateView.text.toString()
                val time = timeView.text.toString()
                val timeA = timeAView.checkedRadioButtonId

                val values = ContentValues().apply {
                    put("code", code)
                    put("date", date)
                    put("time", time)
                    put("time_a", timeA)
                }
                val id = db.insert("DATA", null, values)
                dataList.add(Data(code, date, time, timeA, id))
                adapter.notifyDataSetChanged()
                alert.dismiss()
            }

            view.findViewById<Button>(R.id.button10).setOnClickListener {
                alert.dismiss()
            }
        }
    }
}

class MySQL(private val context: Context) : SQLiteOpenHelper(context, "abc.db", null, 1, null) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE DATA(${BaseColumns._ID} INT PRIMARY KEY, code INT, date TEXT, time TEXT, time_a INT)")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }
}

data class Data(val code: Int, val date: String, val time: String, val timeA: Int, val id: Long)

class MyAdapter(private val context: Context, private val dataList: List<Data>) : BaseAdapter() {
    override fun getCount(): Int {
        return dataList.size
    }

    override fun getItem(p0: Int): Any {
        return dataList[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val view = LayoutInflater.from(context).inflate(R.layout.list_item, null)
        val timeA = if (dataList[p0].timeA == R.id.radioButton) "AM" else "PM"
        view.findViewById<TextView>(R.id.textView17).text = dataList[p0].date
        view.findViewById<TextView>(R.id.textView18).text = "${dataList[p0].time} $timeA"
        view.findViewById<TextView>(R.id.textView19).text = dataList[p0].code.toString()
        return view
    }
}