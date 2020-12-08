package com.example.android.lasttorture.di

import android.content.Context
import androidx.room.Room
import com.example.android.lasttorture.data.db.AnimalDao
import com.example.android.lasttorture.data.db.AnimalRoomDatabase
import com.example.android.lasttorture.data.db.BreedDao
import com.example.android.lasttorture.data.db.TypeDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class RoomModule(context: Context) {

    private val animalDatabase: AnimalRoomDatabase =
        Room.databaseBuilder(context, AnimalRoomDatabase::class.java, "animal_database")
            .fallbackToDestructiveMigration()
            .build()


    @Singleton
    @Provides
    fun providesRoomDatabase(): AnimalRoomDatabase {
        return animalDatabase
    }

    @Singleton
    @Provides
    fun providesAnimalDao(): AnimalDao = animalDatabase.animalDao()

    @Singleton
    @Provides
    fun providesTypeDao(): TypeDao = animalDatabase.typeDao()


    @Singleton
    @Provides
    fun providesBreedDao(): BreedDao = animalDatabase.breedDao()
}