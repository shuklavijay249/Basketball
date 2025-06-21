package com.shuklavijay249.data.mapper


import com.shuklavijay249.data.model.ScheduleDto
import com.shuklavijay249.domain.model.ScheduleGame

fun ScheduleDto.toDomain(): ScheduleGame {
    return ScheduleGame(
        gameId = gid,
        date = gametime,
        time = gametime.substringAfter("T"),
        arena = arena_name,
        homeTeamId = h.tid,
        visitorTeamId = v.tid,
        homeScore = (h.s.toIntOrNull() ?: 0).toString(),
        visitorScore = (v.s.toIntOrNull() ?: 0).toString(),
        status = st
    )
}

