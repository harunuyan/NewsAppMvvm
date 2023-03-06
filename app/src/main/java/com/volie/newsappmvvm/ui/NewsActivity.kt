package com.volie.newsappmvvm.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.volie.newsappmvvm.R
import com.volie.newsappmvvm.databinding.ActivityNewsBinding

class NewsActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityNewsBinding
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        setupBottomNavigationView()

    }

    fun setupBottomNavigationView() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.newsNavHostFragment) as NavHostFragment
        navController = navHostFragment.navController
        mBinding.bottomNavigationView.setupWithNavController(navController)
    }
}