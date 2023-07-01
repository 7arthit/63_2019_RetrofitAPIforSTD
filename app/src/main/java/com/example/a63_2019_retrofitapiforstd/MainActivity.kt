package com.example.a63_2019_retrofitapiforstd

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    lateinit var viweModel: MainActivityViweModel
    lateinit var viweModelDelete: CreatUserViewModel
    private lateinit var recyclerView: RecyclerView

    private lateinit var searchUsers: EditText
    private lateinit var bSearch: Button
    private lateinit var bCreate: Button
    private lateinit var bDelete: Button

    private var adapter: RecycleViweAdapter? = null
    private var user: User? = null
    private var users: ArrayList<User> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        initRecycleView()
        initViewModel()
        initViweModelDelete()

        bSearch.setOnClickListener {
            if(!TextUtils.isEmpty(searchUsers.text.toString())) {
                viweModel.searchUser(searchUsers.text.toString())
            }else {
                viweModel.getUserList()
            }
        }

        bCreate.setOnClickListener {
            startActivity(Intent(this@MainActivity,CreateUserActivity::class.java))
        }

        adapter?.setOnCheckDeleteItem {
            if(it in users) {
                users.remove(it)
            } else {
                user = User(id = it.id, name = it.name, email = it.email, status = it.status, gender = it.gender)
                users.add(user!!)
            }
        }

        bDelete.setOnClickListener {
            if(users.size > 0) {
                val builder = AlertDialog.Builder(this)
                builder.setMessage("Aer you sure want to delete item?")
                builder.setCancelable(true)
                builder.setPositiveButton("yes") {dialog,_->
                    users.forEach{deleteUser(it.id.toString()) }
                    for(i in 1..20){
                        viweModel.getUserList()
                    }
                    users.clear()
                    Toast.makeText(this@MainActivity,"Success to Delete", Toast.LENGTH_LONG).show()
                    dialog.dismiss()
                }
                builder.setNegativeButton("No"){dialog,_->
                    dialog.dismiss()
                }
                val alert = builder.create()
                alert.show()
            }
        }

        adapter?.setOnClickDetailItem {
            user = it
            val detaildata = Intent(this@MainActivity,DetailUserActivity::class.java)
            detaildata.putExtra("u_id",it.id)
            detaildata.putExtra("u_name",it.name)
            detaildata.putExtra("u_email",it.email)
            detaildata.putExtra("u_status",it.status)
            startActivity(detaildata)
        }

        adapter?.setOnClickUpdateItem {
            user = it
            val updatedata = Intent(this@MainActivity,UpdateUserActivity::class.java)
            updatedata.putExtra("u_id",it.id)
            updatedata.putExtra("u_name",it.name)
            updatedata.putExtra("u_email",it.email)
            updatedata.putExtra("u_status",it.status)
            updatedata.putExtra("u_gender",it.gender)
            startActivity(updatedata)
        }
    }

    fun deleteUser(id: String) {
        viweModelDelete.deleteUser(id)
    }

    fun initViewModel() {
        viweModel = ViewModelProvider(this).get(MainActivityViweModel::class.java)
        viweModel.getUserList()
        viweModel.recyclerListData.observe(this, Observer {
            adapter?.addItems(it)
        })
    }

    fun initViweModelDelete() {
        viweModelDelete = ViewModelProvider(this).get(CreatUserViewModel::class.java)
        viweModelDelete.getDeleteUserObserver().observe(this, Observer <UserResponse?> {
            if(it == null){
                viweModel.getUserList()
            }else{
                Toast.makeText(this@MainActivity,"Failed to Delete", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun initRecycleView() {
        recyclerView.layoutManager= LinearLayoutManager(this)
        adapter = RecycleViweAdapter()
        recyclerView.adapter = adapter
    }

    private fun initView() {
        recyclerView = findViewById(R.id.recycleView)
        searchUsers = findViewById(R.id.ptSearchUser)
        bSearch = findViewById(R.id.btSearch)
        bCreate = findViewById(R.id.btCreate)
        bDelete = findViewById(R.id.btDelete)
    }
}