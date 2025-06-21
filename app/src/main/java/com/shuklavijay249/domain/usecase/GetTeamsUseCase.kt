package com.shuklavijay249.domain.usecase


import com.shuklavijay249.domain.model.TeamInfo
import com.shuklavijay249.domain.repository.ScheduleRepository
import javax.inject.Inject

class GetTeamsUseCase @Inject constructor(private val repository: ScheduleRepository) {
    suspend operator fun invoke(): List<TeamInfo> {
        return repository.getTeams()
    }
}