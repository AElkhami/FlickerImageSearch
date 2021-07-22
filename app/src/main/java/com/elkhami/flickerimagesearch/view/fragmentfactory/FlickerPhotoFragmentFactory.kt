package com.elkhami.flickerimagesearch.view.fragmentfactory

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.bumptech.glide.RequestManager
import com.elkhami.flickerimagesearch.view.adapter.PhotoAdapter
import com.elkhami.flickerimagesearch.view.displayphoto.DisplayPhotoFragment
import com.elkhami.flickerimagesearch.view.photosearch.PhotoSearchFragment
import javax.inject.Inject

/**
 * Created by A.Elkhami on 16,July,2021
 */
class FlickerPhotoFragmentFactory @Inject constructor(
    private val photoAdapter: PhotoAdapter,
    private val glide: RequestManager
) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when (className) {
            PhotoSearchFragment::class.java.name -> PhotoSearchFragment(photoAdapter)
            DisplayPhotoFragment::class.java.name -> DisplayPhotoFragment(glide)
            else -> super.instantiate(classLoader, className)
        }
    }
}