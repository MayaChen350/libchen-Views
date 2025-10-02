package io.github.mayachen350.libchenviews.utils

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

open class DefaultCallback<T> : Callback<T> {
    final override fun onResponse(call: Call<T>, response: Response<T>) {
        if (response.isSuccessful) {
            println(response.body())
            onSuccessfulResponse(call, response)
        } else
            println(response.code())
            onSuccessfulResponse(call, response)
    }

    override fun onFailure(call: Call<T>, t: Throwable) {
        t.printStackTrace()
    }

    open fun onSuccessfulResponse(call: Call<T>, response: Response<T>) {}

    open fun onFailedResponse(call: Call<T>, response: Response<T>) {}
}