package org.seona.android.sopt.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.seona.android.sopt.data.Menu
import org.seona.android.sopt.databinding.LayoutMenuBinding

class MenuAdapter(context: Context): RecyclerView.Adapter<MenuViewHolder>(){
    private val inflater by lazy {LayoutInflater.from(context) }
    private var menuList: List<Menu> = emptyList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val binding = LayoutMenuBinding.inflate(inflater, parent, false)
        return MenuViewHolder(binding)
    }

    override fun getItemCount()= menuList.size


    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        holder.onBind(menuList[position])
    }

    fun setMenuList(menuList: List<Menu>) {
        this.menuList = menuList
        notifyDataSetChanged()
    }
}