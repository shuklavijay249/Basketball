package com.shuklavijay249.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.shuklavijay249.domain.model.TeamInfo

@Composable
fun TeamInfoColumn(team: TeamInfo?) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        if (team != null) {
            Image(
                painter = rememberAsyncImagePainter(
                    model = team.logoUrl,
                    placeholder = null,
                    error = null
                ),
                contentDescription = team.fullName,
                modifier = Modifier.size(48.dp)
            )
            Text(team.ta ?: "", color = Color.White)
        } else {
            Text("N/A", color = Color.Gray)
        }
    }
}

