package com.sbnl.headachetracker.repositories

import com.sbnl.headachetracker.database.HeadacheDatabase
import com.sbnl.headachetracker.database.medication.MedicationObject

interface MedicationRepository {

    suspend fun getAllMedications(): List<Medication>
    suspend fun addNewMedicationType(medication: String): Long? // The ID of the new medication type after being saved in the db, null indicates it failed ot store it in the db
}

class MedicationRepositoryImpl(
    private val database: HeadacheDatabase
) : MedicationRepository {
    override suspend fun getAllMedications(): List<Medication> =
        database
            .medicationDao()
            .getAll()
            .map { medication ->
                Medication(
                    medication.medicationId,
                    medication.name
                )
            }

    override suspend fun addNewMedicationType(medication: String): Long? =
        with(database.medicationDao()) {
            if(getMedicationByName(medication).isNotEmpty()) {
                throw MedicationAlreadyExistsException
            } else {
                insertAll(MedicationObject(medication))
                getMedicationByName(medication).lastOrNull()?.medicationId
            }
        }
}

data class Medication(
    val id: Long,
    val name: String
)

object MedicationAlreadyExistsException: Exception()