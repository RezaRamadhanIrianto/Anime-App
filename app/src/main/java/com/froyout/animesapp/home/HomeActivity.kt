package com.froyout.animesapp.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.froyout.animesapp.core.data.Resource
import com.froyout.animesapp.core.ui.AnimeHorizontalAdapter
import com.froyout.animesapp.databinding.ActivityHomeBinding
import com.froyout.animesapp.detail.DetailActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private val homeViewModel: HomeViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val animeAdapter = AnimeHorizontalAdapter()
        animeAdapter.onItemClick = {
            selectedAnime ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_DATA, selectedAnime.id)
            startActivity(intent)

        }


        homeViewModel.animes.observe(this, {
                animes ->
            when(animes){
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    animeAdapter.setData(animes.data)
                }
                is Resource.Loading ->{
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Resource.Error -> {
                    Log.d("HomeActivity", "onCreate: ${animes.message}")
                }
            }
        })
        with(binding.rvTopAnime){
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = animeAdapter
        }
    }
}