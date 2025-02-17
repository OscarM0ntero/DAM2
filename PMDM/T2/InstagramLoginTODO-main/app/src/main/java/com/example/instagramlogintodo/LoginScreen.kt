package com.example.instagramlogintodo

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LoginScreen() {
    Box(
        Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Header(Modifier.align(Alignment.TopCenter))
        Body(Modifier.align(Alignment.Center))
        Footer(Modifier.align(Alignment.BottomCenter))
    }
}

@Composable
fun Header(modifier: Modifier) {
    // No es común tener un header en Instagram Login, pero puedes usarlo si deseas
    Spacer(modifier = Modifier.height(16.dp))
}

@Composable
fun Body(modifier: Modifier) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ImageLogo(modifier = Modifier.padding(bottom = 16.dp))
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        Email(email = email, onTextChanged = { email = it })
        Spacer(modifier = Modifier.height(8.dp))
        Password(password = password, onTextChanged = { password = it })
        Spacer(modifier = Modifier.height(8.dp))
        ForgotPassword(Modifier.align(Alignment.End))
        Spacer(modifier = Modifier.height(16.dp))
        LoginButton(isLoginEnabled = EnableLogin(email, password))
        Spacer(modifier = Modifier.height(16.dp))
        LoginDivider()
        Spacer(modifier = Modifier.height(16.dp))
        LoginSocial()
    }
}

@Composable
fun ImageLogo(modifier: Modifier) {
    Image(
        painter = painterResource(id = R.drawable.icon), // Usa el logo de Instagram
        contentDescription = "Instagram Logo",
        modifier = modifier.size(100.dp)
    )
}

@Composable
fun Email(email: String, onTextChanged: (String) -> Unit) {
    TextField(
        value = email,
        onValueChange = onTextChanged,
        placeholder = { Text("Email") },
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
    )
}

@Composable
fun Password(password: String, onTextChanged: (String) -> Unit) {
    var isPasswordVisible by remember { mutableStateOf(false) }
    TextField(
        value = password,
        onValueChange = onTextChanged,
        placeholder = { Text("Password") },
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            val eye_icon = if (isPasswordVisible) R.drawable.eye_icon else R.drawable.closed_eye_icon
            IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                Icon(painter = painterResource(id = eye_icon), contentDescription = null)
            }
        }
    )
}

@Composable
fun ForgotPassword(modifier: Modifier) {
    Text(
        text = "Forgot password?",
        style = TextStyle(color = Color.Blue, fontSize = 12.sp),
        modifier = modifier.clickable { /* Navigate to Forgot Password */ }
    )
}

@Composable
fun LoginButton(isLoginEnabled: Boolean) {
    Button(
        onClick = { /* Handle Login */ },
        enabled = isLoginEnabled,
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isLoginEnabled) Color.Blue else Color.LightGray,
            contentColor = Color.White
        )
    ) {
        Text("Log In")
    }

}

@Composable
fun LoginDivider() {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Divider(Modifier.weight(1f), color = Color.Gray)
        Text(" OR ", modifier = Modifier.padding(horizontal = 8.dp), color = Color.Gray)
        Divider(Modifier.weight(1f), color = Color.Gray)
    }
}

@Composable
fun LoginSocial() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Log in with Facebook",
            color = Color.Blue,
            style = TextStyle(fontWeight = FontWeight.Bold)
        )
    }
}

@Composable
fun Footer(modifier: Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Text("Don't have an account? ")
        Text(
            text = "Sign up.",
            style = TextStyle(color = Color.Blue, fontWeight = FontWeight.Bold),
            modifier = Modifier.clickable { /* Navigate to Sign Up */ }
        )
    }
}

fun EnableLogin(email: String, password: String): Boolean {
    return email.contains("@") && password.length > 6
}
