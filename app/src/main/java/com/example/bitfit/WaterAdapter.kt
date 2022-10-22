package com.example.bitfit

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class WaterAdapter(
    private val context: Context,
    private val waterList: MutableList<WaterEntity>) : RecyclerView.Adapter<WaterAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.water_detail, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val foodEntity = waterList[position]
        holder.bind(foodEntity)
    }

    override fun getItemCount() : Int{
        return waterList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val waterDate = itemView.findViewById<TextView>(R.id.tvListDate)
        private val waterOunces = itemView.findViewById<TextView>(R.id.tvListOunces)

        fun bind(waterEntry: WaterEntity) {
            waterDate.text = waterEntry.dbDate
            waterOunces.text = waterEntry.dbOunces.toString()

        }
    }
}