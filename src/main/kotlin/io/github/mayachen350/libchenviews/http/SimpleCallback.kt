package io.github.mayachen350.libchenviews.http

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/** A pretty simple and minimal callback for Retrofit. **/
open class SimpleCallback<T> : Callback<T> {
    /** This method groups both the successful and failed outcome.
     * To manage what happens if the response is successful or or if it fails, other methods of the class can be overridden.
     *
     * @see onSuccessfulResponse
     * @see onFailedResponse
     * @
     */
    final override fun onResponse(call: Call<T>, response: Response<T>) {
        if (response.isSuccessful) {
            onSuccessfulResponse(call, response)
        } else {
            onFailedResponse(call, response)
        }
    }

    open override fun onFailure(call: Call<T>, t: Throwable) {
        t.printStackTrace()
    }

    /** Defaults to printing the body of the response.
     *
     * @see onResponse
     */
    open fun onSuccessfulResponse(call: Call<T>, response: Response<T>) {
        println(response.body())
    }

    /** Defaults to printing the status code of the response.
     *
     * @see onResponse
     */
    open fun onFailedResponse(call: Call<T>, response: Response<T>) {
       println(response.code())
    }
}