package com.shuklavijay249.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shuklavijay249.domain.model.ScheduleGame
import com.shuklavijay249.domain.model.TeamInfo
import com.shuklavijay249.domain.usecase.GetScheduleUseCase
import com.shuklavijay249.domain.usecase.GetTeamsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ScheduleViewModel @Inject constructor(
    private val getScheduleUseCase: GetScheduleUseCase,
    private val getTeamsUseCase: GetTeamsUseCase
) : ViewModel() {

    private val _schedule = MutableStateFlow<List<ScheduleGame>>(emptyList())
    val schedule: StateFlow<List<ScheduleGame>> = _schedule

    private val _teams = MutableStateFlow<List<TeamInfo>>(emptyList())
    val teams: StateFlow<List<TeamInfo>> = _teams

    init {
        fetchSchedule()
        fetchTeams()
    }

    private fun fetchSchedule() {
        viewModelScope.launch {
            _schedule.value = getScheduleUseCase()
        }
    }

    private fun fetchTeams() {
        viewModelScope.launch {
            _teams.value = getTeamsUseCase()
        }
    }
}
