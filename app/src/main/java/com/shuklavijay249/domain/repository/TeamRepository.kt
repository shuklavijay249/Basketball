package com.shuklavijay249.domain.repository

import com.shuklavijay249.domain.model.TeamInfo
import kotlinx.coroutines.flow.Flow

interface TeamRepository {
    fun getTeams(): Flow<List<TeamInfo>>
}