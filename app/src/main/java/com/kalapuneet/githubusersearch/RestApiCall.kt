package com.kalapuneet.githubusersearch

import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

class RestApiCall {

    data class User(@SerializedName("login") val login: String)

    data class SearchResponse(@SerializedName("items") val users: Array<User>)

    interface GithubApi {
        @GET("/search/users")
        fun getUsers(@Query("q") query: String): Call<SearchResponse>
    }

    object GithubService {

        fun createGithubService(): GithubApi {
            val builder = Retrofit.Builder()
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://api.github.com")

            return builder.build().create(GithubApi::class.java)
        }
    }
}