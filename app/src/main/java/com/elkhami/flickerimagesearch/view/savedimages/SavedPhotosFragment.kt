package com.elkhami.flickerimagesearch.view.savedimages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.elkhami.flickerimagesearch.databinding.FragmentSavedPhotosBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by A.Elkhami on 06,July,2021
 */
@AndroidEntryPoint
class SavedPhotosFragment : Fragment() {

    private var _binding: FragmentSavedPhotosBinding? = null
    private val binding get() = _binding!!

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

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}