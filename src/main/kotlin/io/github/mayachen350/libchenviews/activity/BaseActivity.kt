package io.github.mayachen350.libchenviews.activity

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.google.android.material.snackbar.Snackbar
import io.github.mayachen350.libchenviews.R

/** This is a kind of contract useful when making super classes, interfaces, helper objects etc which relies on the Activity's binding.
 *
 * It basically exposes the binding as well as an additional `isLoading` boolean.
 */
internal abstract class BaseActivity<Binding : ViewBinding> : AppCompatActivity() {
    var isLoading: Boolean = false
    protected lateinit var binding: Binding
}