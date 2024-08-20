package com.example.test2

data class Complaint (val service: String,
                      val title: String,
                      val description: String,
                      val visibility: String,
                      val status: String) {
    constructor() : this("", "", "", "", "")
}
