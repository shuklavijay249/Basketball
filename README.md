# Basketball Schedule App

A simple Android app built using Jetpack Compose to display basketball game schedules with team metadata.

## Features

- Schedule list grouped by **Month** with sticky headers
- Home team shown on **right**, Visitor team on **left**
- Game status display: **Future / Live / Past**
- Shows:
   - Team logos and names
   - Game date and time (converted to local time)
   - Arena name
- For app team (`tid = 1610612748`):
   - Shows "vs" if Home, "@" if Away
- Dynamic row background color using team's primary color
- Pagination supported
- Tabs: **Schedule / Games (placeholder)**
- Scrolls to next upcoming game when opened
- Follows **MVVM Clean Architecture**
- **Hilt DI** integrated
- Coil used for image loading

## Tech Stack

- Kotlin 1.9.0
- Jetpack Compose 1.4.3
- MVVM + Clean Architecture
- Hilt for Dependency Injection
- Coil for image loading
- Material3 Design
- Coroutines + Flow for state management

## Project Structure

