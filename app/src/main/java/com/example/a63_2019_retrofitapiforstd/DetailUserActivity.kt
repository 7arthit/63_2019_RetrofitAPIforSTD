package com.example.a63_2019_retrofitapiforstd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView

class DetailUserActivity : AppCompatActivity() {
    lateinit var detailName: TextView
    lateinit var detailEmail: TextView
    lateinit var detailStatus: TextView

    private var id: String? = null
    private var name: String? = null
    private var email: String? = null
    private var status: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_user)
        initView()
        initViewModel()
    }

    private fun initView() {
        detailName = findViewById(R.id.tvDetailName)
        detailEmail = findViewById(R.id.tvDetailEmail)
        detailStatus = findViewById(R.id.tvDetailStatus)
    }

    fun initViewModel() {
        this.id = intent.getStringExtra("u_id")
        this.name = intent.getStringExtra("u_name")
        this.email = intent.getStringExtra("u_email")
        this.status = intent.getStringExtra("u_status")

        detailName.setText(this.name.toString())
        detailEmail.setText(this.email.toString())
        detailStatus.setText(this.status.toString())
    }
}