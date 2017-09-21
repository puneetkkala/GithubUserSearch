package com.kalapuneet.githubusersearch

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.AppCompatEditText
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*;
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    fun AppCompatEditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
        this.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(editable: Editable?) {
                afterTextChanged.invoke(editable.toString())
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        search_name_et.afterTextChanged {
            var users: Call<RestApiCall.SearchResponse> = RestApiCall.GithubService.createGithubService().getUsers(it)
            users.enqueue(object: Callback<RestApiCall.SearchResponse> {
                override fun onFailure(call: Call<RestApiCall.SearchResponse>?, t: Throwable?) {
                    t?.printStackTrace()
                }

                override fun onResponse(call: Call<RestApiCall.SearchResponse>?, response: Response<RestApiCall.SearchResponse>?) {
                    val item = response?.body()
                    val userList = item?.users
                }

            })
        }
    }
}
