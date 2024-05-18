package com.example.noticias

// Se define la clase con los campos idénticos a los retornados por la API
data class Noticia(
    val meta:Meta,
    val data:List<Notice>,

)
data class Notice(
    val title: String,
    val snippet: String,
    val description: String,
    val url: String,
    val image_url: String,
    val published_at: String
)
data class Meta(
    val found: Int,
    val returned: Int,
    val limit: Int,
    val page: Int
)


