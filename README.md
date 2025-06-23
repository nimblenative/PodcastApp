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