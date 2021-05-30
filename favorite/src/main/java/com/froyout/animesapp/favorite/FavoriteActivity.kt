package com.froyout.animesapp.favorite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.froyout.animesapp.core.ui.AnimeHorizontalAdapter
import com.froyout.animesapp.detail.DetailActivity
import com.froyout.animesapp.favorite.databinding.ActivityFavoriteBinding
import com.froyout.animesapp.favorite.di.favoriteModule
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteBinding
    private val favoriteViewModel: FavoriteViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadKoinModules(favoriteModule)

        val animeAdapter = AnimeHorizontalAdapter()
        animeAdapter.setViewType(2)

        animeAdapter.onItemClick = {
            selectedAnime ->
                val intent = Intent(this, Class.forName("com.froyout.animesapp.detail.DetailActivity"))
                intent.putExtra(DetailActivity.EXTRA_DATA, selectedAnime.id)
                startActivity(intent)

        }

        favoriteViewModel.getFavorite().observe(this, {
            animes ->
            animeAdapter.setData(animes)
            if(animes.isNotEmpty()){
                binding.tvEmpty.visibility = View.GONE
            }else{
                binding.tvEmpty.visibility = View.VISIBLE
            }
        })

        with(binding.rvFavorite){
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = animeAdapter
        }
    }
}