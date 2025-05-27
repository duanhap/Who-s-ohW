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
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.who_s_ohw.R
import com.example.who_s_ohw.navigation.HomeScreen
import com.example.who_s_ohw.navigation.SignInScreen
import com.example.who_s_ohw.ui.feature.changePassword.FieldInput
import com.example.who_s_ohw.ui.feature.profile.HeaderAppNormalBack

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SignUpScreen (navController: NavController){
    Scaffold {
        Surface(
            modifier = Modifier
                .fillMaxSize()
        ) {
            SignUpContent(navController)
        }
    }
}

@Composable
fun SignUpContent(navController: NavController) {

    var emailUser by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var rePassword by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var checked by remember { mutableStateOf(false) }

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
                SignOutContentField(navController,
                    emailUser = emailUser,
                    onEmailUserChange = { emailUser = it },
                    name = name,
                    onNameChange = { name = it },
                    password = password,
                    onPasswordChange = { password = it },
                    rePassword = rePassword,
                    onRePasswordChange = { rePassword = it },
                    checked = checked,
                    onCheckedChange = { checked = it }
                )
            }


        }
    }

}
@Composable
fun SignOutContentField(
    navController: NavController,
    emailUser: String,
    onEmailUserChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit,
    name: String,
    onNameChange: (String) -> Unit,
    rePassword: String,
    onRePasswordChange: (String) -> Unit,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFEEE2BC)) // màu nền vàng nhạt
            .padding(horizontal = 20.dp)
    ) {
        Text(
            text = "Sign Up",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            color = Color(0xFF000000),
            fontWeight = FontWeight.Bold,
            fontSize = 36.sp
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Create your account below",
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            color = Color(0xFF000000),
            fontWeight = FontWeight.Normal,
            fontSize = 13.sp
        )

        Spacer(modifier = Modifier.height(10.dp))
        FieldInputSignUp(
            label = "Name",
            placeholder = "Enter your name here ",
            text = name,
            onTextChange =onNameChange
        )
        FieldInputSignUp(
            label = "Email",
            placeholder = "Enter your email here ",
            text = emailUser,
            onTextChange = onEmailUserChange
        )
        PasswordFieldSignUp(
            label = "Password",
            text = password,
            placeholder = "Enter your password here ",
            onTextChange =  onPasswordChange
        )
        PasswordFieldSignUp(
            label = "Repassword",
            text = rePassword,
            placeholder = "Enter your repassword here ",
            onTextChange =  onRePasswordChange
        )

        Spacer(modifier = Modifier.height(0.dp))
        TermsAndConditions(
            isChecked = checked,
            onCheckedChange = onCheckedChange,
            onTermsClick = { /* Mở màn Terms */ }
        )
        Button(
            onClick = { navController.navigate(SignInScreen)},
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF5B2C06),
                contentColor = Color.White
            ),
            shape = CircleShape,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text(text = "Sign Up",
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 20.sp)
        }
        Spacer(modifier = Modifier.height(15.dp))
        SocialSignOutSection(
            onGoogleClick = { /* Handle Google sign-in */ },
            onFacebookClick = { /* Handle Facebook sign-in */ },
            onSignUpClick = { /* Handle sign-up */ }
        )
    }
}

@Composable
fun SocialSignOutSection(onGoogleClick: () -> Unit, onFacebookClick: () -> Unit, onSignUpClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {

        // Dòng "Or sign in with"
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 10.dp)
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
            modifier = Modifier.padding(top = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(24.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            SocialCircleIcon(imageId = R.drawable.ic_google,size = 24, onClick = onGoogleClick)
            SocialCircleIcon(imageId = R.drawable.ic_facebook, size = 32, onClick = onFacebookClick)
        }

        // Dòng Sign Up
        Row(
            modifier = Modifier.padding(top = 5.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Already have an account ?", color = Color.Black, fontSize = 15.sp)
            TextButton(onClick = onSignUpClick) {
                Text(
                    text = "Sign In",
                    color = Color(0xFF5C2C06), // màu nâu
                    textDecoration = TextDecoration.Underline
                )
            }
        }
    }
}
@Composable
fun FieldInputSignUp(
    label: String,
    placeholder: String,
    text: String,
    onTextChange: (String) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth().padding(bottom = 5.dp)) {
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
            placeholder = { Text(text = placeholder, color = Color.Gray , fontSize = 15.sp , modifier = Modifier.alpha(0.5f)) },
            shape = RoundedCornerShape(50),
            modifier = Modifier.fillMaxWidth(),
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
fun PasswordFieldSignUp(
    label: String,
    text: String,
    placeholder: String,
    onTextChange: (String) -> Unit
) {
    var passwordVisible by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxWidth().padding(bottom = 5.dp)) {
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
fun TermsAndConditions(
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    onTermsClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = isChecked,
            onCheckedChange = onCheckedChange,
            colors = CheckboxDefaults.colors(
                checkedColor = Color(0xFF5E321C), // màu nâu như hình
                checkmarkColor = Color.White,
                uncheckedColor = Color(0xFF5E321C)
            )
        )
        Spacer(modifier = Modifier.width(0.dp))
        Text(
            buildAnnotatedString {
                append("Agree with ")
                withStyle(
                    style = SpanStyle(
                        color = Color(0xFF5E321C), // nâu đậm
                        textDecoration = TextDecoration.Underline,
                        fontWeight = FontWeight.Medium,
                        fontSize = 15.sp
                    )
                ) {
                    append("Terms & Condition")
                }
            },
            modifier = Modifier.clickable { onTermsClick() },
            fontSize = 15.sp,
            color = Color.Black,
            fontWeight = FontWeight.Medium,

        )
    }
}



@Preview(showBackground = true)
@Composable
fun PreviewSignOut() {
    val navController = rememberNavController()

    SignUpScreen(navController)
}