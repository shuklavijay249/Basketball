package com.shuklavijay249.domain.model

data class ScheduleGame(
    val gameId: String,
    val date: String,
    val time: String,
    val arena: String,
    val homeTeamId: String,
    val visitorTeamId: String,
    val homeScore: String?,
    val visitorScore: String?,
    val status: Int
)