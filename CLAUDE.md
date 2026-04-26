# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Build Commands

```bash
# Build Android debug APK
./gradlew :composeApp:assembleDebug

# Build Android release APK
./gradlew :composeApp:assembleRelease

# Run Android unit tests
./gradlew :composeApp:testDebugUnitTest

# Run a single test class
./gradlew :composeApp:testDebugUnitTest --tests "com.matttax.reado.YourTestClass"

# Run connected/instrumented tests (requires device/emulator)
./gradlew :composeApp:connectedDebugAndroidTest

# Lint
./gradlew :composeApp:lint
```

iOS builds are done via Xcode — open `iosApp/` in Xcode.

## Architecture

This is a **Kotlin Multiplatform (KMP)** project with **Compose Multiplatform** UI shared across Android and iOS.

### Module Structure

- **`composeApp/`** — The single shared module containing all code:
  - `commonMain` — Shared Kotlin + Compose UI code for both platforms
  - `androidMain` — Android-specific implementations (`MainActivity`, platform actuals)
  - `iosMain` — iOS-specific implementations (`MainViewController`, platform actuals)
- **`iosApp/`** — Xcode project that wraps the KMP framework for iOS

### Cross-Platform Pattern

Platform differences are handled via Kotlin's **expect/actual** pattern:
- `commonMain/Platform.kt` — defines `expect` interfaces/functions
- `androidMain/Platform.android.kt` — Android `actual` implementations
- `iosMain/Platform.ios.kt` — iOS `actual` implementations

Shared UI entry point is `commonMain/App.kt`. Android entry is `MainActivity`, iOS entry is `MainViewController`.

### Tech Stack

- Kotlin Multiplatform 2.3.20 / Compose Multiplatform 1.10.3
- Jetpack Compose Material3 (shared across platforms)
- `androidx-lifecycle-viewmodelCompose` + `runtimeCompose` for ViewModel support
- `minSdk` 24, `compileSdk`/`targetSdk` 36, JVM target 11
- Dependency versions managed centrally in `gradle/libs.versions.toml`
