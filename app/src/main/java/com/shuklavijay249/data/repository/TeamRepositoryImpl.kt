package com.shuklavijay249.data.repository

import com.shuklavijay249.data.datasource.LocalDataSource
import com.shuklavijay249.domain.model.TeamInfo
import com.shuklavijay249.domain.repository.TeamRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TeamRepositoryImpl(private val dataSource: LocalDataSource) : TeamRepository {
    override fun getTeams(): Flow<List<TeamInfo>> = flow {
        val list = dataSource.loadTeams().map {
            TeamInfo(
                teamId = it.tid,
                ta = it.ta?:"",
                fullName = it.tn ?: "Unknown",
                logoUrl = it.logo ?: "",
                city = it.tc ?: "Unknown",
                primaryColor = "#${it.color ?: "FFFFFF"}"
            )
        }
        emit(list)
    }
}