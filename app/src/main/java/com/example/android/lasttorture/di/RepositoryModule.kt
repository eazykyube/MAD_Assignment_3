package com.example.android.lasttorture.di

import android.content.res.TypedArray
import com.example.android.lasttorture.data.AnimalRepository
import com.example.android.lasttorture.data.PetfinderService
import com.example.android.lasttorture.data.db.AnimalDao
import com.example.android.lasttorture.data.db.BreedDao
import com.example.android.lasttorture.data.db.TypeDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun providesRepository(
        animalDao: AnimalDao,
        typeDao: TypeDao,
        breedDao: BreedDao,
        petfinderService: PetfinderService
    ): AnimalRepository {
        return AnimalRepository(animalDao, typeDao, breedDao, petfinderService)
    }
}