package com.example.myproject.model

data class IncidentResponse(
    val id: Long,
    val userId: Long,
    val officeNumber: Int,
    val description: String,
    val timestamp: String
)