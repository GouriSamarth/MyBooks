package Screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController


@Composable
fun AuthenticationScreen(navController: NavHostController, viewModel: AuthViewModel = viewModel()) {
    var isLogin by remember { mutableStateOf(true) }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(if (isLogin) "Login" else "Signup", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(value = viewModel.email.value, onValueChange = viewModel::onEmailChange,
                label = { Text("Email") }, singleLine = true, keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email
                ),
                modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(value = viewModel.password.value, onValueChange = viewModel::onPasswordChange,
                label = { Text("Password") }, singleLine = true, visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(20.dp))

            Button(onClick = {
                if (isLogin) viewModel.login {
                    navController.navigate("home") { popUpTo("auth") { inclusive = true } }
                } else viewModel.signup {
                    navController.navigate("home") { popUpTo("auth") { inclusive = true } }
                }
            }, modifier = Modifier.fillMaxWidth(), enabled = !viewModel.isLoading.value) {
                if (viewModel.isLoading.value) CircularProgressIndicator(modifier = Modifier.size(24.dp), color = Color.White, strokeWidth = 2.dp)
                else Text(if (isLogin) "Login" else "Signup")
            }

            Spacer(modifier = Modifier.height(10.dp))
            TextButton(onClick = { isLogin = !isLogin }) {
                Text(if (isLogin) "Don't have an account? Signup" else "Already have an account? Login")
            }

            Spacer(modifier = Modifier.height(20.dp))
            if (viewModel.message.value.isNotEmpty()) {
                Text(text = viewModel.message.value, color = Color.Red, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
            }
        }
    }
}
