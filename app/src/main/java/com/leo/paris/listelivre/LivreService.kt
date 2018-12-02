package com.leo.paris.listelivre

import retrofit2.Call
import retrofit2.http.GET

interface LivreService {

    @GET("books")
    fun listeLivre(): Call<List<Livre>>

}