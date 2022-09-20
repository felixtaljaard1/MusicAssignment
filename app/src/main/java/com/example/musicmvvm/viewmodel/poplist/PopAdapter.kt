package com.example.musicmvvm.viewmodel.poplist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.musicmvvm.data.entities.classic.ResultClassicItem
import com.example.musicmvvm.data.entities.pop.ResultPopItem
import com.example.musicmvvm.databinding.PopRowBinding

class PopAdapter(private val onClickListener: OnClickListener): ListAdapter<ResultPopItem, PopAdapter.MyViewHolderPop>(PopItemDiffCallback()) {

    class OnClickListener(val clickListener: (pop: ResultPopItem) -> Unit){
        fun onClick(pop: ResultPopItem) = clickListener(pop)
    }

    class MyViewHolderPop(val popRowBinding: PopRowBinding): RecyclerView.ViewHolder(popRowBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolderPop {
        val view = LayoutInflater.from(parent.context)
        val binding = PopRowBinding.inflate(view, parent, false)
        return MyViewHolderPop(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolderPop, position: Int) {
        val popList = getItem(position)
        holder.popRowBinding.tvPopName.text = popList.collectionName
        holder.popRowBinding.tvPopBand.text = popList.artistName
        holder.popRowBinding.tvPopPrice.text = popList.trackPrice.toString()

        Glide.with(holder.itemView.context)
            .load(popList.artworkUrl60)
            .into(holder.popRowBinding.imagePop)

        holder.itemView.setOnClickListener{
            onClickListener.onClick(popList)
        }
    }

}
class PopItemDiffCallback: DiffUtil.ItemCallback<ResultPopItem>(){
    override fun areItemsTheSame(oldItem: ResultPopItem, newItem: ResultPopItem): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: ResultPopItem, newItem: ResultPopItem): Boolean =
        oldItem == newItem

}