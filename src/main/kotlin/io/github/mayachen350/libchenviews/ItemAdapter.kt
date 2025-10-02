package io.github.mayachen350.libchenviews

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.viewbinding.ViewBinding

/** Base class for "easy" Recycler View adapters.
 *
 * Of course, an DiffCallback is still needed.
 *
 * A simple adapter (otherwise) could look like this:
 *
 * ```
 * class SujetAdapter : ListAdapter<Sujet, SujetAdapter.SujetViewHolder>(SujetItemDiffCallback) {
 *
 *     inner class SujetViewHolder(private val binding: SujetItemBinding) :
 *         RecyclerView.ViewHolder(binding.root) {
 *
 *         fun bind(item: Sujet): Unit =
 *             binding.apply { sujetElementText.text = item.contenu }.run {
 *
 *                 // Liens vers les autres activités //
 *
 *                 val goToResults: () -> Unit = {
 *                     binding.root.context.startActivity(
 *                         Intent(
 *                             binding.root.context, ResultatsSujetActivity::class.java
 *                         ).putExtra("sujetId", item.id)
 *                     )
 *                 }
 *
 *                 val goToVoting: () -> Unit = {
 *                     binding.root.context.startActivity(
 *                         Intent(
 *                             binding.root.context, VoteActivity::class.java
 *                         ).putExtra("sujetId", item.id)
 *                     )
 *                 }
 *
 *                 // Click listeners //
 *
 *                 sujetElement.setOnClickListener { goToVoting() }
 *
 *                 sujetElementText.setOnClickListener { goToVoting() }
 *
 *                 btnStatsSujet.setOnClickListener { goToResults() }
 *             }
 *
 *     }
 *
 *     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SujetViewHolder =
 *         SujetViewHolder(
 *             SujetItemBinding.inflate(
 *                 LayoutInflater.from(parent.context),
 *                 parent,
 *                 false
 *             )
 *         )
 *
 *     override fun onBindViewHolder(holder: SujetViewHolder, position: Int) =
 *         holder.bind(getItem(position))
 * }
 *
 * object SujetItemDiffCallback : DiffUtil.ItemCallback<Sujet>() {
 *     override fun areItemsTheSame(oldItem: Sujet, newItem: Sujet): Boolean =
 *         oldItem == newItem
 *
 *     override fun areContentsTheSame(oldItem: Sujet, newItem: Sujet): Boolean =
 *         oldItem == newItem
 * }
 * ```
 */
abstract class ItemAdapter<T>(
    itemDiffCallback: DiffUtil.ItemCallback<T>,
//    val itemBindingClass: ViewBinding
) :
    ListAdapter<T, ItemAdapter<T>.ItemHolder<T>>(itemDiffCallback) {

    /** Inner class essential to the adapter.
     *
     * Basically, a recycler view actually hold elements of that class, they are items of the views in sort.
     */
    abstract inner class ItemHolder<T>(private val binding: ViewBinding) :
        ViewHolder(binding.root) {

        /** In normally called when an item in a recycler view is created. (if you did not override `onBindViewHolder`)
         *
         * In this function should be defined the data of the ViewHolder and other data it could hold.
         */
        abstract fun bind(item: T): Unit
    }

    abstract override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder<T>

//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder<T> {
//        val binding: ViewBinding =
//            itemBindingClass.inflate(LayoutInflater.from(parent.context), parent, false)
//        return ItemHolder(binding)
//    }

    override fun onBindViewHolder(holder: ItemHolder<T>, position: Int) {
        holder.bind(getItem(position))
    }
}