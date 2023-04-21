package org.seona.android.sopt.adapter

import androidx.recyclerview.widget.RecyclerView
import org.seona.android.sopt.databinding.LayoutTitleBinding

class TitleViewHolder(
    private val binding: LayoutTitleBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun onBind(title: String) {
        binding.tvTitle.text = title
    }

}