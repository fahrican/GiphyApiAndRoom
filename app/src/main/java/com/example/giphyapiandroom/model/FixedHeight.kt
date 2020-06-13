package com.example.giphyapiandroom.model

import com.squareup.moshi.Json

data class FixedHeight(
    val height: String,
    val mp4: String,
    @field:Json(name = "mp4_size")
    val mp4Size: String,
    val size: String,
    val url: String,
    val webp: String,
    @field:Json(name = "webp_size")
    val webpSize: String,
    val width: String
)