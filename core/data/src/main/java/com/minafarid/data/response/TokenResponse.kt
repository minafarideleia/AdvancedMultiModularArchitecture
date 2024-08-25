package com.minafarid.data.response

import com.google.gson.annotations.SerializedName

class TokenResponse(
    @SerializedName("accessToken")
    val accessToken: String,
    @SerializedName("refreshToken")
    val refreshToken: String
)