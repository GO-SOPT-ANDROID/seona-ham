package org.seona.android.sopt.adapter.UserAdapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.seona.android.sopt.data.User
import org.seona.android.sopt.databinding.LayoutUserBinding

class UserViewHolder(
    private val binding: LayoutUserBinding
    ):RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: User) {
            Glide.with(binding.root.context).load(data.image).into(binding.ivImageUser)
            binding.tvUserName.text = data.userName
            binding.tvUserEmail.text = data.userEmail
        }
    }