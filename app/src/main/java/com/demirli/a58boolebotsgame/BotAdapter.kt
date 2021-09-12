package com.demirli.a58boolebotsgame

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BotAdapter(var botList: List<Bot>): RecyclerView.Adapter<BotAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.bot_item,parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = botList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.botName.setText(botList[position].botName)
        holder.booleanValue.setText("Value: " + botList[position].booleanValue.toString())
        holder.booleanOperation.setText("Operation: " + botList[position].booleanOperation)
        holder.isAlive.setText("IsAlive: " + botList[position].isAlive.toString())
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val botName = view.findViewById<TextView>(R.id.bot_name_tv)
        val booleanValue = view.findViewById<TextView>(R.id.boolean_value_tv)
        val booleanOperation = view.findViewById<TextView>(R.id.boolean_operation_tv)
        val isAlive = view.findViewById<TextView>(R.id.isAlive_tv)
    }
}