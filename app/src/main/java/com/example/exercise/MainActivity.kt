package com.example.exercise

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        val list = findViewById<RecyclerView>(R.id.list)
        val adapter = MyAdapter()
        val callBack = ItemList(adapter)
        val itemTouch = ItemTouchHelper(callBack)
        adapter.dataList.add("1")
        adapter.dataList.add("2")
        adapter.dataList.add("3")

        list.layoutManager = LinearLayoutManager(this)
        list.adapter = adapter
        itemTouch.attachToRecyclerView(list)
    }
}

class AdapterHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(data: String) {
        itemView.findViewById<TextView>(R.id.textView).text = data
    }
}

class MyAdapter : RecyclerView.Adapter<AdapterHolder>() {
    val dataList = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterHolder {
        val inflater = LayoutInflater.from(parent.context)
        val layout = inflater.inflate(R.layout.layout, parent, false)

        return AdapterHolder(layout)
    }

    override fun onBindViewHolder(holder: AdapterHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int = dataList.size

    fun removeItem(pos: Int) {
        dataList.removeAt(pos)
        notifyItemRemoved(pos)
    }

    fun moveItem(fromPos: Int, toPos: Int) {
        val data = dataList[fromPos]
        dataList.removeAt(fromPos)
        dataList.add(toPos, data)
        notifyItemMoved(fromPos, toPos)
    }
}

class ItemList(private val adapter: MyAdapter) : ItemTouchHelper.Callback() {
    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        return makeMovementFlags(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        )
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        adapter.moveItem(viewHolder.adapterPosition, target.adapterPosition)
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        adapter.removeItem(viewHolder.adapterPosition)
    }
}