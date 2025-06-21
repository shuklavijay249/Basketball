package com.shuklavijay249.presentation.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.shuklavijay249.domain.model.ScheduleGame
import com.shuklavijay249.domain.model.TeamInfo
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ScheduleScreenSearch(games: List<ScheduleGame>, getHomeTeam: (ScheduleGame) -> TeamInfo?, getVisitorTeam: (ScheduleGame) -> TeamInfo?) {
    var searchQuery by remember { mutableStateOf("") }

    val filteredGames = remember(games, searchQuery) {
        if (searchQuery.isBlank()) {
            games
        } else {
            games.filter { game ->
                val home = getHomeTeam(game)
                val visitor = getVisitorTeam(game)
                val arena = game.arena
                val cityHome = home?.city ?: ""
                val cityVisitor = visitor?.city ?: ""

                arena.contains(searchQuery, ignoreCase = true) ||
                cityHome.contains(searchQuery, ignoreCase = true) ||
                cityVisitor.contains(searchQuery, ignoreCase = true) ||
                (home?.fullName?.contains(searchQuery, ignoreCase = true) ?: false) ||
                (visitor?.fullName?.contains(searchQuery, ignoreCase = true) ?: false)
            }
        }
    }

    val scheduleByMonth = remember(filteredGames) {
        filteredGames.groupBy {
            val date = parseDate(it.date)
            SimpleDateFormat("MMMM yyyy", Locale.US).format(date).uppercase(Locale.getDefault())
        }
    }

    val listState = rememberLazyListState()

    Column {
        TextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            label = { Text("Search by arena, city or team") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

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
                    GameCard(game = game, home = getHomeTeam(game), visitor = getVisitorTeam(game))
                }
            }
        }
    }
}
