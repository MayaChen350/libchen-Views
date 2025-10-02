package io.github.mayachen350.libchenviews.activity

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.google.android.material.snackbar.Snackbar
import io.github.mayachen350.libchenviews.R

abstract class BaseActivity<Binding : ViewBinding> : AppCompatActivity(), HasLoadingSnackbar {
    var isLoading: Boolean = false
    protected lateinit var binding: Binding

    override fun getLoadingSnackbar(contextView: View): Snackbar = Snackbar.make(
        contextView,
        getString(R.string.loading),
        Snackbar.LENGTH_INDEFINITE
    )
}