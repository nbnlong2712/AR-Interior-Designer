package com.example.arinteriordesigner.presenter.ui.onboard

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import com.example.arinteriordesigner.R

data class OnBoardPage(
    val title: String,
    val description: String,
    @DrawableRes val image: Int,
    @ColorRes val backgroundColor: Int,
    @ColorRes val buttonColor: Int
) {
    companion object {
        val onBoardPages = listOf(
            OnBoardPage(
                "AI Interior Design",
                "Scan your space with AR to capture the layout of your room",
                R.drawable.pg_1,
                R.color.light_cyan_0,
                R.color.light_cyan_1
            ),
            OnBoardPage(
                "Generate Design Ideas",
                "Let AI analyze the room and suggest furniture arrangements",
                R.drawable.pg_2,
                R.color.azure_0,
                R.color.azure_1
            ),
            OnBoardPage(
                "Browse Furniture",
                "Select furniture from the catalog and visualize it in 3D",
                R.drawable.pg_3,
                R.color.oldlace_0,
                R.color.oldlace_1
            ),
        )
    }
}