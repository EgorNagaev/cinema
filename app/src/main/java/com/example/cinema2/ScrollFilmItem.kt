package com.example.cinema2

data class ScrollFilmItem(
    val age: String,
    val description: String,
    val images: List<String>,
    val movieId: String,
    val name: String,
    val poster: String,
    val tags: List<Tag>
)