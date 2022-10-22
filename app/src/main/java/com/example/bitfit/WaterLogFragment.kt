package com.example.bitfit

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import kotlinx.coroutines.launch

class WaterLogFragment : Fragment() {
    private var drinks = mutableListOf<WaterEntity>()
    private lateinit var hydroRv: RecyclerView
    //private lateinit val addWater = findViewById<Button>(R.id.btnMainAdd)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Change this statement to store the view in a variable instead of a return statement
        val view = inflater.inflate(R.layout.fragment_water_log, container, false)
        // Add these configurations for the recyclerView and to configure the adapter
        val layoutManager = LinearLayoutManager(context)
        // TODO button listener? Or replace with menu option?

        hydroRv = view.findViewById(R.id.rvWater)
        hydroRv.layoutManager = layoutManager
        hydroRv.setHasFixedSize(true)
        hydroRv.adapter = WaterAdapter(view.context,drinks)

        lifecycleScope.launch {
            (activity?.application as WaterApplication).database.waterDao().getAll().collect { databaseList ->
                databaseList.map { entity ->
                    WaterEntity(
                        entity.dbDate,
                        entity.dbOunces
                    )
                }.also { mappedList ->
                    drinks.clear()
                    drinks.addAll(mappedList)
                    hydroRv.adapter ?.notifyDataSetChanged()
                }
            }
        }
        // Inflate the layout for this fragment
        return view
    }

    companion object {
        fun newInstance(): WaterLogFragment {
            return WaterLogFragment()
            }
    }

}