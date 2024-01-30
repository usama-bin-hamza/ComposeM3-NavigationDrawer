package com.usama.m3_navigationdrawer

import androidx.compose.ui.graphics.vector.ImageVector

data class NavigationItem(
    val title: String,
    val route: String,
    val badgeCount: Int? = null,
    val selectedIcon: ImageVector,
    val unSelectedIcon: ImageVector,
)
