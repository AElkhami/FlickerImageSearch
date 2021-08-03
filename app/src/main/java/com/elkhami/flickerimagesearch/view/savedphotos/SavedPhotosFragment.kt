package com.elkhami.flickerimagesearch.view.savedphotos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.elkhami.flickerimagesearch.R
import com.elkhami.flickerimagesearch.databinding.FragmentSavedPhotosBinding
import com.elkhami.flickerimagesearch.other.Constants
import com.elkhami.flickerimagesearch.other.MarginItemDecoration
import com.elkhami.flickerimagesearch.view.adapter.PhotoAdapter
import com.elkhami.flickerimagesearch.view.adapter.PhotoLoadStateAdapter
import com.elkhami.flickerimagesearch.view.savedphotos.viewmodel.SavedPhotosViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by A.Elkhami on 06,July,2021
 */
@AndroidEntryPoint
class SavedPhotosFragment(
    private val photoAdapter: PhotoAdapter
) : Fragment() {

    private var _binding: FragmentSavedPhotosBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SavedPhotosViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSavedPhotosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerAdapter()
        observeOnSavedPhotos()
    }

    private fun observeOnSavedPhotos() {

        viewModel.getSavedPhotos.observe(viewLifecycleOwner, {
            viewLifecycleOwner.lifecycleScope.launchWhenCreated {
                photoAdapter.submitData(it)
            }
        })

    }

    private fun setUpRecyclerAdapter() {
        binding.savedImagesRecyclerView.apply {
            adapter = photoAdapter.withLoadStateFooter(
                footer = PhotoLoadStateAdapter {
                    photoAdapter.retry()
                }
            )

            addItemDecoration(
                MarginItemDecoration(
                    resources.getDimensionPixelSize(R.dimen.recycler_item_margin)
                )
            )
            layoutManager = GridLayoutManager(requireContext(), Constants.GRID_SPAN_COUNT)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}