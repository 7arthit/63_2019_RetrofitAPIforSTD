package com.example.a63_2019_retrofitapiforstd

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreatUserViewModel: ViewModel() {
    lateinit var createUserLiveData: MutableLiveData<UserResponse?>
    lateinit var deleteUserLiveData: MutableLiveData<UserResponse?>

    init {
        createUserLiveData = MutableLiveData()
        deleteUserLiveData = MutableLiveData()
    }

    fun getCreatNewUserObserver(): MutableLiveData<UserResponse?> {
        return createUserLiveData
    }

    fun getDeleteUserObserver(): MutableLiveData<UserResponse?> {
        return deleteUserLiveData
    }

    fun createUser(user:User) {
        val retroService = RetroInstance.getRetroInstance().create(RetroService::class.java)
        val call = retroService.createUser(user)
        call.enqueue(object: Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                createUserLiveData.postValue(response.body())
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

    fun deleteUser(user_id: String?) {
        val retroService = RetroInstance.getRetroInstance().create(RetroService::class.java)
        val call = retroService.deleteUser(user_id!!)
        call.enqueue(object: Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                deleteUserLiveData.postValue(response.body())
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                deleteUserLiveData.postValue(null)
            }
        })
    }
}