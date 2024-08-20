package com.example.test2

data class Feedback (val text: String, val rating: Float){
    constructor() : this("", 0F)
}