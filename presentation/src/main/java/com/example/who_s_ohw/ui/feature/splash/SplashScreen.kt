package com.example.who_s_ohw.ui.feature.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.domain.model.Event
import com.example.who_s_ohw.R
import com.example.who_s_ohw.navigation.HomeScreen
import com.example.who_s_ohw.navigation.SplashScreen
import com.example.who_s_ohw.ui.feature.home.EventItem
import com.example.who_s_ohw.navigation.SignInScreen

import kotlinx.coroutines.delay


@Composable
fun SplashScreen(navController: NavController) {
    LaunchedEffect(true) {
        delay(3000L) // Chờ 2 giây, có thể thay bằng logic tải dữ liệu
        navController.navigate(SignInScreen) {
            popUpTo("splash") { inclusive = true } // Xoá splash khỏi back stack
        }
    }
    SplashContent(navController)


}
@Composable
fun SplashContent(navController: NavController){
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Ảnh nền
        Image(
            painter = painterResource(id = R.drawable.backgroundsplashscreen),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        // Nội dung hiển thị trên ảnh
        Image(
            painter = painterResource(id = R.drawable.headersplashscreen),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.fillMaxWidth()
                .align(Alignment.TopCenter)
        )
        Image(
            painter = painterResource(id = R.drawable.ic_find_splash_screen),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier.fillMaxWidth()
                .align(Alignment.Center)
                .offset(y = (30).dp)
        )
        Image(
            painter = painterResource(id = R.drawable.ic_title_splash_screen),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier.fillMaxWidth()
                .align(Alignment.Center)
                .offset(y = (100).dp)
        )
        Image(
            painter = painterResource(id = R.drawable.mascotwhosohw),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier.fillMaxWidth()
                .align(Alignment.Center)
                .size(320.dp)
                .offset(y = (-40).dp)
        )

    }

}
@Preview(showBackground = true)
@Composable
fun PreviewSplashScreen() {
    SplashScreen(navController = NavController(LocalContext.current))
}
