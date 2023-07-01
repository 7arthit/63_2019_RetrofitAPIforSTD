package com.example.a63_2019_retrofitapiforstd

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class UpdateUserActivity : AppCompatActivity() {
    lateinit var viweModelUpdate: UpdateUserViewModel

    private lateinit var updateName: EditText
    private lateinit var updateEmail: EditText
    private lateinit var updateUser: Button

    private var user: User? = null

    private var id: String? = null
    private var name: String? = null
    private var email: String? = null
    private var status: String? = null
    private var gender: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_user)
        initView()
        initViweModelUpdate()
        initViewModel()

        updateUser.setOnClickListener {
            updateUser()
        }
    }

    fun updateUser() {
        val name = updateName.text.toString()
        val email = updateEmail.text.toString()
        if(name == this.name && email == this.email) {
            Toast.makeText(this,"Record not chang", Toast.LENGTH_LONG).show()
            return
        }
        val userEdit = User(id = this.id, name = name, email = email, status = this.status, gender = this.gender)
        viweModelUpdate.updateUser(this.id.toString(),userEdit)
    }

    fun initViweModelUpdate(){
        viweModelUpdate = ViewModelProvider(this).get(UpdateUserViewModel::class.java)
        viweModelUpdate.getUpdateUserObserver().observe(this, Observer <UserResponse?> {
            if(it == null) {
                Toast.makeText(this@UpdateUserActivity,"Filde to update", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this@UpdateUserActivity,"Success to update", Toast.LENGTH_LONG).show()
                startActivity(Intent(this@UpdateUserActivity,MainActivity::class.java))
            }
        })
    }

    private fun initView() {
        updateName = findViewById(R.id.ptUpdateName)
        updateEmail = findViewById(R.id.ptUpateEmail)
        updateUser = findViewById(R.id.btUpdateUser)
    }

    fun initViewModel() {
        this.id = intent.getStringExtra("u_id")
        this.name = intent.getStringExtra("u_name")
        this.email = intent.getStringExtra("u_email")
        this.status = intent.getStringExtra("u_status")
        this.gender = intent.getStringExtra("u_gender")

        updateName.setText(this.name.toString())
        updateEmail.setText(this.email.toString())
    }
}