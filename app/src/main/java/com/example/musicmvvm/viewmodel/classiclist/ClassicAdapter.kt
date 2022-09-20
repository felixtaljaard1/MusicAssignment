package com.example.musicmvvm.viewmodel.classiclist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.musicmvvm.data.entities.classic.ResultClassicItem
import com.example.musicmvvm.databinding.ClassicRowBinding



class ClassicAdapter(private val onClickListener: OnClickListener): ListAdapter<ResultClassicItem, ClassicAdapter.MyViewHolderClassic>(ClassicItemDiffCallback()) {

    class OnClickListener(val clickListener: (classic: ResultClassicItem) -> Unit){
        fun onClick(classic: ResultClassicItem) = clickListener(classic)
    }

    class MyViewHolderClassic(val classicRowBinding: ClassicRowBinding): RecyclerView.ViewHolder(classicRowBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolderClassic {
        val view = LayoutInflater.from(parent.context)
        val binding = ClassicRowBinding.inflate(view, parent, false)
        return MyViewHolderClassic(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolderClassic, position: Int) {
        val classicList = getItem(position)
        holder.classicRowBinding.tvClassicName.text = classicList.collectionName
        holder.classicRowBinding.tvClassicBand.text = classicList.artistName
        holder.classicRowBinding.tvClassicPrice.text = classicList.trackPrice.toString()

        Glide.with(holder.itemView.context)
            .load(classicList.artworkUrl60)
            .into(holder.classicRowBinding.imageClassic)

        holder.itemView.setOnClickListener{
            onClickListener.onClick(classicList)
        }
    }

}
class ClassicItemDiffCallback: DiffUtil.ItemCallback<ResultClassicItem>(){
    override fun areItemsTheSame(oldItem: ResultClassicItem, newItem: ResultClassicItem): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: ResultClassicItem, newItem: ResultClassicItem): Boolean =
        oldItem == newItem

}