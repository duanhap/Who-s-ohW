package com.example.who_s_ohw

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.who_s_ohw.navigation.ChatScreen
import com.example.who_s_ohw.navigation.EventScreen
import com.example.who_s_ohw.navigation.HomeScreen
import com.example.who_s_ohw.navigation.RelationsScreen
import com.example.who_s_ohw.ui.feature.home.HomeScreen
import com.example.who_s_ohw.ui.theme.WhosohwTheme
import kotlin.reflect.typeOf

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WhosohwTheme {
                val shouldShowBottomNav = remember{
                    mutableStateOf(true)
                }
                val navController = rememberNavController()
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        AnimatedVisibility(visible =shouldShowBottomNav.value, enter = fadeIn() ) {
                            BottomNavigationBar(navController)
                        }

                    }
                ) {
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(it)
                    ) {
                        NavHost(navController = navController, startDestination = HomeScreen) {
                            composable<HomeScreen> {
                                HomeScreen(navController)
                                shouldShowBottomNav.value = true
                            }
                            composable<RelationsScreen> {
                                shouldShowBottomNav.value = true
                                Box(modifier = Modifier.fillMaxSize()) {
                                    Text(text = "Relations")
                                }
                            }
                            composable<EventScreen> {
                                shouldShowBottomNav.value = true
                                Box(modifier = Modifier.fillMaxSize()) {
                                    Text(text = "Event")
                                }
                            }
                            composable<ChatScreen> {
                                shouldShowBottomNav.value = true
                                Box(modifier = Modifier.fillMaxSize()) {
                                    Text(text = "Chat")
                                }
                            }

                        }

                    }

                }

            }
        }
    }
        @Composable
        fun BottomNavigationBar(navController: NavController) {
            NavigationBar {
                //current route
                val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
                val items = listOf(
                    BottomNavItems.Home,
                    BottomNavItems.Relations,
                    BottomNavItems.Event,
                    BottomNavItems.Chat
                )

                items.forEach { item ->
                    val isSelected = currentRoute?.substringBefore("?") == item.route::class.qualifiedName
                    NavigationBarItem(
                        selected = isSelected,
                        onClick = {
                            navController.navigate(item.route) {
                                navController.graph.startDestinationRoute?.let { startRoute ->
                                    popUpTo(startRoute) {
                                        saveState = true
                                    }
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        label = { Text(text = item.title) },
                        icon = {
                            Image(
                                painter = painterResource(id = item.icon),
                                contentDescription = null,
                                colorFilter = ColorFilter.tint(if(isSelected) MaterialTheme.colorScheme.primary else Color.Gray)
                            )
                        }, colors = NavigationBarItemDefaults.colors().copy(
                            selectedIconColor = MaterialTheme.colorScheme.primary,
                            selectedTextColor = MaterialTheme.colorScheme.primary,
                            unselectedTextColor = Color.Gray,
                            unselectedIconColor = Color.Gray
                        )
                    )
                }
            }

        }
    sealed class BottomNavItems(val route: Any, val title: String, val icon: Int) {
        object Home : BottomNavItems(HomeScreen, "Home", icon = R.drawable.ic_home)
        object Relations : BottomNavItems(RelationsScreen, "Relations", icon = R.drawable.ic_relations)
        object Event : BottomNavItems(EventScreen,"Event", icon = R.drawable.ic_event)
        object Chat : BottomNavItems(ChatScreen,"Chat", icon = R.drawable.ic_chat)
    }
}

