package com.example.myapplication

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.doOnTextChanged
import java.util.*

class MainActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        supportActionBar?.hide()
        findViewById<ImageButton>(R.id.imageButton2).setOnClickListener {
            onBackPressed()
        }
        val addButton = findViewById<Button>(R.id.button4)
        val clearButton = findViewById<Button>(R.id.button3)
        val dataList = mutableListOf<Data>()
        val db = MySQL(this).writableDatabase
        val adapter = MyAdapter(this, dataList, db)

        val cr = db.query("DATA", arrayOf("id", "code", "time", "time_a", "date"), null, null, null, null, null)

        with(cr) {
            while (cr.moveToNext()) {
                val id = getString(getColumnIndexOrThrow("id"))
                val code = getString(getColumnIndexOrThrow("code"))
                val date = getString(getColumnIndexOrThrow("date"))
                val time = getString(getColumnIndexOrThrow("time"))
                val timeA = getString(getColumnIndexOrThrow("time_a"))

                dataList.add(Data(id, code, date, time, timeA))
                adapter.notifyDataSetChanged()
            }
        }

        addButton.setOnClickListener {
            val views = layoutInflater.inflate(R.layout.alter, null)
            val alert = AlertDialog.Builder(this)
                .setView(views)
                .show()
            val codeView = views.findViewById<EditText>(R.id.editTextTextPersonName2)
            val dateView = views.findViewById<EditText>(R.id.editTextTextPersonName4)
            val timeView = views.findViewById<EditText>(R.id.editTextTextPersonName3)
            val selectAmOrPm = views.findViewById<RadioGroup>(R.id.radioGroup)
            alert.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            views.findViewById<Button>(R.id.button5).setOnClickListener {
                // add
                val id = UUID.randomUUID().toString()
                val code = codeView.text.toString()
                val date = dateView.text.toString()
                val time = timeView.text.toString()
                val timeA = selectAmOrPm.checkedRadioButtonId.toString()
                if (code.isEmpty() || date.isEmpty() || time.isEmpty()) {
                    Toast.makeText(this, "你很像有東西沒輸入", Toast.LENGTH_LONG).show()
                } else {
                    val values = ContentValues().apply {
                        put("id", id)
                        put("code", code)
                        put("time", time)
                        put("date", date)
                        put("time_a", timeA)
                    }
                    dataList.add(Data(id, code, date, time, timeA))
                    adapter.notifyDataSetChanged()
                    db.insert("DATA", null, values)
                    alert.dismiss()
                }
            }
            views.findViewById<Button>(R.id.button6).setOnClickListener {
                alert.dismiss()
            }
        }

        findViewById<ListView>(R.id.listView).adapter = adapter

        clearButton.setOnClickListener {
            for (id in adapter.clearList) {
                dataList.removeIf { it.id == id }
                db.delete("DATA", "id = ?", arrayOf(id))
                adapter.notifyDataSetChanged()
            }

            adapter.clearList.clear()
        }

        findViewById<EditText>(R.id.editTextTextPersonName).doOnTextChanged { _, _, _, _ ->
            val text = findViewById<EditText>(R.id.editTextTextPersonName).text.toString()
            val searchText = if (text.isEmpty()) null else "date LIKE '%${text}%'"
            dataList.clear()
            adapter.notifyDataSetChanged()
            val cr = db.query("DATA", arrayOf("id", "code", "time", "time_a", "date"), searchText, null, null, null, null)

            with(cr) {
                while (cr.moveToNext()) {
                    val id = getString(getColumnIndexOrThrow("id"))
                    val code = getString(getColumnIndexOrThrow("code"))
                    val date = getString(getColumnIndexOrThrow("date"))
                    val time = getString(getColumnIndexOrThrow("time"))
                    val timeA = getString(getColumnIndexOrThrow("time_a"))

                    dataList.add(Data(id, code, date, time, timeA))
                    adapter.notifyDataSetChanged()
                }
            }
        }
    }
}

data class Data(val id: String, val code: String, val date: String, val time: String, val timeA: String)

class MySQL(private val context: Context) : SQLiteOpenHelper(context, "123.db", null, 1, null) {
    override fun onCreate(p0: SQLiteDatabase) {
        p0.execSQL("CREATE TABLE DATA(code TEXT, date TEXT, time TEXT, time_a TEXT, id TEXT)")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }
}

class MyAdapter(private val context: Context, private val dataList: MutableList<Data>, val db:  SQLiteDatabase) : BaseAdapter() {
    val clearList = mutableListOf<String>()
    override fun getCount(): Int = dataList.size

    override fun getItem(p0: Int): Data = dataList[p0]

    override fun getItemId(p0: Int): Long = p0.toLong()

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val views = LayoutInflater.from(context).inflate(R.layout.adapter_item, null)
        val amOrPm = if (dataList[p0].timeA.toInt() == R.id.radioButton) "AM" else "PM"
        views.findViewById<TextView>(R.id.textView25).text = dataList[p0].date
        views.findViewById<TextView>(R.id.textView26).text = "${dataList[p0].time} $amOrPm"
        views.findViewById<TextView>(R.id.textView27).text = dataList[p0].code
        views.findViewById<CheckBox>(R.id.checkBox).setOnCheckedChangeListener { _, b ->
            if (b) {
                clearList.add(dataList[p0].id)
            } else {
                dataList[p0].id
            }
        }
        views.findViewById<ImageButton>(R.id.imageButton3).setOnClickListener {
            val data = dataList[p0]
            val views = LayoutInflater.from(context).inflate(R.layout.alter, null)
            val alert = AlertDialog.Builder(context)
                .setView(views)
                .show()
            val codeView = views.findViewById<EditText>(R.id.editTextTextPersonName2)
            val dateView = views.findViewById<EditText>(R.id.editTextTextPersonName4)
            val timeView = views.findViewById<EditText>(R.id.editTextTextPersonName3)
            val selectAmOrPm = views.findViewById<RadioGroup>(R.id.radioGroup)
            alert.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            codeView.setText(data.code)
            dateView.setText(data.date)
            timeView.setText(data.time)
            selectAmOrPm.check(data.timeA.toInt())
            views.findViewById<Button>(R.id.button6).setOnClickListener {
                alert.dismiss()
            }

            views.findViewById<Button>(R.id.button5).setOnClickListener {
                val id = dataList[p0].id
                val code = codeView.text.toString()
                val date = dateView.text.toString()
                val time = timeView.text.toString()
                val timeA = selectAmOrPm.checkedRadioButtonId.toString()

                if (code.isEmpty() || date.isEmpty() || time.isEmpty()) {
                    Toast.makeText(context, "你很像有東西沒輸入", Toast.LENGTH_LONG).show()
                } else {
                    val values = ContentValues().apply {
                        put("code", code)
                        put("time", time)
                        put("date", date)
                        put("time_a", timeA)
                    }
                    dataList[p0] = Data(id, code, date, time, timeA)
                    notifyDataSetChanged()
                    db.update("DATA", values, "id = ?", arrayOf(id))
                    alert.dismiss()
                }
            }
        }
        return views
    }
}