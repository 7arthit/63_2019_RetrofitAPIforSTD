package com.example.a63_2019_retrofitapiforstd

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViweModel : ViewModel() {

    var recyclerListData = MutableLiveData<ArrayList<User>>()

    fun getUserList() {
        var retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
        val call = retroInstance.getUserList()
        call.enqueue(object: Callback<ArrayList<User>> {
            override fun onResponse(
                call: Call<ArrayList<User>>,
                response: Response<ArrayList<User>>
            ) {
                recyclerListData.value = response.body()
            }
            override fun onFailure(call: Call<ArrayList<User>>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

    fun searchUser(searchText:String) {
        var retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
        val call = retroInstance.searchUsers(searchText)
        call.enqueue(object: Callback<ArrayList<User>> {
            override fun onResponse(
                call: Call<ArrayList<User>>,
                response: Response<ArrayList<User>>
            ) {
                recyclerListData.value = response.body()
            }
            override fun onFailure(call: Call<ArrayList<User>>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
}