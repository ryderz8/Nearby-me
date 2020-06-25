package com.app.nearby_me.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.app.nearby_me.BuildConfig
import com.app.nearby_me.R
import com.app.nearby_me.databinding.FragmentPlaceDetailBinding
import com.app.nearby_me.model.pojo.PlaceDTO
import com.app.nearby_me.viewmodel.DetailViewModel
import com.app.nearby_me.viewmodel.SearchedPlaceViewModel
import com.foursquare.android.nativeoauth.FoursquareOAuth


private const val REQUEST_CODE_FSQ_CONNECT = 100

class PlaceDetailsFragment : Fragment() {
    private lateinit var binding: FragmentPlaceDetailBinding
    private val viewModel: DetailViewModel by viewModels()
    private var placeDTO: PlaceDTO? = null
    private lateinit var searchedPlaceViewModel: SearchedPlaceViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_place_detail, container, false)
        searchedPlaceViewModel = ViewModelProviders.of(requireActivity()).get(SearchedPlaceViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        placeDTO = searchedPlaceViewModel.getPlace()
        placeDTO?.run {
            viewModel.placeDTO.value = placeDTO
            viewModel.setWebURL(locationDTO.latitude, locationDTO.longitude)
        }
        binding.ivBack.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.details.ivFav.setOnClickListener {
            val intent = FoursquareOAuth.getConnectIntent(
                context,
                BuildConfig.FOURSQUARE_CLIENT_ID
            )
            startActivityForResult(intent, REQUEST_CODE_FSQ_CONNECT)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            REQUEST_CODE_FSQ_CONNECT -> {
                val tokenResponse =
                    FoursquareOAuth.getTokenFromResult(resultCode, data)

            }
        }
    }

}