package com.shuklavijay249.data.repository

import com.shuklavijay249.data.datasource.LocalDataSource
import com.shuklavijay249.data.mapper.toDomain
import com.shuklavijay249.domain.model.ScheduleGame
import com.shuklavijay249.domain.model.TeamInfo
import com.shuklavijay249.domain.repository.ScheduleRepository
import javax.inject.Inject

class ScheduleRepositoryImpl @Inject constructor(private val localDataSource: LocalDataSource) : ScheduleRepository {
    override suspend fun getSchedule(): List<ScheduleGame> {
        return localDataSource.loadSchedule().map { it.toDomain() }
    }

    override suspend fun getTeams(): List<TeamInfo> {
        return localDataSource.loadTeams().map { it.toDomain() }
    }
}

