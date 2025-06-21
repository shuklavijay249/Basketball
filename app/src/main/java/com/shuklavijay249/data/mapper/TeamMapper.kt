package com.shuklavijay249.data.mapper

import com.shuklavijay249.data.model.TeamDto
import com.shuklavijay249.domain.model.TeamInfo

fun TeamDto.toDomain(): TeamInfo {
    return TeamInfo(
        teamId = tid,
        fullName = tn ?: "",
        ta = ta ?: "",
        city = tc ?: "",
        logoUrl = logo ?: "",
        primaryColor = color ?: "000000"
    )
}
