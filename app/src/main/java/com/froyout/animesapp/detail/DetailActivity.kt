package com.froyout.animesapp.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.froyout.animesapp.R
import com.froyout.animesapp.core.data.Resource
import com.froyout.animesapp.core.domain.models.Anime
import com.froyout.animesapp.databinding.ActivityDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {
    private val detailViewModel: DetailViewModel by viewModel()
    private lateinit var binding: ActivityDetailBinding

    companion object{
        val EXTRA_DATA = "anime_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val idAnime = intent.getStringExtra(EXTRA_DATA)

        Log.d("TAG", "onCreate: $idAnime")

        if (idAnime != null) {
            detailViewModel.getDetailAnime(idAnime).observe(this, { anime ->
                when(anime){
                    is Resource.Success ->{
                        binding.progressBar.visibility = View.GONE
                        binding.lytParent.visibility = View.VISIBLE
                        populateAnime(anime.data)
                    }
                    is Resource.Loading ->{
                        binding.progressBar.visibility = View.VISIBLE
                        binding.lytParent.visibility = View.GONE
                    }
                    is Resource.Error ->{
                        Log.d("DetailActivity", "onCreate: ${anime.message}")
                    }
                }
            })
        }

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }
        binding.btnFavorite.setOnClickListener {
            binding.btnFavorite.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite_fill))
        }
    }

    fun populateAnime(anime: Anime?){
        if(anime != null){
            Log.d("TAG", "populateAnime: ${anime.score}")
            binding.tvSynopsis.text = anime.synopsis
            binding.tvAired.text = anime.premiered
            binding.tvScore.text = anime.score.toString()
            binding.tvRank.text = "#${anime.rank}"
            binding.tvTitle.text = anime.title
            binding.tvType.text = anime.type

            Glide.with(this)
                .load(anime.imageUrl)
                .placeholder(R.drawable.loading)
                .into(binding.imgDetail)
        }
    }


}