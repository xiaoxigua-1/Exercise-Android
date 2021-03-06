package com.example.exercise

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.exercise.databinding.ABinding

class A : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = ABinding.inflate(inflater, container, false)
        val view = binding.root
        val list = binding.list
        val database by lazy { context?.let { MyDatabase.createDatabase(it) } }
        val adapter = MyAdapter(database)
        val callBack = ItemList(adapter)
        val itemTouch = ItemTouchHelper(callBack)


        database?.dao()?.selectAllTestTable()?.forEach {
            adapter.dataList.add(it)
        }

        list.layoutManager = LinearLayoutManager(context)
        list.adapter = adapter
        itemTouch.attachToRecyclerView(list)
        return view
    }
}


class AdapterHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(data: Test) {
        itemView.findViewById<TextView>(R.id.textView).text = data.username
    }
}

class MyAdapter(private val database: MyDatabase?) : RecyclerView.Adapter<AdapterHolder>() {
    val dataList = mutableListOf<Test>()

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
        database?.dao()?.delete(dataList[pos])
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
            ItemTouchHelper.LEFT
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