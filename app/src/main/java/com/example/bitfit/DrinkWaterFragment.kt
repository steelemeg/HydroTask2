package com.example.bitfit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

private const val TAG = "DrinkWaterActivity"
private lateinit var dateEditText: EditText
private lateinit var ouncesEditText: EditText

class DrinkWaterFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Change this statement to store the view in a variable instead of a return statement
        val view = inflater.inflate(R.layout.fragment_drink_water, container, false)

        val date = view.findViewById<EditText>(R.id.etDate)
        val sdf = SimpleDateFormat("MM/dd/yyyy")
        val c: Calendar = Calendar.getInstance()
        val todayDate = sdf.format(c.getTime())
        date.setText(todayDate)
        val addDetailsButton =  view.findViewById<Button>(R.id.btnAddingWater)

        dateEditText = view.findViewById(R.id.etDate)
        ouncesEditText  = view.findViewById(R.id.etOunces)

        addDetailsButton.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                (activity?.application as WaterApplication).database.waterDao().insert(
                    WaterEntity(dbDate = dateEditText.text.toString(),
                        dbOunces = ouncesEditText.text.toString().toInt())
                )
            }
            // Begin the transaction
            (activity as MainActivity?)!!.backToLog()
        }


        return view
    }

    companion object {
        fun newInstance(): DrinkWaterFragment {
            return DrinkWaterFragment()

            }
    }
}