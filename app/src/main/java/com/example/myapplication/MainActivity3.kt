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
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doOnTextChanged
import java.util.*

class MainActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        val db = MySQL(this).writableDatabase
        val dataList = mutableListOf<Data>()
        val adapter = MyAdapter(this, dataList, db)
        val listView = findViewById<ListView>(R.id.listView)
        val addButton = findViewById<Button>(R.id.button4)
        val clearButton = findViewById<Button>(R.id.button3)
        val searchView = findViewById<EditText>(R.id.editTextTextPersonName)

        listView.adapter = adapter

        val cur = db.query("DATA", arrayOf("code", "time", "date", "time_a", BaseColumns._ID), "", null, null, null, null)
        with(cur) {
            while (moveToNext()) {
                val code = getInt(getColumnIndexOrThrow("code"))
                val time = getString(getColumnIndexOrThrow("time"))
                val date = getString(getColumnIndexOrThrow("date"))
                val id = getString(getColumnIndexOrThrow(BaseColumns._ID))
                val timeA = getInt(getColumnIndexOrThrow("time_a"))

                dataList.add(Data(code, date, time, timeA, id))
                adapter.notifyDataSetChanged()
                Log.v("a", id.toString())
            }
        }

        clearButton.setOnClickListener {
            for (id in adapter.clearList) {
                db.delete("DATA", "_id = ?", arrayOf(id))
                dataList.removeIf { it.id == id }
            }
            adapter.notifyDataSetChanged()
            adapter.clearList.clear()
        }

        addButton.setOnClickListener {
            val view = layoutInflater.inflate(R.layout.adapter, null)
            val codeView = view.findViewById<EditText>(R.id.editTextTextPersonName4)
            val dateView = view.findViewById<EditText>(R.id.editTextTextPersonName3)
            val timeView = view.findViewById<EditText>(R.id.editTextTextPersonName2)
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
                val id = UUID.randomUUID().toString()

                val values = ContentValues().apply {
                    put("code", code)
                    put("date", date)
                    put("time", time)
                    put("time_a", timeA)
                    put(BaseColumns._ID, id)
                }
                db.insert("DATA", null, values)
                dataList.add(Data(code, date, time, timeA, id))
                adapter.notifyDataSetChanged()
                alert.dismiss()
            }

            view.findViewById<Button>(R.id.button10).setOnClickListener {
                alert.dismiss()
            }
        }

        searchView.doOnTextChanged { text, start, before, count ->
            val cur = if (searchView.text.isNotEmpty()) {
                db.query("DATA", arrayOf("code", "time", "date", "time_a", BaseColumns._ID), "date = ?", arrayOf(searchView.text.toString()), null, null, null)
            } else {
                db.query("DATA", arrayOf("code", "time", "date", "time_a", BaseColumns._ID), null, null, null, null, null)
            }
            dataList.clear()
            adapter.notifyDataSetChanged()
            with(cur) {
                while (moveToNext()) {
                    val code = getInt(getColumnIndexOrThrow("code"))
                    val time = getString(getColumnIndexOrThrow("time"))
                    val date = getString(getColumnIndexOrThrow("date"))
                    val id = getString(getColumnIndexOrThrow(BaseColumns._ID))
                    val timeA = getInt(getColumnIndexOrThrow("time_a"))

                    dataList.add(Data(code, date, time, timeA, id))
                    adapter.notifyDataSetChanged()
                }
            }
        }
    }
}

class MySQL(private val context: Context) : SQLiteOpenHelper(context, "abcde.db", null, 1, null) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE DATA(${BaseColumns._ID} TEXT, code INT, date TEXT, time TEXT, time_a INT)")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }
}

data class Data(val code: Int, val date: String, val time: String, val timeA: Int, val id: String)

class MyAdapter(private val context: Context, private val dataList: MutableList<Data>, val db:  SQLiteDatabase) : BaseAdapter() {
    val clearList = mutableListOf<String>()

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
        view.findViewById<Button>(R.id.button5).setOnClickListener {
            val view =  LayoutInflater.from(context).inflate(R.layout.adapter, null)
            val alert = AlertDialog.Builder(context)
                .setView(view)
                .show()
            val codeView = view.findViewById<EditText>(R.id.editTextTextPersonName4)
            val dateView = view.findViewById<EditText>(R.id.editTextTextPersonName3)
            val timeView = view.findViewById<EditText>(R.id.editTextTextPersonName2)
            val timeAView = view.findViewById<RadioGroup>(R.id.radioGroup)
            view.findViewById<TextView>(R.id.textView8).text = "編輯實聯制"
            timeView.setText(dataList[p0].time)
            dateView.setText(dataList[p0].date)
            codeView.setText(dataList[p0].code.toString())
            timeAView.check(dataList[p0].timeA)
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
                Log.v("id", dataList[p0].id.toString())
                val a = db.update("DATA", values, "${BaseColumns._ID} = ?", arrayOf(dataList[p0].id))
                Log.v("count", a.toString())
                dataList[p0] = Data(code, date, time, timeA, dataList[p0].id)
                notifyDataSetChanged()
                alert.dismiss()
            }

            view.findViewById<Button>(R.id.button10).setOnClickListener {
                alert.dismiss()
            }
        }
        view.findViewById<CheckBox>(R.id.checkBox).setOnCheckedChangeListener { _, b ->
            if (b) clearList.add(dataList[p0].id)
            else clearList.remove(dataList[p0].id)
        }
        return view
    }
}