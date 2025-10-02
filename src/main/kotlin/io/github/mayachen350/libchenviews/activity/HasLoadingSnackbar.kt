package io.github.mayachen350.libchenviews.activity

import android.view.View
import com.google.android.material.snackbar.Snackbar

/** Add useful functions when using android material's snackbar **/
interface HasLoadingSnackbar {
    fun getLoadingSnackbar(contextView: View): Snackbar

    fun httpRequestWithLoading(
        contextView: View,
        request: (dismissSnackbarFun: () -> Unit) -> Unit
    ) {
        println("New http request!")
        val snackbar = getLoadingSnackbar(contextView).also { it.show() }
        println("Loading snackbar supposedly made!")
        request { snackbar.dismiss() }
        println("Request ended!")
    }
}