package io.github.mayachen350.libchenviews

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import ca.cegepmontpetit.cem6222816.tp3_4n6.ConsultationActivity
import ca.cegepmontpetit.cem6222816.tp3_4n6.databinding.TaskItemBinding
import org.kickmyb.transfer.HomeItemResponse
import java.text.SimpleDateFormat
import kotlin.math.roundToInt

class TaskAdapter :
    ListAdapter<HomeItemResponse, TaskAdapter.TaskViewHolder>(TaskItemDiffCallback) {

    inner class TaskViewHolder(private val binding: TaskItemBinding) :
        ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n", "SimpleDateFormat")
        fun bind(task: HomeItemResponse) {
            binding.tvTaskName.text = task.name
            binding.tvProgress.text =
                binding.root.context.getString(R.string.progress_two_dots) + "${task.percentageDone}%"
            binding.tvDeadlineDate.text = SimpleDateFormat("dd/MM/yyyy").format(task.deadline)
            binding.tvUntilDeadlineProgressPourcent.text = "${task.percentageTimeSpent}%"

            binding.progressBarTaskProgression.progress = task.percentageDone
            binding.progressBarUntilDeadline.progress = task.percentageTimeSpent.roundToInt()

            binding.main.setOnClickListener {
                binding.root.context.startActivity(
                    Intent(
                        binding.root.context, ConsultationActivity::class.java
                    ).putExtra("TASK_ID", task.id)
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding: TaskItemBinding =
            TaskItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    public fun getTaskId(position: Int) : Long = getItem(position).id
}

object TaskItemDiffCallback : DiffUtil.ItemCallback<HomeItemResponse>() {
    override fun areItemsTheSame(oldItem: HomeItemResponse, newItem: HomeItemResponse): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: HomeItemResponse, newItem: HomeItemResponse): Boolean {
        return oldItem.id == newItem.id &&
                oldItem.name == newItem.name &&
                oldItem.percentageDone == newItem.percentageDone &&
                oldItem.deadline == newItem.deadline &&
                oldItem.percentageTimeSpent == newItem.percentageTimeSpent
    }
}