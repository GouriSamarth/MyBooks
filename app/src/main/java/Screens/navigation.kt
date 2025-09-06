package Screens


import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.firebase.auth.FirebaseAuth


@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val firebaseAuth = FirebaseAuth.getInstance()
    val currentUser = firebaseAuth.currentUser

    NavHost(
        navController = navController,
        startDestination = if (currentUser != null) "home" else "auth" // 👈 check login state
    ) {

        composable("auth") {
            AuthenticationScreen(
                navController = navController
            )
        }

        composable("home") {
            HomeScreen(navController = navController)
        }

        composable(
            "detail/{bookId}",
            arguments = listOf(navArgument("bookId") { type = NavType.StringType })
        ) { backStackEntry ->
            val bookId = backStackEntry.arguments?.getString("bookId") ?: ""
            DetailScreen(bookId = bookId, navController = navController)
        }

        composable("favorites") {
            FavoritesScreen(navController = navController)
        }

    }
}
