package org.seona.android.sopt.data

import androidx.annotation.DrawableRes

data class Menu(
    @DrawableRes val image: Int,
    val menuName: String,
    val menuPrice: Int

)
