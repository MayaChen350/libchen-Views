package io.github.mayachen350.libchenviews

import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ca.cegepmontpetit.cem6222816.tp3_4n6.backend.RetrofitUtil
import io.github.mayachen350.libchenviews.utils.DefaultCallback
import retrofit2.Call
import retrofit2.Response

abstract class RecyclerViewHelper<T>(var view: HasRecyclerView<T, TaskAdapter.TaskViewHolder>) {

    open fun setupRecycler() {
        view.recyclerView.apply {
            adapter = view.listAdapter
            setHasFixedSize(true)
            addItemDecoration(
                DividerItemDecoration(
                    view.recyclerView.context, DividerItemDecoration.VERTICAL
                )
            )

            val itemTouchHelper =
                ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
                    ItemTouchHelper.RIGHT,
                    ItemTouchHelper.LEFT
                ) {
                    override fun onMove(
                        recyclerView: RecyclerView,
                        viewHolder: RecyclerView.ViewHolder,
                        target: RecyclerView.ViewHolder
                    ): Boolean = false

                    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                        val position: Int = viewHolder.adapterPosition
                        val taskId = (view.listAdapter as TaskAdapter).getTaskId(position)
                        RetrofitUtil.get().deleteTask(taskId).enqueue( object :
                            DefaultCallback<String>() {
                            override fun onSuccessfulResponse(
                                call: Call<String>,
                                response: Response<String>
                            ) {
                                super.onSuccessfulResponse(call, response)
                                (adapter as ListAdapter<T, *>).apply {
                                    val newList = currentList.filterIndexed { index, _ ->
                                        index != position }
                                    submitList(newList)
                                    notifyItemRemoved(position)
                                }
                            }
                        })
                    }
                })

            itemTouchHelper.attachToRecyclerView(this)
        }
    }

    abstract fun fillRecycler()
}