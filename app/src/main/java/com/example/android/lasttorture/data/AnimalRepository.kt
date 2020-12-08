package com.example.android.lasttorture.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.android.lasttorture.data.db.*
import com.example.android.lasttorture.data.model.Animal
import com.example.android.lasttorture.data.model.AnimalBreed
import com.example.android.lasttorture.data.model.AnimalType
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.coroutines.EmptyCoroutineContext

class AnimalRepository(
    private val animalDao: AnimalDao,
    private val typeDao: TypeDao,
    private val breedDao: BreedDao,
    var petFinderService: PetfinderService
) {

    fun testNetwork(): Boolean = petFinderService.getAnimals(null, null)
        .map {
            true
        }.onErrorReturn {
            false
        }.blockingGet()

    //Improve with cashing logic. DONE
    fun getAllAnimals(type: String?, breed: String?): LiveData<List<AnimalDbModel>> {
        return if (testNetwork()) {
            petFinderService.getAnimals(type, breed)
                    .subscribeOn(Schedulers.io())
                    .map {
                        val animals = it.animalsArray.map { a -> animalMapper(a) }
                        CoroutineScope(EmptyCoroutineContext).launch {
                            animalDao.insert(animals) // Cashing
                        }
                        MutableLiveData(animals) as LiveData<List<AnimalDbModel>>
                    }
                .onErrorReturn {
                    animalDao.getAlphabetizedAnimals()
                }.blockingGet()
        }
        else {
            animalDao.getAlphabetizedAnimals() // Getting from db
        }
    }

    fun getAllTypes(): LiveData<List<TypeDbModel>> {
        return if (testNetwork()) {
            petFinderService.getTypes()
                    .subscribeOn(Schedulers.io())
                    .map {
                        val types = it.typesArray.map { a -> typeMapper(a) }
                        CoroutineScope(EmptyCoroutineContext).launch {
                            typeDao.insert(types) // Cashing
                        }
                        MutableLiveData(types) as LiveData<List<TypeDbModel>>
                    }.blockingGet()
        }
        else {
            typeDao.getTypes() // Getting from db
        }
    }

    fun getAllBreeds(type: String): LiveData<List<BreedDbModel>> {
        return if (testNetwork()) {
            petFinderService.getBreeds(type)
                    .subscribeOn(Schedulers.io())
                    .map {
                        val breeds = it.breedsArray.map { a -> breedMapper(a, type) }
                        CoroutineScope(EmptyCoroutineContext).launch {
                            breedDao.insert(breeds) // Cashing
                        }
                        MutableLiveData(breeds) as LiveData<List<BreedDbModel>>
                    }.blockingGet()
        }
        else {
            breedDao.getBreeds(type) // Getting from db
        }
    }

    private fun animalMapper(animal: Animal): AnimalDbModel =
        AnimalDbModel(
            id = animal.id ?: -1,
            organizationId = animal.organizationId,
            type = animal.type ?: "",
            breed = animal.breed?.breed ?: "",
            age = animal.age,
            gender = animal.gender ?: "",
            size = animal.size ?: "",
            name = animal.name ?: "",
            description = animal.description ?: "",
            photoFullUrl = animal.photos?.firstOrNull()?.photoFull ?: "",
            photoSmallUrl = animal.photos?.firstOrNull()?.photoSmall ?: ""
        )

    private fun typeMapper(animalType: AnimalType): TypeDbModel =
            TypeDbModel(
                    name = animalType.name ?: ""
            )

    private fun breedMapper(animalBreed: AnimalBreed, type: String): BreedDbModel =
            BreedDbModel(
                    name = animalBreed.breed ?: "",
                    type = type
            )
}
