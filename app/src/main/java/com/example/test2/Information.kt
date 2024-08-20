package com.example.test2

data class Information (val email: String, val name: String, val phone: String, val gender: String){
    constructor() : this("", "", "", "")
}

