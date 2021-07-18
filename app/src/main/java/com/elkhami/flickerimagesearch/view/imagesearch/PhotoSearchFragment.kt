package com.elkhami.flickerimagesearch.view.imagesearch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.elkhami.flickerimagesearch.R
import com.elkhami.flickerimagesearch.databinding.FragmentPhotoSearchBinding
import com.elkhami.flickerimagesearch.other.Constants.GRID_SPAN_COUNT
import com.elkhami.flickerimagesearch.other.EditTextExtensions.hideKeyboard
import com.elkhami.flickerimagesearch.other.MarginItemDecoration
import com.elkhami.flickerimagesearch.view.adapter.PhotoAdapter
import com.elkhami.flickerimagesearch.view.imagesearch.viewmodel.PhotoSearchViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

/**
 * Created by A.Elkhami on 06,July,2021
 */
@AndroidEntryPoint
class PhotoSearchFragment @Inject constructor(
    private val photoAdapter: PhotoAdapter
) : Fragment() {

    private var _binding: FragmentPhotoSearchBinding? = null
    private val binding get() = _binding!!

    private val viewModel: PhotoSearchViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPhotoSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerAdapter()
        setSearchButtonClickListener()
        setPhotoAdapterClickListener()
        addPhotoLoadStateListener()

    }

    private fun addPhotoLoadStateListener() {
        photoAdapter.addLoadStateListener { loadState ->

            if (loadState.refresh is LoadState.Loading) {
                binding.progressBar.visibility = View.VISIBLE
                binding.imagesRecyclerView.visibility = View.GONE
            } else {
                binding.searchButton.isEnabled = true

                binding.progressBar.visibility = View.GONE
                binding.imagesRecyclerView.visibility = View.VISIBLE

                // getting the error
                val errorState = when {
                    loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                    loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                    loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                    else -> null
                }
                //show the error
                errorState?.let {
                    Snackbar.make(
                        binding.root,
                        it.error.message ?: getString(R.string.somthing_went_wrong),
                        Snackbar.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    private fun setPhotoAdapterClickListener() {
        photoAdapter.setOnItemClickListener {
            val bundle = Bundle()
            bundle.apply {
                putParcelable(getString(R.string.photo_arg), it)
            }
            findNavController().navigate(R.id.action_imageSearchFragment_to_displayImageFragment)
        }
    }

    private fun setSearchButtonClickListener() {
        binding.searchButton.setOnClickListener {
            binding.searchEditText.text?.let { editable ->
                if (editable.isNotEmpty()) {
                    viewModel.getPaginatingData(editable.toString())
                    binding.searchEditText.hideKeyboard()
                    binding.searchButton.isEnabled = false
                    setPhotosListToPhotoAdapter()
                }

            }
        }
    }

    private fun setUpRecyclerAdapter() {
        binding.imagesRecyclerView.apply {
            adapter = photoAdapter
            addItemDecoration(
                MarginItemDecoration(
                    resources.getDimensionPixelSize(R.dimen.recycler_item_margin)
                )
            )
            layoutManager = GridLayoutManager(requireContext(), GRID_SPAN_COUNT)
        }
    }

    private fun setPhotosListToPhotoAdapter() {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.photosFlow.collectLatest {
                photoAdapter.submitData(it)
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}