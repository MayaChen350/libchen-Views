package io.github.mayachen350.libchenviews

import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

interface HasRecyclerView<T,VH : RecyclerView.ViewHolder> {
    var recyclerView: RecyclerView

    var listAdapter:  ListAdapter<T,VH>
}