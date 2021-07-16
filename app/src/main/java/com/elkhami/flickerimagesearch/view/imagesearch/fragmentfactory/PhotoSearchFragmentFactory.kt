package com.elkhami.flickerimagesearch.view.imagesearch.fragmentfactory

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.elkhami.flickerimagesearch.view.adapter.PhotoAdapter
import com.elkhami.flickerimagesearch.view.imagesearch.PhotoSearchFragment
import javax.inject.Inject

/**
 * Created by A.Elkhami on 16,July,2021
 */
class PhotoSearchFragmentFactory @Inject constructor(
    private val photoAdapter: PhotoAdapter
) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when (className) {
            PhotoSearchFragment::class.java.name -> PhotoSearchFragment(photoAdapter)
            else -> super.instantiate(classLoader, className)
        }
    }
}