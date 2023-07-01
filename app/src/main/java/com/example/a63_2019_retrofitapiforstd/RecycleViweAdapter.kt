package com.example.a63_2019_retrofitapiforstd

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecycleViweAdapter : RecyclerView.Adapter<RecycleViweAdapter.MyViweHolder>(){

    private var dataList: ArrayList<User> = ArrayList()

    private var OnCheckDeleteItem: ((User)->Unit)? = null

    private var OnClickDetailItem: ((User)->Unit)? = null

    private var OnClickUpdateItem: ((User)->Unit)? = null

    fun addItems(items: ArrayList<User>) {
        this.dataList = items
        notifyDataSetChanged()
    }

    fun setOnCheckDeleteItem(callback: (User)->Unit) {
        this.OnCheckDeleteItem = callback
    }

    fun setOnClickDetailItem(callback: (User)->Unit) {
        this.OnClickDetailItem = callback
    }

    fun setOnClickUpdateItem(callback: (User)->Unit) {
        this.OnClickUpdateItem = callback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyViweHolder (
        LayoutInflater.from(parent.context).inflate(R.layout.items_user_list,parent,false)
    )

    override fun onBindViewHolder(holder: MyViweHolder, position: Int) {
        var users = dataList[position]
        holder.bind(users)
        holder.bDetail.setOnClickListener{OnClickDetailItem?.invoke(users)}
        holder.bUpdate.setOnClickListener{OnClickUpdateItem?.invoke(users)}
        holder.cBoxUser.setOnClickListener{OnCheckDeleteItem?.invoke(users)}
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    class MyViweHolder(view: View): RecyclerView.ViewHolder(view) {
        val cBoxName = view.findViewById<TextView>(R.id.checkBoxUser)!!
        val cBoxUser = view.findViewById<CheckBox>(R.id.checkBoxUser)!!
        val bDetail = view.findViewById<Button>(R.id.btDetail)!!
        val bUpdate = view.findViewById<Button>(R.id.btUpdate)!!

        fun bind(data: User) {
            cBoxName.text = data.name
        }
    }
}