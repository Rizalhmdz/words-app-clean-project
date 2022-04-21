package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView

class WordAdapter(private val context: Context, letterId: String): RecyclerView.Adapter<WordAdapter.ViewHolder>() {
    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val button : Button = view.findViewById(R.id.btn_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return ViewHolder(layoutInflater)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = filteredWords[position].toString()
        val queryUrl = "${WordListFragment.SEARCH_PREFIX}$data"
        holder.button.text = data
        holder.button.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(queryUrl))
            context.startActivity(intent)
        }
    }

    private val words = context.resources.getStringArray(R.array.words)
    private val filteredWords = words.filter {
        it.startsWith(letterId, ignoreCase = true)
    }.shuffled().take(5).sorted()

    override fun getItemCount(): Int = filteredWords.size
}