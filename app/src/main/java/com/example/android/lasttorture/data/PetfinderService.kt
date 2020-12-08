package com.example.android.lasttorture.data

import com.example.android.lasttorture.data.response.AnimalsResponse
import com.example.android.lasttorture.data.response.BreedsResponse
import com.example.android.lasttorture.data.response.TypesResponse
import io.reactivex.Single
import retrofit2.http.*

interface PetfinderService {

    @GET("animals")
    fun getAnimals(@Query ("type") type: String?,
                   @Query ("breed") breed: String?): Single<AnimalsResponse>

//    @GET("animals/{id}")
//    fun getSingleAnimal(@Path("id") id: Int): Single<AnimalsResponse>

    @GET("types")
    fun getTypes(): Single<TypesResponse>

//    @GET("types/{type}")
//    fun getSingleType(@Path("type") type: String): Single<TypesResponse>

    @GET("types/{type}/breeds")
    fun getBreeds(@Path("type") type: String): Single<BreedsResponse>

}
