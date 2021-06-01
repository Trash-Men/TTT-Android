package com.tjrwns8024.ttt_android.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.tjrwns8024.ttt_android.databinding.ItemRankBinding
import com.tjrwns8024.ttt_android.model.RankModel

class RankAdapter : RecyclerView.Adapter<RankAdapter.RankViewHolder>() {
    private var rankListItems = ArrayList<RankModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RankViewHolder {
        val binding = ItemRankBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return RankViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RankViewHolder, position: Int) {
        holder.bind(rankListItems[position])
    }

    override fun getItemCount(): Int =
        rankListItems.size

    fun setItems(rankList: MutableLiveData<ArrayList<RankModel>>) {
        this.rankListItems = rankList.value!!
        notifyDataSetChanged()
    }

    inner class RankViewHolder(private val binding: ItemRankBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(rankModel: RankModel) {
            binding.rankModel = rankModel
        }
    }
}