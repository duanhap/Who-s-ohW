package com.example.who_s_ohw.ui.feature.auth

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.who_s_ohw.R
import com.example.who_s_ohw.navigation.HomeScreen
import com.example.who_s_ohw.navigation.SignUpScreen
import com.example.who_s_ohw.ui.feature.changePassword.FieldInput

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SignInScreen (navController: NavController){
    Scaffold {
        Surface(
            modifier = Modifier
                .fillMaxSize()
        ) {
            SignInContent(navController)
        }
    }
}
@Composable
fun HeaderAppNormal(navController: NavController, onImageClick: () -> Unit) {
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
                        onImageClick()
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



    }

}
@Composable
fun SignInContent(navController: NavController) {

    var emailUser by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var isOverlayVisible by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize()
                .background(Color(0xFFEEE2BC))
        ) {
            HeaderAppNormal (
                navController = navController,
                onImageClick = { isOverlayVisible = !isOverlayVisible })
            // Spacer(modifier = Modifier.size(10.dp))
            val scrollState = rememberScrollState()
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
                    .padding(0.dp)
            ) {
                SignInContentField(navController,
                    emailUser = emailUser,
                    onEmailUserChange = { emailUser = it },
                    password = password,
                    onPasswordChange = { password = it },
                )
            }


        }
    }

}
@Composable
fun SignInContentField(
    navController: NavController,
    emailUser: String,
    onEmailUserChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit,
    ) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFEEE2BC)) // màu nền vàng nhạt
            .padding(horizontal = 20.dp)
    ) {
        Text(
            text = "Sign In",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            color = Color(0xFF000000),
            fontWeight = FontWeight.Bold,
            fontSize = 36.sp
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Hi ! Welcome back to Who's Who",
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            color = Color(0xFF000000),
            fontWeight = FontWeight.Normal,
            fontSize = 13.sp
        )


        Spacer(modifier = Modifier.height(24.dp))

        FieldInput(
            label = "Email",
            placeholder = "Enter your email here ",
            text = emailUser,
            onTextChange = onEmailUserChange
        )

        PasswordField(
            label = "Password",
            text = password,
            placeholder = "Enter your password here ",
            onTextChange =  onPasswordChange
        )

        TextButton(
            modifier = Modifier.align(Alignment.End),
            onClick = {
                // TODO: Xử lý khi nhấn vào
            },
            contentPadding = PaddingValues(0.dp) // bỏ padding mặc định nếu muốn sát chữ hơn
        ) {
            Text(
                text = "Forget password ?",
                color = Color(0xFF5C2C06), // Màu nâu
                fontSize = 15.sp,
                textDecoration = TextDecoration.Underline
            )
        }
        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = { navController.navigate(HomeScreen)},
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF5B2C06),
                contentColor = Color.White
            ),
            shape = CircleShape,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text(text = "Sign In",
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 20.sp)
        }
        Spacer(modifier = Modifier.height(30.dp))
        SocialSignInSection(
            onGoogleClick = { /* Handle Google sign-in */ },
            onFacebookClick = { /* Handle Facebook sign-in */ },
            onSignUpClick = { navController.navigate(SignUpScreen)}
        )
    }
}
@Composable
fun PasswordField(
    label: String,
    text: String,
    placeholder: String,
    onTextChange: (String) -> Unit
) {
    var passwordVisible by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp)) {
        Text(
            text = label,
            fontSize = 16.sp,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 4.dp),
            style = MaterialTheme.typography.titleMedium
        )
        OutlinedTextField(
            value = text,
            onValueChange = onTextChange,
            placeholder = {
                Text(text = placeholder, color = Color.Gray, fontSize = 15.sp, modifier = Modifier.alpha(0.5f))
            },
            shape = RoundedCornerShape(50),
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val image = if (passwordVisible)
                    painterResource(id = R.drawable.ic_visibility)
                else
                    painterResource(id = R.drawable.ic_visibility_off)

                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(painter = image, contentDescription = null)
                }
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Black.copy(alpha = 0.5f),
                unfocusedBorderColor = Color.Black.copy(alpha = 0.5f),
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White
            )
        )
    }
}
@Composable
fun SocialSignInSection(onGoogleClick: () -> Unit, onFacebookClick: () -> Unit, onSignUpClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {

        // Dòng "Or sign in with"
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 16.dp)
        ) {
            Divider(modifier = Modifier.weight(1f)
                .padding(start = 50.dp), color = Color.Gray)
            Text(
                text = "   Or sign in with  ",
                color = Color.DarkGray,
                fontSize = 15.sp
            )
            Divider(modifier = Modifier.weight(1f)
                .padding(end = 50.dp), color = Color.Gray)
        }

        // Nút Google và Facebook
        Row(
            modifier = Modifier.padding(top = 15.dp),
            horizontalArrangement = Arrangement.spacedBy(24.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            SocialCircleIcon(imageId = R.drawable.ic_google,size = 24, onClick = onGoogleClick)
            SocialCircleIcon(imageId = R.drawable.ic_facebook, size = 32, onClick = onFacebookClick)
        }

        // Dòng Sign Up
        Row(
            modifier = Modifier.padding(top = 15.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Don’t have an account ?", color = Color.Black, fontSize = 15.sp)
            TextButton(onClick = onSignUpClick) {
                Text(
                    text = "Sign Up",
                    color = Color(0xFF5C2C06), // màu nâu
                    textDecoration = TextDecoration.Underline,
                )
            }
        }
    }
}

@Composable
fun SocialCircleIcon(imageId: Int,
                     size: Int,
                     onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(60.dp)
            .clip(CircleShape)
            .border(1.dp, Color.Gray, CircleShape)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = imageId),
            contentDescription = null,
            modifier = Modifier.size(size.dp), // Kích thước icon bên trong
            contentScale = ContentScale.Fit
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewProfileContentField() {
    val navController = rememberNavController()

    SignInScreen(navController)
}