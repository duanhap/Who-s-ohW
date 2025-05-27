package com.example.who_s_ohw.ui.feature.profile

import android.annotation.SuppressLint
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.domain.model.Community
import com.example.domain.model.Event
import com.example.domain.model.Feed
import com.example.who_s_ohw.R
import com.example.who_s_ohw.navigation.ChangePasswordScreen
import com.example.who_s_ohw.ui.feature.changePassword.ChangePasswordScreen
import com.example.who_s_ohw.ui.feature.home.HomeCommunityRow
import com.example.who_s_ohw.ui.feature.home.HomeEventRow
import com.example.who_s_ohw.ui.feature.home.HomeFeatureRow
import com.example.who_s_ohw.ui.feature.home.HomeFeedRow
import com.example.who_s_ohw.ui.feature.home.HomeViewModel
import com.example.who_s_ohw.ui.feature.home.ImageCarouselWithAutoScroll
import com.example.who_s_ohw.ui.feature.home.OverlayButtons
import com.example.who_s_ohw.ui.feature.home.SearchBar
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import org.koin.androidx.compose.koinViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProfileScreen(navController: NavController) {
    Scaffold {
        Surface(
            modifier = Modifier
                .fillMaxSize()
        ) {
            ProfileContent(navController)
        }
    }
}

@Composable
fun HeaderAppNormalBack(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .background(Color.Transparent)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_header_bg),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth()
        )
        Row(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(horizontal = 20.dp, vertical = 15.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_camera),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(90.dp)
                    .clickable {
                        // click vào nút camera
                    }
            )
            Spacer(modifier = Modifier.width(15.dp))
            Image(
                painter = painterResource(id = R.drawable.ic_alert_red),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
            )
            Spacer(modifier = Modifier.width(5.dp))
            Image(
                painter = painterResource(id = R.drawable.ic_alert_yellow),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
            )
            Spacer(modifier = Modifier.width(5.dp))
            Image(
                painter = painterResource(id = R.drawable.ic_alert_green),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
            )

        }
        Image(
            painter = painterResource(id = R.drawable.ic_backwsw),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(horizontal = 75.dp, vertical = 20.dp)
                .alpha(0.2f)
        )
        var pressed by remember { mutableStateOf(false) }
        val scale by animateFloatAsState(
            targetValue = if (pressed) 0.9f else 1f,
            label = "scale"
        )

        Image(
            painter = painterResource(id = R.drawable.ic_backwsw),
            contentDescription = "Back button",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .scale(scale)
                .align(Alignment.BottomEnd)
                .padding(horizontal = 75.dp, vertical = 25.dp)
                .pointerInput(Unit) {
                    detectTapGestures(
                        onPress = {
                            pressed = true
                            tryAwaitRelease()
                            pressed = false
                            // 👉 xử lý back ở đây
                            navController.popBackStack()
                        }
                    )
                }
        )


    }

}

@Composable
fun ProfileContent(navController: NavController) {
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setStatusBarColor(
            color = Color(0xFFB79B29),
            darkIcons = true
        )
    }
    //var isOverlayVisible by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize()
                .background(Color(0xFFEEE2BC))
        ) {
            HeaderAppNormalBack (
                navController = navController)
           // Spacer(modifier = Modifier.size(10.dp))
            val scrollState = rememberScrollState()
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
                    .padding(0.dp)
            ) {
                ProfileContentField(navController)
            }


        }
    }

}
@Composable
fun ProfileContentField(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFEEE2BC)) // màu nền vàng nhạt
            .padding(horizontal = 20.dp)
    ) {
        Text(
            text = "Profile",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            color = Color(0xFF5B2C06),
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .size(120.dp)
        ) {
            AsyncImage(
                model = "https://i.postimg.cc/y8DSHsVG/Which-Bad-Harry-Potter-Witch-Are-You.jpg",
                contentDescription = "Profile picture",
                //placeholder = painterResource(R.drawable.placeholder),
                error = painterResource(R.drawable.ic_relations),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
            )


            IconButton(
                onClick = { /* Chỉnh sửa avatar */ },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .size(35.dp)
                    .background(Color(0xFF5B2C06), CircleShape)
                    .border(3.dp, Color(0xFFEEE2BC), CircleShape)
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit",
                    tint = Color.White,
                    modifier = Modifier.size(20.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        ProfileField(label = "Name", value = "Severus Snape")
        ProfileField(label = "Date of birth", value = "09/01/2004")
        ProfileField(label = "Mobile", value = "02136612234")

        Divider(
            color = Color.Gray.copy(alpha = 0.4f),
            thickness = 1.dp,
            modifier = Modifier.padding(vertical = 24.dp)
        )

        Text(
            text = "Account",
            style = MaterialTheme.typography.titleMedium,
            color = Color(0xFF5B2C06),
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontSize = 20.sp

            )

        Spacer(modifier = Modifier.height(12.dp))

        ProfileField(label = "Email", value = "severussnape@gmail.com")
        ProfileField(label = "Password", value = "************")

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = { navController.navigate(ChangePasswordScreen) },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF5B2C06),
                contentColor = Color.White
            ),
            shape = CircleShape,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text(text = "Change password",
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 20.sp)
        }
        Spacer(modifier = Modifier.height(30.dp))
    }
}

@Composable
fun ProfileField(label: String, value: String) {
    Column(modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp)) {
        Text(
            text = label,
            fontSize = 16.sp,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 4.dp),
            style = MaterialTheme.typography.titleMedium


        )
        OutlinedTextField(
            value = value,
            onValueChange = {},
            readOnly = true,
            shape = RoundedCornerShape(50),
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Black.copy(alpha = 0.5f),
                unfocusedBorderColor = Color.Black.copy(alpha = 0.5f),
                disabledBorderColor = Color.Transparent,
                disabledTextColor = Color.Black,
                focusedContainerColor = Color.White,     // nền khi focus
                unfocusedContainerColor = Color.White    // nền khi không focus
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHeader() {
   // HeaderProfile( ,{})
}
@Preview(showBackground = true)
@Composable
fun PreviewProfileContentField() {
    //ProfileContentField()
}