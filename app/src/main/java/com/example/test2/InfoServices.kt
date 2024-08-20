package com.example.test2

data class InfoServices (val email: String, val name: String, val phone: String, val services: String){
    constructor() : this("", "", "", "")
}