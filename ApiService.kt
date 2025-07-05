// ApiService.kt
package com.example.myproject.network

import com.example.myproject.model.IncidentRequest
import com.example.myproject.model.IncidentResponse
import com.example.myproject.model.SignUpRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("api/users")
    fun registerUser(@Body signUpRequest: SignUpRequest): Call<Users>

    @POST("api/incidents")
    fun reportIncident(@Body incidentRequest: IncidentRequest): Call<IncidentResponse>
}
