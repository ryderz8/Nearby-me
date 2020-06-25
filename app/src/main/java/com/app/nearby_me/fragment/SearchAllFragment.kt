package com.app.nearby_me.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.app.nearby_me.R
import com.app.nearby_me.databinding.FragmentSearchAllBinding
import com.app.nearby_me.model.pojo.PlaceDTO
import com.app.nearby_me.viewmodel.SearchedPlaceViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions


class SearchAllFragment : Fragment(), OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener {

    private var mMap: GoogleMap? = null
    private lateinit var binding: FragmentSearchAllBinding
    private lateinit var searchedPlaceViewModel: SearchedPlaceViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search_all, container, false)
        searchedPlaceViewModel = ViewModelProviders.of(requireActivity()).get(SearchedPlaceViewModel::class.java)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment =
            childFragmentManager.findFragmentById(R.id.fullMap) as SupportMapFragment
        mapFragment.getMapAsync(this)
        binding.ivBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        mMap = googleMap
        mMap?.setOnInfoWindowClickListener(this)
        val list = searchedPlaceViewModel.getList()
        val builder = LatLngBounds.Builder()
        val markerOptions = MarkerOptions()
        list.forEach {
            markerOptions.title(it.name)
            markerOptions.position(LatLng(it.locationDTO.latitude, it.locationDTO.longitude))
            builder.include(markerOptions.position)
            val marker = mMap?.addMarker(markerOptions)
            marker?.tag = it
        }
        val bounds = builder.build()
        val cu = CameraUpdateFactory.newLatLngBounds(bounds, 0)
        mMap?.animateCamera(cu)
    }

    override fun onInfoWindowClick(marker: Marker?) {
        marker?.let {
            val placeDTO: PlaceDTO? = marker.tag as PlaceDTO
            placeDTO?.let { searchedPlaceViewModel.setPlace(it) }
            findNavController().navigate(R.id.action_searchAllFragment_to_placeDetailsFragment)

        }
    }


}