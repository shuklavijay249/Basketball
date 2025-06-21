package com.shuklavijay249.presentation.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shuklavijay249.domain.model.ScheduleGame
import com.shuklavijay249.domain.model.TeamInfo
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ScheduleScreen(
    games: List<ScheduleGame>,
    getHomeTeam: (ScheduleGame) -> TeamInfo?,
    getVisitorTeam: (ScheduleGame) -> TeamInfo?
) {
    val listState = rememberLazyListState()
    var selectedTab by remember { mutableStateOf(0) }

    // Group schedule by Month
    val scheduleByMonth = remember(games) {
        games.groupBy {
            val date = parseDate(it.date)
            SimpleDateFormat("MMMM yyyy", Locale.US).format(date).uppercase(Locale.getDefault())
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        // Page Title
        Text(
            text = "TEAM",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            textAlign = TextAlign.Center
        )

        // Tabs: Schedule and Games
        TabRow(selectedTabIndex = selectedTab) {
            Tab(selected = selectedTab == 0, onClick = { selectedTab = 0 }) {
                Text("Schedule", fontSize = 20.sp)
            }
            Tab(selected = selectedTab == 1, onClick = { selectedTab = 1 }) {
                Text("Games", fontSize = 20.sp)
            }
        }

        if (selectedTab == 0) {
            // Schedule list with sticky month headers
            LazyColumn(state = listState, modifier = Modifier.fillMaxSize()) {
                scheduleByMonth.forEach { (monthYear, monthGames) ->
                    stickyHeader {
                        Text(
                            text = monthYear,
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.DarkGray)
                                .padding(8.dp),
                            color = Color.White,
                            style = MaterialTheme.typography.titleMedium,
                            textAlign = TextAlign.Center
                        )
                    }
                    items(monthGames) { game ->
                        GameCard(
                            game = game,
                            home = getHomeTeam(game),
                            visitor = getVisitorTeam(game)
                        )
                    }
                }
            }
        } else {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text("Games tab content coming soon.")
            }
        }
    }
}

// Convert API date string to formatted string like "SAT JUL 01"
fun formatDateString(dateString: String): String {
    return try {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US)
        inputFormat.timeZone = TimeZone.getTimeZone("UTC")
        val date: Date = inputFormat.parse(dateString) ?: return ""

        val outputFormat = SimpleDateFormat("EEE MMM dd", Locale.US)
        outputFormat.format(date).uppercase(Locale.getDefault())
    } catch (e: Exception) {
        ""
    }
}

fun parseDate(dateString: String): Date {
    return try {
        val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US)
        format.timeZone = TimeZone.getTimeZone("UTC")
        format.parse(dateString) ?: Date()
    } catch (e: Exception) {
        Date()
    }
}
