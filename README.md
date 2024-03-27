# 🌠MeteoTracker🌠

## Introduction 
MeteoTracker is an application designed to provide detailed information about fallen meteorites. Utilizing data directly from NASA's open API, the app offers an interactive map for users to explore the locations of meteorite landings, detailed statistics through charts, and an informative list detailing each meteorite's specific characteristics.

## Features
- **Interactive Map**🗺️ : Powered by the Osmdroid library, users can visually navigate through the global map to discover the locations of fallen meteorites.
- **Statistics Charts**📊 : Includes a pie chart for meteorite classification and a line graph depicting the number of meteorites fallen within a user-selected year range, implemented with MPAndroidChart.
- **Detailed Data List**📝: An exhaustive list of meteorites allows users to select any item to view more in-depth details including the meteorite's name, classification, location, country, and more.
- **Dynamic Data Fetching**🛰️: Utilizes Retrofit for network operations and SOQL for efficient data querying from NASA's API.
- **Dependency Injection**💉: Implements Dagger-Hilt for streamlined and maintainable dependency injection.
- **Localization**🌐: Offers multiple language support with the ability for users to select their preferred language directly from the UI.
- **Theme Switching**🌗: Users can switch between light and dark themes to suit their preference.

## Technologies Used
- Jetpack Compose
- Osmdroid for interactive maps 
- MPAndroidChart for chart visualizations
- Retrofit for network requests
- SOQL for data querying
- Dagger-Hilt for dependency injection 
- Materal Design Theme Builder
