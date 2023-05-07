package com.era.sliderdemo

sealed class MyStuff {
    data class Image(val imageUrl: String) : MyStuff()
    data class Video(val videoUrl: String) : MyStuff()
}