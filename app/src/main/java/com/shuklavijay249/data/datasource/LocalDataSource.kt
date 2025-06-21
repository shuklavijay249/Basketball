package com.shuklavijay249.data.datasource

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.shuklavijay249.data.model.ScheduleDto
import com.shuklavijay249.data.model.TeamDto
import dagger.hilt.android.qualifiers.ApplicationContext
import org.json.JSONObject
import javax.inject.Inject

class LocalDataSource @Inject constructor(@ApplicationContext private val context: Context) {

    fun loadSchedule(): List<ScheduleDto> {
        val json = context.assets.open("Schedule.json").bufferedReader().use { it.readText() }
        val jsonObject = JSONObject(json)
        val schedules = jsonObject.getJSONObject("data").getJSONArray("schedules")
        return Gson().fromJson(schedules.toString(), object : TypeToken<List<ScheduleDto>>() {}.type)
    }

    fun loadTeams(): List<TeamDto> {
        val json = context.assets.open("teams.json").bufferedReader().use { it.readText() }
        val jsonObject = JSONObject(json)
        val teams = jsonObject.getJSONObject("data").getJSONArray("teams")
        return Gson().fromJson(teams.toString(), object : TypeToken<List<TeamDto>>() {}.type)
    }
}
