package com.app.nearby_me.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.app.nearby_me.R
import com.app.nearby_me.databinding.RowItemPlacesBinding
import com.app.nearby_me.interfaces.ItemClickListener
import com.app.nearby_me.model.pojo.PlaceDTO


class PlacesAdapter(val listener: ItemClickListener) :
    ListAdapter<PlaceDTO, PlacesAdapter.ViewHolder>(DiffCallback()) {

    private lateinit var binding: RowItemPlacesBinding


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.row_item_places, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(val binding: RowItemPlacesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(dto: PlaceDTO) {
            binding.dto = dto
            binding.listener = listener
            binding.executePendingBindings()
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<PlaceDTO>() {
        override fun areItemsTheSame(oldItem: PlaceDTO, newItem: PlaceDTO): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: PlaceDTO,
            newItem: PlaceDTO
        ): Boolean {
            return oldItem == newItem
        }

    }
}
