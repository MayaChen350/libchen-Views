package io.github.mayachen350.libchenviews

import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

/** An interface acting as a contract to an activity to make it promise to have all
 * the necessary things for any recycler view helper class to be working.
 *
 * @see RecyclerViewHelper**/
open interface HasRecyclerView<T, VH : RecyclerView.ViewHolder> {
    var recyclerView: RecyclerView

    var listAdapter: ListAdapter<T, VH>
}