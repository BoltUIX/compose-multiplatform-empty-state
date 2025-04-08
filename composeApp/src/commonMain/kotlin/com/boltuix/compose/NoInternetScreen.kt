package com.boltuix.compose

// ✨ Jetpack Compose imports for animations, layout, styling, and resources
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// 🌍 Resources from Compose Multiplatform
import composem.composeapp.generated.resources.Res
import composem.composeapp.generated.resources.empty_state_no_internet_red
import composem.composeapp.generated.resources.no_internet_button
import composem.composeapp.generated.resources.no_internet_image_description
import composem.composeapp.generated.resources.no_internet_subtitle
import composem.composeapp.generated.resources.no_internet_title
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun NoInternetScreen(
    title: String = stringResource(Res.string.no_internet_title), // 📛 Title from resources
    subtitle: String = stringResource(Res.string.no_internet_subtitle), // 📄 Subtitle from resources
    imageRes: Painter = painterResource(Res.drawable.empty_state_no_internet_red), // 🖼️ Default image
    modifier: Modifier = Modifier,
    onRetry: () -> Unit = {} // 🔁 Retry action callback
) {
    val (alphaAnim, scaleAnim) = animateFadeAndScale() // 🎞️ Load entrance animation values

    BoxWithConstraints(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background) // 🎨 Apply background color
            .padding(NoInternetConstants.PADDING) // 📦 Apply padding
    ) {
        val isLandscape = maxWidth > maxHeight // 📱 Detect screen orientation

        val layoutModifier = Modifier
            .fillMaxSize()
            .alpha(alphaAnim) // ✨ Fade in effect
            .scale(scaleAnim) // 🔍 Zoom-in effect

        // 🔄 Dynamic layout based on orientation
        if (isLandscape) {
            Row(
                modifier = layoutModifier,
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                ImageSection(imageRes) // 🖼️ Display image
                TextSection(title, subtitle, onRetry) // 📝 Show texts and retry button
            }
        } else {
            Column(
                modifier = layoutModifier,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                ImageSection(imageRes)
                Spacer(modifier = Modifier.height(NoInternetConstants.TEXT_SPACING * 2)) // ➖ Spacing
                TextSection(title, subtitle, onRetry)
            }
        }
    }
}

@Composable
private fun TextSection(title: String, subtitle: String, onRetry: () -> Unit) {
    Column(
        modifier = Modifier
            .padding(horizontal = NoInternetConstants.TEXT_HORIZONTAL_PADDING), // 📏 Text padding
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            fontSize = NoInternetConstants.TITLE_FONT_SIZE, // 🔤 Title font size
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(NoInternetConstants.TEXT_SPACING))
        Text(
            text = subtitle,
            fontSize = NoInternetConstants.SUBTITLE_FONT_SIZE,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(NoInternetConstants.TEXT_SPACING * 2))

        // 🔁 Retry Button
        Button(
            onClick = onRetry,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.onBackground,
                contentColor = MaterialTheme.colorScheme.background
            )
        ) {
            Text(text = stringResource(Res.string.no_internet_button)) // 🔘 Button label
        }
    }
}

@Composable
private fun ImageSection(imageRes: Painter) {
    Image(
        painter = imageRes,
        contentDescription = stringResource(Res.string.no_internet_image_description), // 🖼️ Accessibility label
        modifier = Modifier
            .size(NoInternetConstants.IMAGE_SIZE) // 📏 Image size
            .padding(end = NoInternetConstants.IMAGE_PADDING) // ➡️ Padding to the right
    )
}

@Composable
internal fun animateFadeAndScale(): Pair<Float, Float> {
    // 🔄 Animate alpha from 0 to 1 for fade-in
    val alpha by animateFloatAsState(
        targetValue = 1f,
        animationSpec = tween(
            durationMillis = NoInternetConstants.ALPHA_DURATION,
            easing = FastOutSlowInEasing
        ),
        label = "alpha"
    )

    // 🔄 Animate scale from 0 to 1 for zoom-in
    val scale by animateFloatAsState(
        targetValue = 1f,
        animationSpec = tween(
            durationMillis = NoInternetConstants.SCALE_DURATION,
            easing = FastOutSlowInEasing
        ),
        label = "scale"
    )

    return alpha to scale
}

// 🧾 Constants for layout styling and animation timing
private object NoInternetConstants {

    // 📦 Padding around the entire layout
    val PADDING = 32.dp

    // 🖼️ Size of the image (width x height)
    val IMAGE_SIZE = 300.dp

    // ➡️ Space to the right of the image
    val IMAGE_PADDING = 16.dp

    // 📏 Horizontal padding for text content
    val TEXT_HORIZONTAL_PADDING = 16.dp

    // 🔁 Spacing between text elements
    val TEXT_SPACING = 8.dp

    // 🔠 Font size for the title text
    val TITLE_FONT_SIZE = 20.sp

    // 💬 Font size for the subtitle text
    val SUBTITLE_FONT_SIZE = 16.sp

    // ⏱️ Fade-in animation duration (milliseconds)
    const val ALPHA_DURATION = 800

    // ⏱️ Scale-in animation duration (milliseconds)
    const val SCALE_DURATION = 600
}