package com.shuklavijay249.domain.usecase

import com.shuklavijay249.domain.model.ScheduleGame
import com.shuklavijay249.domain.repository.ScheduleRepository
import javax.inject.Inject


class GetScheduleUseCase @Inject constructor(
    private val repository: ScheduleRepository
) {
    suspend operator fun invoke(): List<ScheduleGame> {
        return repository.getSchedule()
    }
}
