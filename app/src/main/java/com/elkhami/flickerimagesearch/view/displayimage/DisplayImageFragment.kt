package com.elkhami.flickerimagesearch.view.displayimage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.elkhami.flickerimagesearch.databinding.FragmentDisplayImageBinding

/**
 * Created by A.Elkhami on 06,July,2021
 */
class DisplayImageFragment: Fragment() {

    private lateinit var binding: FragmentDisplayImageBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDisplayImageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}