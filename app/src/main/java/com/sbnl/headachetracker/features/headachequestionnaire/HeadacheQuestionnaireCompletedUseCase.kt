package com.sbnl.headachetracker.features.headachequestionnaire

import com.sbnl.headachetracker.repositories.HeadacheRepository

class HeadacheQuestionnaireCompletedUseCase(private val headacheRepository: HeadacheRepository) {

    suspend fun saveNewHeadacheReport(report: HeadacheReport) {
        headacheRepository.storeHeadacheInfo(report)
    }
}