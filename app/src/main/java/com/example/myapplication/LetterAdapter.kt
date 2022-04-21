package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView

class LetterAdapter: RecyclerView.Adapter<LetterAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val button : Button = view.findViewById(R.id.btn_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return ViewHolder(layoutInflater)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item_data = data[position].toString()
        holder.button.text = item_data
        holder.button.setOnClickListener {
            val action = LetterListFragmentDirections.actionLetterListFragmentToWordListFragment(item_data)
            holder.button.findNavController().navigate(action)
        }

    }

    private val data = ('A' .. 'Z').toList()

    override fun getItemCount(): Int = data.size
}