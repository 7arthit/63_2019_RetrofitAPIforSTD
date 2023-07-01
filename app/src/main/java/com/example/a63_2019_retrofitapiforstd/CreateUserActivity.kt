package com.example.a63_2019_retrofitapiforstd

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class CreateUserActivity : AppCompatActivity() {
    lateinit var createName: EditText
    lateinit var createEmail: EditText
    lateinit var createUser: Button
    lateinit var viewModel: CreatUserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_user)
        initView()
        initViewModel()

        createUser.setOnClickListener{
            createUser()
            startActivity(Intent(this@CreateUserActivity,MainActivity::class.java))
        }
    }

    private fun createUser(){
        val user = User("",createName.text.toString(),createEmail.text.toString(),"Active","Male")
        viewModel.createUser(user)
    }

    fun initView(){
        createName = findViewById(R.id.ptCreateName)
        createEmail = findViewById(R.id.ptCreateEmail)
        createUser = findViewById(R.id.btCreateUser)
    }

    fun initViewModel(){
        viewModel = ViewModelProvider(this).get(CreatUserViewModel::class.java)
        viewModel.getCreatNewUserObserver().observe(this, Observer {
            if(it == null){
                Toast.makeText(this@CreateUserActivity,"Faild create user", Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this@CreateUserActivity,"Success create user", Toast.LENGTH_LONG).show()
            }
        })
    }
}