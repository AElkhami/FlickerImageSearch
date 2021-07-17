package com.elkhami.flickerimagesearch.view.imagesearch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.elkhami.flickerimagesearch.R
import com.elkhami.flickerimagesearch.databinding.FragmentPhotoSearchBinding
import com.elkhami.flickerimagesearch.other.Constants.GRID_SPAN_COUNT
import com.elkhami.flickerimagesearch.other.EditTextExtensions.hideKeyboard
import com.elkhami.flickerimagesearch.other.MarginItemDecoration
import com.elkhami.flickerimagesearch.other.Status
import com.elkhami.flickerimagesearch.view.adapter.PhotoAdapter
import com.elkhami.flickerimagesearch.view.imagesearch.viewmodel.PhotoSearchViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
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
        subscribeToObservers()

/*      --Optional in case the user want it for automatic search.

        var job: Job? = null

        binding.searchEditText.addTextChangedListener { editable ->
            job?.cancel()

                job = lifecycleScope.launch {
                    delay(SEARCH_DELAY_TIME)
                    editable?.let {
                        if (editable.toString().isNotEmpty()) {
                            viewModel.searchFlickerWithKeyword(editable.toString())
                        }
                    }
                }
        }*/

        binding.searchButton.setOnClickListener {
            binding.searchEditText.text?.let { editable ->
                viewModel.searchFlickerWithKeyword(editable.toString())
                binding.searchEditText.hideKeyboard()
                binding.searchButton.isEnabled = false
            }
        }

        photoAdapter.setOnItemClickListener {
            val bundle = Bundle()
            bundle.apply {
                putParcelable(getString(R.string.photo_arg), it)
            }
            findNavController().navigate(R.id.action_imageSearchFragment_to_displayImageFragment)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
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

    private fun subscribeToObservers() {
        viewModel.flickerPhotos.observe(viewLifecycleOwner, {
            it.getContentIfNotHandled()?.let { result ->
                when (result.status) {
                    Status.SUCCESS -> {
                        binding.progressBar.visibility = View.GONE
                        photoAdapter.photosList = result.data?.photos?.photo ?: listOf()
                    }
                    Status.FAILED -> {
                        binding.progressBar.visibility = View.GONE

                        Snackbar.make(
                            binding.root,
                            result.message ?: getString(R.string.somthing_went_wrong),
                            Snackbar.LENGTH_LONG
                        ).show()
                    }
                    Status.LOADING -> binding.progressBar.visibility = View.VISIBLE
                }

                binding.searchButton.isEnabled = true
            }
        })
    }
}