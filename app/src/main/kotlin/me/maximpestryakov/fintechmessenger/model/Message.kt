package me.maximpestryakov.fintechmessenger.model

data class Message(val id: Int, val senderId: Int, val body: String, val time: Long)
