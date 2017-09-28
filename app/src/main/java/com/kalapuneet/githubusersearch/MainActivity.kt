package com.kalapuneet.githubusersearch

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var adapter: CustomAdapter
    val handler = Handler()
    lateinit var prev: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        adapter = CustomAdapter()
        adapter.setItems(arrayOf())
        search_result_rv.adapter = adapter
        search_result_rv.layoutManager = LinearLayoutManager(this) as RecyclerView.LayoutManager?
        prev = ""
        makeNetworkCall()
    }

    fun makeNetworkCall() {
        val query = search_name_et.text.toString()
        if (!query.isEmpty() && !query.contentEquals(prev)) {
            val users: Call<RestApiCall.SearchResponse> = RestApiCall.GithubService.createGithubService().getUsers(query)
            users.enqueue(object: Callback<RestApiCall.SearchResponse> {
                override fun onFailure(call: Call<RestApiCall.SearchResponse>?, t: Throwable?) {
                    t?.printStackTrace()
                }

                override fun onResponse(call: Call<RestApiCall.SearchResponse>?, response: Response<RestApiCall.SearchResponse>?) {
                    val item = response?.body()
                    adapter.setItems(item?.users)
                    adapter.notifyDataSetChanged()
                }
            })
        }
        prev = query
        handler.postDelayed({
            makeNetworkCall()
        },1000)
    }
}
