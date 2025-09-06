package Screens

import Networking.Book
import Networking.RetrofitInstance
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class HomeScreenViewModel: ViewModel() {

        var books = mutableStateListOf<Book>()
            private set

        init { fetchBooks() }

        private fun fetchBooks() {
            viewModelScope.launch {
                try {
                    val response = RetrofitInstance.api.getRecentBooks()
                    if (response.status == "ok") books.addAll(response.books)
                } catch (e: Exception) { Log.e("HomeViewModel", "Error", e) }
            }
        }
    }