package com.sample.assignment.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sample.assignment.R
import com.sample.assignment.databinding.ItemBinding
import com.sample.assignment.db.data.Row
import com.squareup.picasso.Picasso


/**
Created by Ajay Arya on 28/08/20
 */
/**
 *      Display places list items
 *
 * @property context
 * @property clickListener      to handle click events of list itam
 */
class ListAdapter(private val context: Context, private val clickListener: (Row) -> Unit) :
    RecyclerView.Adapter<ListViewHolder>() {

    private val callback = object : DiffUtil.ItemCallback<Row>() {

        override fun areItemsTheSame(oldItem: Row, newItem: Row): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: Row, newItem: Row): Boolean {
            return oldItem == newItem
        }

    }
    private val differ = AsyncListDiffer(this, callback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding: ItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item,
            parent,
            false
        )
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(differ.currentList[position], clickListener)

        Picasso.get()
            .load(differ.currentList[position].imageHref)
            .placeholder(R.drawable.ic_holder)
            .fit().centerInside()
            .into(holder.binding.tvImage)
    }

    /**
     *      set venue list to Adapter from Activity and notify to update UI(RecylerView)
     *
     * @param tempVenueList     Venue List to display
     */
    fun setVenueListData(tempVenueList: List<Row>) {
        differ.submitList(tempVenueList)
    }
}

class ListViewHolder(val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(venue: Row, clickListener: (Row) -> Unit) {
        /**
         *      bind data to list item
         */
        binding.row = venue

        binding.clParent.setOnClickListener {
            clickListener(venue)
        }
    }


}