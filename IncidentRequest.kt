package com.example.myproject.model

data class IncidentRequest(
    val userId: Long,
    val officeNumber: Int,
    val description: String
)