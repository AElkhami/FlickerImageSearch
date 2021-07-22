package com.elkhami.flickerimagesearch.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.elkhami.flickerimagesearch.R
import com.elkhami.flickerimagesearch.databinding.ActivityMainBinding
import com.elkhami.flickerimagesearch.view.fragmentfactory.FlickerPhotoFragmentFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
     val binding get() = _binding!!

    @Inject
    lateinit var flickerPhotoFragmentFactory: FlickerPhotoFragmentFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportFragmentManager.fragmentFactory = flickerPhotoFragmentFactory

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpNavigation()


    }

    private fun setUpNavigation() {
        (supportFragmentManager
            .findFragmentById(R.id.fragmentContainer) as NavHostFragment)
            .also {
                binding.bottomNavigationView.setupWithNavController(it.navController)

                it.navController.addOnDestinationChangedListener{ _, destination, _ ->
                    when(destination.id){
                        R.id.imageSearchFragment -> hideBackButtonShowNav()
                        R.id.displayImageFragment -> showBackButtonHideNav()
                        R.id.savedImagesFragment -> hideBackButtonShowNav()
                    }
                }
            }
    }

    private fun showBackButtonHideNav(){
        binding.include.backButton.visibility = View.VISIBLE
        binding.bottomNavigationView.visibility = View.GONE
    }

    private fun hideBackButtonShowNav(){
        binding.include.backButton.visibility = View.GONE
        binding.bottomNavigationView.visibility = View.VISIBLE
    }
}