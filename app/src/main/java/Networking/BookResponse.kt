package Networking

data class BookResponse
    (val status: String, val total: Int, val books: List<Book>)

data class Book(
    val id: String,
    val title: String,
    val subtitle: String?,
    val authors: String,
    val image: String,
    val url: String
)

// Firestore Entity
fun Book.toMap(): Map<String, Any> = mapOf(
    "id" to id,
    "title" to title,
    "subtitle" to subtitle.orEmpty(),
    "authors" to authors,
    "image" to image,
    "url" to url
)

fun mapToBook(map: Map<String, Any>): Book = Book(
    id = map["id"] as String,
    title = map["title"] as String,
    subtitle = map["subtitle"] as String,
    authors = map["authors"] as String,
    image = map["image"] as String,
    url = map["url"] as String
)
