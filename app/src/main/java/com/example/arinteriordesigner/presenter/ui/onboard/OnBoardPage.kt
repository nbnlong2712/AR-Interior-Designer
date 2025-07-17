package com.example.arinteriordesigner.presenter.ui.onboard

import androidx.annotation.DrawableRes
import com.example.arinteriordesigner.R

data class OnBoardPage(
    val title: String,
    val description: String,
    @DrawableRes val image: Int
) {
    companion object {
        val onBoardPages = listOf(
            OnBoardPage(
                "AI Interior Design",
                "Scan your space with AR to capture the layout of your room",
                R.drawable.onboard_1
            ),
            OnBoardPage(
                "Generate Design Ideas",
                "Let AI analyze the room and suggest furniture arrangements",
                R.drawable.onboard_2
            ),
            OnBoardPage(
                "Browse Furniture",
                "Select furniture from the catalog and visualize it in 3D",
                R.drawable.onboard_3
            ),
        )
    }
}