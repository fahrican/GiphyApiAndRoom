package com.example.giphyapiandroom.model

import com.google.gson.annotations.SerializedName

data class Images(
    @SerializedName("fixed_height_small_still")
    val fixedHeightSmallStill: FixedHeightSmallStill
)