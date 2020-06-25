package com.app.nearby_me.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.app.nearby_me.R
import com.app.nearby_me.databinding.FragmentSearchResultBinding
import com.app.nearby_me.viewmodel.SearchedPlaceViewModel
import com.app.nearby_me.viewmodel.SearchResultViewModel
import java.util.*


class PlaceSearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchResultBinding
    private val viewModel: SearchResultViewModel by viewModels()
    private lateinit var searchedPlaceViewModel: SearchedPlaceViewModel
    private var timer: Timer? = null
    private val debounceTime = 600L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.goToNext.observe(this, Observer {
            if (it != null) {
                searchedPlaceViewModel.setPlace(it)
                findNavController().navigate(
                    R.id.action_searchResultFragment_to_placeDetailsFragment
                )
            }
        })
        viewModel.openMap.observe(this, Observer {
            if (it == true) {
                if (viewModel.list.value.isNullOrEmpty().not()) {
                    searchedPlaceViewModel.setList(viewModel.list.value)
                    findNavController().navigate(
                        R.id.action_searchResultFragment_to_searchAllFragment
                    )
                }
            }
        })

    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_search_result, container, false)
        searchedPlaceViewModel = ViewModelProviders.of(requireActivity()).get(SearchedPlaceViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.searchView.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                timer?.cancel()
                timer = Timer()
                timer?.schedule(object : TimerTask() {
                    override fun run() {
                        newText?.run {
                            viewModel.getDataFromServerBySearchKey(newText)
                        }
                    }
                }, debounceTime)

                return true
            }

        })
    }


}