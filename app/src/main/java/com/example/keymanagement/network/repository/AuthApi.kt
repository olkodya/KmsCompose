package com.example.keymanagement.network.repository

import com.example.keymanagement.network.dto.LoginDto
import com.example.keymanagement.network.dto.TokenDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("api/login")
    suspend fun getLogin(@Body loginDto: LoginDto) : Response<TokenDto>

}