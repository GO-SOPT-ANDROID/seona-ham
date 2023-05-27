package org.seona.android.sopt.adapter

import androidx.recyclerview.widget.RecyclerView
import org.seona.android.sopt.data.Menu
import org.seona.android.sopt.databinding.LayoutMenuBinding

class MenuViewHolder(
    private val binding: LayoutMenuBinding
    ):RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: Menu) {
            binding.ivImageMenu.setImageDrawable(binding.root.context.getDrawable(data.image))
            binding.tvMenuName.text = data.menuName
            binding.tvMenuPrice.text = data.menuPrice.toString()
        }
    }