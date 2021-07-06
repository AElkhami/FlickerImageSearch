package com.elkhami.flickerimagesearch.view.savedimages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.elkhami.flickerimagesearch.databinding.FragmentSavedImagesBinding

/**
 * Created by A.Elkhami on 06,July,2021
 */
class SavedImagesFragment: Fragment() {

    private lateinit var binding: FragmentSavedImagesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSavedImagesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}