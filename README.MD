# Compose Multiplatform Launches

This project is a reimplementation of the Kotlin Multiplatform (KMP) tutorial '[Create a multiplatform app using Ktor and SQLDelight](https://www.jetbrains.com/help/kotlin-multiplatform-dev/multiplatform-ktor-sqldelight.html)' using Jetpack Compose for Mobile Multiplatform. It demonstrates how to build a cross-platform application that runs on both Android and iOS using shared Kotlin code and Compose for UI.

## Features

- **Kotlin Multiplatform**: Shared business logic across Android and iOS.
- **Jetpack Compose**: Modern UI toolkit for building native UIs.
- **Compose for Multiplatform**: Compose UI components for both Android and iOS.
- **Dependency Injection**: Using Koin for dependency management.
- **Networking**: Using Ktor for making network requests.
- **Database**: Using SQLDelight for local database management.
- **Pull to Refresh**: Implemented using [dev.materii.pullrefresh](https://github.com/MateriiApps/pullrefresh).

## Project Structure

- `commonMain`: Contains shared code for both Android and iOS.
- `androidMain`: Contains Android-specific code.
- `iosMain`: Contains iOS-specific code.

## Dependencies

- **Compose**: For building UI.
- **Koin**: For dependency injection.
- **Ktor**: For networking.
- **SQLDelight**: For database management.
- **Coil**: For image loading.

## Setup

1. **Clone the repository**:
    ```sh
    git clone https://github.com/yourusername/kmplaunches.git
    cd kmplaunches
    ```

2. **Open the project in Android Studio**:
    - Use Android Studio Arctic Fox or later.

3. **Build the project**:
    - Sync the project with Gradle files.
    - Build the project to download all dependencies.

## Running the App

## Before running!
- check your system with [KDoctor](https://github.com/Kotlin/kdoctor)
- install JDK 17 or higher on your machine
- add `local.properties` file to the project root and set a path to Android SDK there

### Android
To run the application on android device/emulator:
- open project in Android Studio and run imported android run configuration

To build the application bundle:
- run `./gradlew :composeApp:assembleDebug`
- find `.apk` file in `composeApp/build/outputs/apk/debug/composeApp-debug.apk`
  Run android simulator UI tests: `./gradlew :composeApp:pixel5Check`

### iOS
To run the application on iPhone device/simulator:
- Open `iosApp/iosApp.xcproject` in Xcode and run standard configuration
- Or use [Kotlin Multiplatform Mobile plugin](https://plugins.jetbrains.com/plugin/14936-kotlin-multiplatform-mobile) for Android Studio
  Run iOS simulator UI tests: `./gradlew :composeApp:iosSimulatorArm64Test`

This project is licensed under the MIT License. See the `LICENSE` file for more details.

## Acknowledgements

Special thanks to:

- [Kotlin Multiplatform](https://kotlinlang.org/lp/mobile/)
- [Jetpack Compose](https://developer.android.com/jetpack/compose)
- [Koin](https://insert-koin.io/)
- [Ktor](https://ktor.io/)
- [SQLDelight](https://cashapp.github.io/sqldelight/)
- [Coil](https://coil-kt.github.io/coil/)
- [PullRefreshLayout](https://github.com/baoyongzhang/android-PullRefreshLayout)
- [Create a multiplatform app using Ktor and SQLDelight](https://www.jetbrains.com/help/kotlin-multiplatform-dev/multiplatform-ktor-sqldelight.html)

## What's Next?

- Add Network Status Detection
- Add Routes and Navigation with Voyager
- Add Unit Tests (lol)
- Add Flow testing with Turbine.
- Add UI Tests with Jetpack Compose Testing
- CI/CD with GitHub Actions
- Pre-commit hooks with Ktlint and Detekt
- Rename the app to CMMPLaunches
- A decent looking error screen (that captures a few different http errors into a few easily readable messages)
- Searching, Sorting and Filtering
- Responsive UI layout for tablets