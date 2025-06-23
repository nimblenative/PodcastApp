# 🎧 Podcast Pro - A Modern Android Showcase
A sample podcast browsing app built with 100% Kotlin and the latest Android development tools. This project demonstrates a robust, scalable, and maintainable architecture for building modern Android applications.

## ✨ Features
- Effortless Browsing: Smooth, infinitely scrolling list of podcasts.

- Client-Side Paging: Efficiently handles large datasets from the API by using the Paging 3 library with a local Room database as the source of truth.

- Podcast Details: Tap on any podcast to view its detailed description and banner image.

- Favorites System: Mark and save your favorite podcasts. The favorite status is persisted locally and seamlessly integrated across all screens.

- Robust Error Handling: Gracefully handles network errors with user-friendly messages and retry actions.

- Cache-First Offline Support: After the initial data load, the app works entirely from the local database cache for an instant, offline-first experience.

- Pull-to-Refresh: Manually refresh the podcast list with the latest data from the server.

## 📸 Screenshots
<img src="https://github.com/user-attachments/assets/4b8d0b90-f32a-4fc6-b979-68b8cef96fd5" alt="home screen" style="width:30%; height:auto;"/>
<img src="https://github.com/user-attachments/assets/b4d8801b-ee53-4702-ae59-ccad2853f0ae" alt="details screen" style="width:30%; height:auto;"/>

## 🛠️ Tech Stack & Architecture
This project follows the official Android guide to app architecture, implementing a layered, MVVM pattern with Clean Architecture principles.

- 100% Kotlin: Including Kotlin Coroutines and Flow for asynchronous operations.

- Jetpack Compose: The entire UI is built with Jetpack Compose, Android's modern declarative UI toolkit.

- Architecture:

  - MVVM (Model-View-ViewModel): For a clean separation of UI and business logic.

  - Clean Architecture: With data, domain, and presentation layers to enforce separation of concerns and create a testable, independent codebase.

  - Repository Pattern: To abstract data sources.

  - Use Cases (Interactors): To encapsulate specific business logic.

- Jetpack Libraries:

  - Hilt: For compile-time safe dependency injection.

  - ViewModel: For managing UI-related data in a lifecycle-aware way.

  - Room: For creating a robust, offline-first persistence layer.

  - Paging 3: For efficient, client-side pagination from the local database.

  - Navigation Compose: For handling navigation between screens.

- Networking:

  - Retrofit: For type-safe HTTP calls to the REST API - [ListenNotes](https://www.listennotes.com/api/)

  - Moshi: For efficient JSON serialization.

- Image Loading:

  - Coil: For fast, modern image loading in Compose.
