@file:OptIn(ExperimentalMaterial3Api::class)

package com.usama.m3_navigationdrawer

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.usama.m3_navigationdrawer.ui.theme.M3NavigationDrawerTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
            var selectedItemIndex by rememberSaveable {
                mutableStateOf(0)
            }
            val scope = rememberCoroutineScope()
            val items = remember {
                mutableListOf(
                    NavigationItem(
                        title = "Home",
                        route = "home",
                        selectedIcon = Icons.Default.Home,
                        unSelectedIcon = Icons.Outlined.Home,
                    ),
                    NavigationItem(
                        title = "Settings",
                        route = "settings",
                        selectedIcon = Icons.Default.Settings,
                        unSelectedIcon = Icons.Outlined.Settings,
                    ),
                    NavigationItem(
                        title = "Urgent",
                        route = "urgent",
                        badgeCount = 56,
                        selectedIcon = Icons.Default.Info,
                        unSelectedIcon = Icons.Outlined.Info,
                    ),
                    NavigationItem(
                        title = "All",
                        route = "all",
                        selectedIcon = Icons.Default.AccountCircle,
                        unSelectedIcon = Icons.Outlined.AccountCircle,
                    ),
                )
            }

            M3NavigationDrawerTheme {
                ModalNavigationDrawer(
                    modifier = Modifier.width(300.dp),
                    drawerContent = {
                        ModalDrawerSheet {
                            Spacer(modifier = Modifier.height(16.dp))
                            items.forEachIndexed { index, navigationItem ->
                                NavigationDrawerItem(
                                    label = {
                                        Text(text = navigationItem.title)
                                    },
                                    selected = index == selectedItemIndex,
                                    onClick = {
                                        selectedItemIndex = index
                                        scope.launch {
                                            drawerState.close()
                                        }
                                    },
                                    icon = {
                                        Icon(
                                            imageVector = if (index == selectedItemIndex) {
                                                navigationItem.selectedIcon
                                            } else navigationItem.unSelectedIcon,
                                            contentDescription = navigationItem.title
                                        )
                                    },
                                    badge = {
                                        navigationItem.badgeCount?.let {
                                            Text(text = navigationItem.badgeCount.toString())
                                        }
                                    },
                                    modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                                )
                            }
                        }
                    },
                    drawerState = drawerState,
                ) {
                    Scaffold(
                        topBar = {
                            TopAppBar(
                                title = {
                                    Text(text = "Todo App")
                                },
                                navigationIcon = {
                                    IconButton(onClick = {
                                        scope.launch {
                                            drawerState.open()
                                        }
                                    }) {
                                        Icon(
                                            imageVector = Icons.Default.Menu,
                                            contentDescription = "Menu"
                                        )
                                    }
                                }
                            )
                        }
                    ) {

                    }
                }

            }
        }
    }
}
