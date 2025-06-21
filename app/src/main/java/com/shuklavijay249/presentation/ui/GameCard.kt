package com.shuklavijay249.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.shuklavijay249.domain.model.ScheduleGame
import com.shuklavijay249.domain.model.TeamInfo

@Composable
fun GameCard(game: ScheduleGame, home: TeamInfo?, visitor: TeamInfo?) {
    // Checking game status: past, live or upcoming
    val isPast = game.status == 3
    val isLive = game.status == 2
    val isUpcoming = game.status == 1

    val bgColor = Color(0xFF1C1C1E)
    val textColor = Color.White

    val formattedDate = formatDateString(game.date)
    val homeAway = if (game.homeTeamId == home?.teamId) "HOME" else "AWAY"

    val appTeamId = "1610612748" // App's team ID
    val vsAtSymbol = when (appTeamId) {
        game.homeTeamId -> "vs"
        game.visitorTeamId -> "@"
        else -> "vs"
    }

    val headerStatusText = if (isPast) "FINAL" else ""

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(containerColor = bgColor)
    ) {
        Column(Modifier.padding(8.dp)) {

            //Top Header: HOME/AWAY | JUL 01 | FINAL (for past only)
            Text(
                text = listOfNotNull(homeAway, formattedDate, headerStatusText)
                    .filter { it.isNotBlank() }
                    .joinToString(" | "),
                color = textColor,
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(8.dp))

            if (!isUpcoming) {
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    TeamInfoColumn(visitor) // Visitor team left as shown in the UI

                    Row(
                        modifier = Modifier
                            .padding(top = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = game.visitorScore.toString(),
                            color = textColor,
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(end = 4.dp)
                        )
                        Text(
                            text = vsAtSymbol,
                            color = textColor,
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(horizontal = 4.dp)
                        )
                        Text(
                            text = game.homeScore.toString(),
                            color = textColor,
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(start = 4.dp)
                        )
                    }

                    // Home team on Right as shown in the UI
                    TeamInfoColumn(home)
                }
            } else { //For upcoming games — No scores as shown in the UI
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    TeamInfoColumn(visitor)
                    Text(
                        text = vsAtSymbol,
                        color = textColor,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                    TeamInfoColumn(home)
                }
            }

            //Arena Name
            Text(
                text = game.arena ?: "",
                color = Color.LightGray,
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp)
                    .wrapContentWidth(Alignment.CenterHorizontally)
            )

            //LIVE info shown only when game is Live
            if (isLive) {
                Spacer(Modifier.height(4.dp))
                Text(
                    text = "LIVE  |  4TH QTR  |  02:12", // place holder static for now
                    color = Color.Green,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.CenterHorizontally)
                        .padding(4.dp)
                )
            }

            //Ticket button only for upcoming games as shown in the UI
            if (isUpcoming) {
                Spacer(Modifier.height(8.dp))
                Button(
                    onClick = { /* TODO: Add ticket click action */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp)
                ) {
                    Text("BUY TICKETS ON Ticketmaster")
                }
            }
        }
    }
}
