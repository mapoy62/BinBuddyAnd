package com.oym.binbuddy.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.oym.binbuddy.R
import com.oym.binbuddy.databinding.ActivityMainBinding
import com.oym.binbuddy.databinding.FragmentRewardsBinding
import com.oym.binbuddy.ui.fragments.ChallengesFragment
import com.oym.binbuddy.ui.fragments.ExploreFragment
import com.oym.binbuddy.ui.fragments.HomeFragment
import com.oym.binbuddy.ui.fragments.RewardsFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        //enableEdgeToEdge()
        setContentView(binding.root)

        val homeFragment = HomeFragment()
        val challengesFragment = ChallengesFragment()
        val exploreFragment = ExploreFragment()
        val rewardsFragment = RewardsFragment()

        supportFragmentManager.beginTransaction()
            .replace(R.id.navHostFragment, homeFragment)
            .commit()

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            val selectedFragment = when (item.itemId) {
                R.id.nav_home -> homeFragment
                R.id.nav_challenges -> challengesFragment
                R.id.nav_explore -> exploreFragment
                R.id.nav_rewards -> rewardsFragment
                else -> null
            }

            selectedFragment?.let {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.navHostFragment, it)
                    .commit()
            }
            true
        }
    }
}