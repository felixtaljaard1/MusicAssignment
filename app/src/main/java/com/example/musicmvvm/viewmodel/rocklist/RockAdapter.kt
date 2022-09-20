package com.example.musicmvvm.viewmodel.rocklist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.ui.layout.Layout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.musicmvvm.data.entities.rock.Result
import com.example.musicmvvm.databinding.RockRowBinding
import com.example.musicmvvm.viewmodel.classiclist.ClassicAdapter


class RockAdapter(private val onClickListener: OnClickListener): ListAdapter<Result, RockAdapter.MyViewHolderRock>(RockItemDiffCallback()) {

    class OnClickListener(val clickListener: (rock: Result) -> Unit){
        fun onClick(rock: Result) = clickListener(rock)
    }

    class MyViewHolderRock(val rockRowBinding: RockRowBinding): RecyclerView.ViewHolder(rockRowBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolderRock {
        val view = LayoutInflater.from(parent.context)
        val binding = RockRowBinding.inflate(view, parent, false)
        return MyViewHolderRock(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolderRock, position: Int) {
        val rockList = getItem(position)
        holder.rockRowBinding.tvRockName.text = rockList.collectionName
        holder.rockRowBinding.tvRockBand.text = rockList.artistName
        holder.rockRowBinding.tvRockPrice.text = rockList.trackPrice.toString()

        Glide.with(holder.itemView.context)
            .load(rockList.artworkUrl60)
            .into(holder.rockRowBinding.imageRock)

        holder.itemView.setOnClickListener{
            onClickListener.onClick(rockList)
        }
    }

}
class RockItemDiffCallback: DiffUtil.ItemCallback<Result>(){
    override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean =
        oldItem == newItem

}