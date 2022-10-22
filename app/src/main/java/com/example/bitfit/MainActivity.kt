package com.example.bitfit

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {
    private val drinks = mutableListOf<WaterEntity>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val hydroRv = findViewById<RecyclerView>(R.id.rvWater)
        val dashboardFragment = DashboardFragment()
        val logFragment = WaterLogFragment()
        val drinkFragment = DrinkWaterFragment()
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)

        // Start up with the log
        replaceFragment(logFragment)

        bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.action_log->replaceFragment(logFragment)
                R.id.action_stats->replaceFragment(dashboardFragment)
                R.id.action_goal->replaceFragment(drinkFragment)
            }
            true
        }


    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.apply { replace(R.id.water_frame_layout, fragment) }
        fragmentTransaction.commit()
    }

    public fun backToLog(){
        // Begin the transaction
        replaceFragment(WaterLogFragment())
    }
}