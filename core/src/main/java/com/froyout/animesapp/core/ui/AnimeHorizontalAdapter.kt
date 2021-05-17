package com.froyout.animesapp.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.froyout.animesapp.core.R
import com.froyout.animesapp.core.databinding.ItemAnimeHorizontalBinding
import com.froyout.animesapp.core.domain.models.Anime

class AnimeHorizontalAdapter: RecyclerView.Adapter<AnimeHorizontalAdapter.AnimeViewHolder>() {
    var onItemClick: ((Anime) -> Unit)? = null
    private var listData = ArrayList<Anime>()
    private var view = 1

    fun setData(newListData: List<Anime>?){
        if(newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    fun setViewType(view: Int){
        this.view = view
    }

    inner class AnimeViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val binding = ItemAnimeHorizontalBinding.bind(itemView)
        fun bind(data: Anime){
            with(binding){
                Glide.with(itemView.context)
                    .load(data.imageUrl)
                    .placeholder(R.drawable.loading)
                    .into(imgAnime)
                tvTitle.text = data.title
                tvType.text = data.type
                rating.rating = (data.score / 2).toFloat()
            }
        }
        init {
            binding.root.setOnClickListener{
                onItemClick?.invoke(listData[adapterPosition])
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeViewHolder {
        if(this.view == 1) {
            return AnimeViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_anime_horizontal, parent, false)
            )
        } else{
            return AnimeViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_anime_vertical, parent, false))
        }
    }

    override fun onBindViewHolder(holder: AnimeViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    override fun getItemViewType(position: Int): Int {
        if(this.view == 1){
            return 1
        }else{
            return 2
        }
    }

    override fun getItemCount(): Int {
        return listData.size
    }
}