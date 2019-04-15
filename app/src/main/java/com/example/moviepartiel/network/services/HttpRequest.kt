package com.example.moviepartiel.network.services

import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request


/**
 * Created by Valentin Pigeau on 14/04/2019
 * Class with function to do GET request with a callback
 */
class HttpRequest {
    companion object {
        private var c = OkHttpClient()

        fun GET(url: String, callback: Callback): Call {
            val request = Request.Builder()
                .url(url)
                .build()
            val call = c.newCall(request)
            call.enqueue(callback)
            return call
        }

    }
}

