package com.example.bitfit

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

private const val TAG = "DrinkWaterActivity"
private lateinit var dateEditText: EditText
private lateinit var ouncesEditText: EditText
class DrinkWaterActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_water)
        val date = findViewById<EditText>(R.id.etDate)
        val sdf = SimpleDateFormat("MM/dd/yyyy")
        val c: Calendar = Calendar.getInstance()
        val todayDate = sdf.format(c.getTime())
        date.setText(todayDate)
        val addDetailsButton =  findViewById<Button>(R.id.btnAddingWater)

        dateEditText = findViewById(R.id.etDate)
        ouncesEditText  = findViewById(R.id.etOunces)

        addDetailsButton.setOnClickListener {
            lifecycleScope.launch(IO) {
                (application as WaterApplication).database.waterDao().insert(
                    WaterEntity(dbDate = dateEditText.text.toString(),
                        dbOunces = ouncesEditText.text.toString().toInt())
                )
            }

            super.finish()
        }



    }
}