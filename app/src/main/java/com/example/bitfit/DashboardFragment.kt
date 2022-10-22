package com.example.bitfit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit


private lateinit var daysText: TextView
private lateinit var averageText: TextView
private lateinit var minText: TextView
private lateinit var maxText: TextView
private lateinit var chart: LineChart

class DashboardFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Change this statement to store the view in a variable instead of a return statement
        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)
        daysText = view.findViewById(R.id.tvStatDays)
        averageText = view.findViewById(R.id.tvStatAverage)
        minText = view.findViewById(R.id.tvStatMinimum)
        maxText = view.findViewById(R.id.tvStatMaximum)
        chart = view.findViewById(R.id.chart1)

        val sdf = SimpleDateFormat("MM/dd/yyyy")

        lifecycleScope.launch {
            val days = (activity?.application as WaterApplication).database.waterDao().getDayCount()
            val average = (activity?.application as WaterApplication).database.waterDao().getOunceTotal()
            val min = (activity?.application as WaterApplication).database.waterDao().getMin()
            val max = (activity?.application as WaterApplication).database.waterDao().getMax()
            val firstDay = sdf.parse((activity?.application as WaterApplication).database.waterDao().getFirstDay())
            val graphData = (activity?.application as WaterApplication).database.waterDao().getAllList()
            var graphList = ArrayList<Entry>()
            for (data in graphData){
                val day = sdf.parse(data.dbDate)
                val diff: Long = day.getTime() - firstDay.getTime()
                graphList.add(Entry(TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS).toFloat(), data.dbOunces.toFloat()))
            }
            val linedataset = LineDataSet(graphList, "First")
            //We add features to our chart
            linedataset.color = resources.getColor(R.color.purple_200)

            linedataset.circleRadius = 10f
            linedataset.setDrawFilled(true)
            linedataset.valueTextSize = 20F
            //linedataset.fillColor = resources.getColor(R.color.green)
            linedataset.setMode(LineDataSet.Mode.CUBIC_BEZIER);

            //We connect our data to the UI Screen
            val data = LineData(linedataset)
            chart.data = data
            chart.setBackgroundColor(resources.getColor(R.color.white))
            chart.animateXY(2000, 2000, Easing.EaseInCubic)

            daysText.text = days.toString()
            averageText.text = (average / days).toString()
            minText.text = min.toString()
            maxText.text = max.toString()
        }
        return view
    }

    companion object {
        fun newInstance(): DashboardFragment{
            return DashboardFragment()
            }
    }
}