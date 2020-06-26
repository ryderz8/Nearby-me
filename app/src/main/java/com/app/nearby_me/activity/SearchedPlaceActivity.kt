package com.app.nearby_me.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.app.nearby_me.R
import com.app.nearby_me.databinding.ActivitySearchedPlaceBinding
import com.app.nearby_me.viewmodel.SearchedPlaceViewModel
import com.google.android.gms.location.FusedLocationProviderClient

class SearchedPlaceActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchedPlaceBinding
    private lateinit var viewModel: SearchedPlaceViewModel
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_searched_place)
        viewModel = ViewModelProviders.of(this).get(SearchedPlaceViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this


    }
}
