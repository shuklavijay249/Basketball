package com.shuklavijay249.domain.repository

import com.shuklavijay249.domain.model.ScheduleGame
import com.shuklavijay249.domain.model.TeamInfo

interface ScheduleRepository {
    suspend fun getSchedule(): List<ScheduleGame>
    suspend fun getTeams(): List<TeamInfo>
}
