# compose-multiplatform-empty-state

![Compose Multiplatform](https://img.shields.io/badge/Jetpack%20Compose-Multiplatform-blueviolet)
![Kotlin](https://img.shields.io/badge/Kotlin-1.9%2B-orange)
![License](https://img.shields.io/github/license/BoltUIX/compose-multiplatform-empty-state)

A lightweight, reusable UI component built with **Jetpack Compose Multiplatform** for displaying beautiful and responsive empty states across **Android**, **Desktop**, and **Web** platforms.

## What is an Empty State?
- An empty state is a UI design pattern used when there's no content to display, such as no internet connection, empty search results, or first-time app usage.
- It typically includes an illustration, a title, a subtitle explaining the situation, and an optional call-to-action button (e.g., retry).
- Purpose: Guides users, reduces frustration, and encourages next steps. Improves UX by making "nothing" informative and engaging.

## Features
-  **Cross-platform** support (Android / Desktop / Web)
- **Lightweight and customizable**
- Plug-and-play empty UI screens
- Responsive design out-of-the-box
- Optional icon, title, description, and action button

## Preview
![Empty State Preview](https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEhTC0r4BYU7vY5KewOCY2VKEiHymABI6nualYiX50VBmOg0n5AUMkBtmuBUoWV5EuHDpPn2SJI37Gao3lS18uQmKdVL2OOZmseodfW-PysPAEItVSNx_IMCQ1y88xn1QRgOyd_5APpmqUkrNTzZHnWHpu4pNFU7DJyONQ7YBShdH13dPFTrR61Oexq8sXI/s16000/How%20to%20create%20a%20%20Empty%20State%20in%20Jetpack%20Compose%20for%20Kotlin%20Multiplatform.jpg)

* Explore the full UI experience in our [Compose UI Showcase App](https://play.google.com/store/apps/details?id=com.boltuix.compose)  
* Watch the tutorial on YouTube: [Compose Multiplatform Empty State Preview](https://youtu.be/J_XfGmayX4U)

## Implementation
For the full source code, check the files in this repository (e.g., `NoInternetScreen.kt`). You can clone and run the project to test it directly.

Here's a key snippet of the main composable function:

```kotlin
@Composable
fun NoInternetScreen(
    title: String = stringResource(Res.string.no_internet_title),
    subtitle: String = stringResource(Res.string.no_internet_subtitle),
    imageRes: Painter = painterResource(Res.drawable.empty_state_no_internet_red),
    modifier: Modifier = Modifier,
    onRetry: () -> Unit = {}
) {
    val (alphaAnim, scaleAnim) = animateFadeAndScale()

    BoxWithConstraints(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(NoInternetConstants.PADDING)
    ) {
        val isLandscape = maxWidth > maxHeight

        val layoutModifier = Modifier
            .fillMaxSize()
            .alpha(alphaAnim)
            .scale(scaleAnim)

        if (isLandscape) {
            Row(
                modifier = layoutModifier,
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                ImageSection(imageRes)
                TextSection(title, subtitle, onRetry)
            }
        } else {
            Column(
                modifier = layoutModifier,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                ImageSection(imageRes)
                Spacer(modifier = Modifier.height(NoInternetConstants.TEXT_SPACING * 2))
                TextSection(title, subtitle, onRetry)
            }
        }
    }
}
```

## Usage
To use the `NoInternetScreen` in your project, call the composable function:

```kotlin
NoInternetScreen(
    onRetry = { /* Handle retry action */ }
)
```

Customize as needed by passing title, subtitle, image, or modifier.

## Resources
- [How to Create Empty State in Jetpack Compose](https://www.boltuix.com/2025/08/how-to-create-empty-state-in-jetpack.html)

## Contributing
Contributions are welcome! Please open an issue or submit a pull request for any improvements or bug fixes.
