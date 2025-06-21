package com.shuklavijay249

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import com.shuklavijay249.presentation.ui.ScheduleScreen
import com.shuklavijay249.presentation.viewmodel.ScheduleViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: ScheduleViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val scheduleState = viewModel.schedule.collectAsState()
            val teamsState = viewModel.teams.collectAsState()

            ScheduleScreen(
                games = scheduleState.value,
                getHomeTeam = { game -> teamsState.value.find { it.teamId == game.homeTeamId } },
                getVisitorTeam = { game -> teamsState.value.find { it.teamId == game.visitorTeamId } }
            )
        }
    }
}
