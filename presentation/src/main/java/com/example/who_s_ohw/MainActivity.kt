package com.example.who_s_ohw

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.who_s_ohw.navigation.CameraScreen
import com.example.who_s_ohw.navigation.ChangePasswordScreen

import com.example.who_s_ohw.navigation.ChatScreen
import com.example.who_s_ohw.navigation.EventScreen
import com.example.who_s_ohw.navigation.HomeScreen
import com.example.who_s_ohw.navigation.NotificationScreen
import com.example.who_s_ohw.navigation.ProfileScreen
import com.example.who_s_ohw.navigation.RelationsScreen
import com.example.who_s_ohw.navigation.SettingScreen
import com.example.who_s_ohw.navigation.SignInScreen
import com.example.who_s_ohw.navigation.SignUpScreen
import com.example.who_s_ohw.ui.feature.changePassword.ChangePasswordScreen
import com.example.who_s_ohw.ui.feature.home.HomeScreen
import com.example.who_s_ohw.ui.feature.notification.NotificationScreen
import com.example.who_s_ohw.ui.feature.profile.ProfileScreen
import com.example.who_s_ohw.ui.feature.setting.SettingScreen
import com.example.who_s_ohw.ui.feature.auth.SignInScreen
import com.example.who_s_ohw.ui.feature.auth.SignUpScreen
import com.example.who_s_ohw.ui.feature.relations.MyRelationsScreen
import com.example.who_s_ohw.ui.feature.splash.SplashScreen
import com.example.who_s_ohw.ui.theme.WhosohwTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WhosohwTheme {
                val shouldShowBottomNav = remember {
                    mutableStateOf(true)
                }
                val navController = rememberNavController()
                Scaffold(
                    containerColor = Color(0xFFB79B29),
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        AnimatedVisibility(
                            visible = shouldShowBottomNav.value,
                            enter = fadeIn(animationSpec = tween(durationMillis = 500)), // 500ms
                        ) {
                            BottomNavigationBar(navController)
                        }

                    }
                ) {
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(it)
                    ) {
                        NavHost(navController = navController, startDestination = com.example.who_s_ohw.navigation.SplashScreen) {
                            composable<com.example.who_s_ohw.navigation.SplashScreen> {
                                SplashScreen(navController)
                                shouldShowBottomNav.value = false
                            }
                            composable<SignInScreen>  {
                                SignInScreen(navController)
                                shouldShowBottomNav.value = false
                            }
                            composable<SignUpScreen>  {
                                SignUpScreen(navController)
                                shouldShowBottomNav.value = false
                            }

                            composable<HomeScreen> {
                                HomeScreen(navController)
                                shouldShowBottomNav.value = true
                            }
                            composable<RelationsScreen> {
                                MyRelationsScreen(navController)
                                shouldShowBottomNav.value = true

                            }
                            composable<CameraScreen> {
                                shouldShowBottomNav.value = true
                                Box(modifier = Modifier.fillMaxSize()) {
                                    Text(text = "Camera")
                                }
                            }
                            composable<EventScreen> {
                                shouldShowBottomNav.value = true
                                Box(modifier = Modifier.fillMaxSize()) {
                                    Text(text = "Event")
                                }
                            }
                            composable<ProfileScreen> {
                                shouldShowBottomNav.value = false
                                ProfileScreen(navController)

                            }
                            composable<ChangePasswordScreen> {
                                shouldShowBottomNav.value = false
                                ChangePasswordScreen(navController)

                            }
                            composable<NotificationScreen> {
                                shouldShowBottomNav.value = false
                                NotificationScreen(navController)

                            }
                            composable<SettingScreen> {
                                shouldShowBottomNav.value = false
                                SettingScreen(navController)

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
        Box {
            Image(
                painter = painterResource(id = R.drawable.ic_bottomnav),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.FillBounds
            )
            NavigationBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter),
                containerColor = Color.Transparent
            ) {
                val currentRoute =
                    navController.currentBackStackEntryAsState().value?.destination?.route
                val items = listOf(
                    BottomNavItems.Home,
                    BottomNavItems.Relations,
                    BottomNavItems.Camera,
                    BottomNavItems.Event,
                    BottomNavItems.Chat
                )

                items.forEachIndexed { index, item ->
                    if (item is BottomNavItems.Camera) {
                        Spacer(modifier = Modifier.weight(1f)) // giữ chỗ
                    } else {
                        NavigationBarItem(
                            selected = currentRoute?.substringBefore("?") == item.route::class.qualifiedName,
                            onClick = {
                                navController.navigate(item.route) {
                                    navController.graph.startDestinationRoute?.let { startRoute ->
                                        popUpTo(startRoute) { saveState = true }
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
                                    colorFilter = ColorFilter.tint(
                                        if (currentRoute?.substringBefore("?") == item.route::class.qualifiedName)
                                            Color.White else Color.Gray
                                    )
                                )
                            },
                            colors = NavigationBarItemDefaults.colors().copy(
                                selectedIndicatorColor = Color.Transparent,
                                selectedIconColor = Color.White,
                                selectedTextColor = Color.White,
                                unselectedTextColor = Color.Gray,
                                unselectedIconColor = Color.Gray
                            )
                        )
                    }
                }
            }

            Box(
                modifier = Modifier
                    .size(68.dp)
                    .align(Alignment.TopCenter)
                    .offset(y = (-15).dp)
                    .clip(CircleShape)
                    .background(Color.Transparent)
                    .clickable {
                        navController.navigate(BottomNavItems.Camera.route) {
                            navController.graph.startDestinationRoute?.let { startRoute ->
                                popUpTo(startRoute) { saveState = true }
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_camera),
                    contentDescription = "Camera",
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
        }
    }

    sealed class BottomNavItems(val route: Any, val title: String, val icon: Int) {
        object Home : BottomNavItems(HomeScreen, "Home", icon = R.drawable.ic_home)
        object Relations :
            BottomNavItems(RelationsScreen, "Relations", icon = R.drawable.ic_relations)
        object Camera : BottomNavItems(CameraScreen, "Camera", icon = R.drawable.ic_camera)
        object Event : BottomNavItems(EventScreen, "Event", icon = R.drawable.ic_event)
        object Chat : BottomNavItems(ChatScreen, "Chat", icon = R.drawable.ic_chat)
    }

}
