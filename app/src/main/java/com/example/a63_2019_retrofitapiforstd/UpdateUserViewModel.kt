package com.example.a63_2019_retrofitapiforstd

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpdateUserViewModel : ViewModel(){
    var recyclerListData = MutableLiveData<ArrayList<User>>()
    lateinit var updateUserLiveData: MutableLiveData<UserResponse?>

    init {
        updateUserLiveData = MutableLiveData()
    }

    fun getUpdateUserObserver(): MutableLiveData<UserResponse?> {
        return updateUserLiveData
    }

    fun updateUser(user_id: String,user: User) {
        val retroService = RetroInstance.getRetroInstance().create(RetroService::class.java)
        val call = retroService.updateUser(user_id,user)
        call.enqueue(object: Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                updateUserLiveData.postValue(response.body())
            }
            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                updateUserLiveData.postValue(null)
            }
        })
    }
}