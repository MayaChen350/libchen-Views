package io.github.mayachen350.libchenviews

import androidx.recyclerview.widget.RecyclerView

/**
 * A helper class to be used in an activity inheriting the `HasRecyclerView` interface.
 *
 * I strongly suggest using the parameters promised by `HasRecyclerView` when defining your RecyclerViewHelper class.
 *
 * @param view Put `This`.
 * @see HasRecyclerView
 */
abstract class RecyclerViewHelper<T>(var view: HasRecyclerView<T, RecyclerView.ViewHolder>) {

    /** Setup the recycler view. **/
    abstract fun setupRecycler()

    /** Fill the recycler view with items. **/
    abstract fun fillRecycler()
}