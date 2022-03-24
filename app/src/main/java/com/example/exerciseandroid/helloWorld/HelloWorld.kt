package com.example.exerciseandroid.helloWorld

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.exerciseandroid.R

class HelloWorld : AppCompatActivity() {
    data class TimeData(val time: String, val hi: Int)

    class MyAdapter(context: Context, private val items: MutableList<TimeData>) : BaseAdapter() {
        private val inflater: LayoutInflater
                = LayoutInflater.from(context)
        override fun getCount(): Int {
            return items.size
        }

        override fun getItem(p0: Int): TimeData {
            return items[p0]
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }

        @SuppressLint("ViewHolder")
        override fun getView(p0: Int, p1: View?, parent: ViewGroup?): View {
            val rowView = inflater.inflate(R.layout.time_data, parent, false)
            (rowView.findViewById(R.id.textView2) as TextView).text = getItem(p0).time
            (rowView.findViewById(R.id.textView3) as TextView).text = getItem(p0).hi.toString()
            return rowView
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hello_world)
        val mListView = findViewById<ListView>(R.id.userlist)
        val a = mutableListOf(
            TimeData("asd", 123),
            TimeData("asdasd", 123),
            TimeData("asdasxa", 123),
            TimeData("asdasdx", 123),
            TimeData("asdasdq", 123)
        )
        var b = 10
        val adapter = MyAdapter(this, a)
        mListView.adapter = adapter

        mListView.setOnItemClickListener { _, _, i, _ ->
            val dialog = AlertDialog.Builder(this, R.style.MyTheme)

            dialog
                .setTitle(a[i].time)
                .setPositiveButton("abc") { _, _ ->
                    a.add(TimeData("aa", b++))
                    adapter.notifyDataSetChanged()
                }
                .setPositiveButton("OK", null)
                .create()

            dialog.show()
        }
    }
}