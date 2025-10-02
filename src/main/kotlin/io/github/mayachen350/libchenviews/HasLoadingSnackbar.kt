package io.github.mayachen350.libchenviews

import android.view.View
import com.google.android.material.snackbar.Snackbar

interface HasLoadingSnackbar {
    fun getLoadingSnackbar(contextView: View): Snackbar
}