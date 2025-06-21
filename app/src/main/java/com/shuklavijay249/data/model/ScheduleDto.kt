package com.shuklavijay249.data.model

data class ScheduleDto(
    val gid: String,
    val gametime: String,
    val arena_name: String,
    val h: TeamSideDto,
    val v: TeamSideDto,
    val st: Int
)