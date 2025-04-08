package com.boltuix.compose

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform