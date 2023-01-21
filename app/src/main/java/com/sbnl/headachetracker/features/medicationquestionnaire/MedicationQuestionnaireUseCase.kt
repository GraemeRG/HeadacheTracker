package com.sbnl.headachetracker.features.medicationquestionnaire

import com.sbnl.headachetracker.DateTimeProvider
import com.sbnl.headachetracker.repositories.HeadacheRepository
import com.sbnl.headachetracker.repositories.Medication
import com.sbnl.headachetracker.repositories.MedicationRepository

class MedicationQuestionnaireUseCase(
    private val dateTimeProvider: DateTimeProvider,
    private val headacheRepository: HeadacheRepository,
    private val medicationRepository: MedicationRepository
) {

    suspend fun loadMedicationTypes(): List<Medication> =
        medicationRepository.getAllMedications()

    suspend fun addMedicationToHeadache(headacheId: Long, medicationId: Long) {
        headacheRepository.updateHeadacheWithMedication(
            headacheId = headacheId,
            medicationId = medicationId,
            medicationTakenTime = dateTimeProvider.nowUtcInMillis()
        )
    }

    suspend fun addNewMedicationAndLogAgainstHeadache(headacheId: Long, customMedication: String) {
        medicationRepository
            .addNewMedicationType(customMedication)
            ?.apply {
                addMedicationToHeadache(headacheId, this)
            } ?: FailedToSaveNewMedicationException
    }
}

object FailedToSaveNewMedicationException : Exception()