package com.elkhami.flickerimagesearch.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.elkhami.flickerimagesearch.R
import com.elkhami.flickerimagesearch.databinding.ActivityMainBinding
import com.elkhami.flickerimagesearch.view.imagesearch.fragmentfactory.PhotoSearchFragmentFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var photoSearchFragmentFactory: PhotoSearchFragmentFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportFragmentManager.fragmentFactory = photoSearchFragmentFactory

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        (supportFragmentManager
            .findFragmentById(R.id.fragmentContainer) as NavHostFragment)
            .also {
                binding.bottomNavigationView.setupWithNavController(it.navController)
            }
    }
}