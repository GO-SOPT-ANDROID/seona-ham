package org.seona.android.sopt.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.seona.android.sopt.databinding.LayoutTitleBinding

class TitleAdapter(context: Context) : RecyclerView.Adapter<TitleViewHolder>(){
    private val inflater by lazy {LayoutInflater.from(context) }
    private var title: String = ""

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TitleViewHolder {
        val binding = LayoutTitleBinding.inflate(inflater, parent, false)
        return TitleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TitleViewHolder, position: Int) {
        holder.onBind(title)
    }

    override fun getItemCount(): Int {
        return 1
    }

    fun setTitle(title: String) {
        this.title = title
        notifyDataSetChanged()
    }
}