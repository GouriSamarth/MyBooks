package Screens

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class AuthViewModel : ViewModel() {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    var email = mutableStateOf("")
        private set
    var password = mutableStateOf("")
        private set
    var message = mutableStateOf("")
        private set
    var isLoading = mutableStateOf(false)
        private set
    var currentUserId = mutableStateOf<String?>(auth.currentUser?.uid)
        private set

    fun onEmailChange(newEmail: String) { email.value = newEmail }
    fun onPasswordChange(newPassword: String) { password.value = newPassword }

    fun signup(onSuccess: () -> Unit) {
        isLoading.value = true
        auth.createUserWithEmailAndPassword(email.value, password.value)
            .addOnCompleteListener { task ->
                isLoading.value = false
                if (task.isSuccessful) {
                    currentUserId.value = auth.currentUser?.uid
                    message.value = "Signup Successful!"
                    onSuccess()
                } else {
                    message.value = task.exception?.localizedMessage ?: "Signup failed"
                }
            }
    }

    fun login(onSuccess: () -> Unit) {
        isLoading.value = true
        auth.signInWithEmailAndPassword(email.value, password.value)
            .addOnCompleteListener { task ->
                isLoading.value = false
                if (task.isSuccessful) {
                    currentUserId.value = auth.currentUser?.uid
                    message.value = "Login Successful!"
                    onSuccess()
                } else {
                    message.value = task.exception?.localizedMessage ?: "Login failed"
                }
            }
    }

    fun logout(onLogout: () -> Unit) {
        auth.signOut()
        currentUserId.value = null
        onLogout()
    }
}