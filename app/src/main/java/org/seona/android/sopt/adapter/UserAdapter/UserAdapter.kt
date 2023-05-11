package org.seona.android.sopt.adapter.UserAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.seona.android.sopt.data.User
import org.seona.android.sopt.databinding.LayoutUserBinding

class UserAdapter(context: Context): RecyclerView.Adapter<UserViewHolder>(){
    private val inflater by lazy {LayoutInflater.from(context) }
    private var userList: List<User> = emptyList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = LayoutUserBinding.inflate(inflater, parent, false)
        return UserViewHolder(binding)
    }

    override fun getItemCount()= userList.size


    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.onBind(userList[position])
    }

    fun setUserList(userList: List<User>) {
        this.userList = userList
        notifyDataSetChanged()
    }
}