@file:OptIn(ExperimentalComposeUiApi::class)

package tech.aliorpse.animo.util

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.pointerInput
import kotlin.math.abs

fun Modifier.mouseScrolling(
    state: ScrollableState,
    sensitivity: Float = 200f
): Modifier = composed {
    var target by remember { mutableFloatStateOf(0f) }
    val animatable = remember { Animatable(0f) }

    LaunchedEffect(target) {
        if (target == animatable.value) return@LaunchedEffect

        var lastValue = animatable.value
        runCatching {
            state.scroll {
                animatable.animateTo(
                    targetValue = target,
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioNoBouncy,
                        stiffness = 400f
                    )
                ) {
                    val delta = value - lastValue
                    val consumed = scrollBy(delta)
                    lastValue = value

                    if (abs(delta - consumed) > 0.1f) {
                        target = value
                    }
                }
            }
        }
    }

    pointerInput(Unit) {
        awaitPointerEventScope {
            while (true) {
                val event = awaitPointerEvent()
                if (event.type == PointerEventType.Scroll) {
                    val change = event.changes.firstOrNull() ?: continue

                    val deltaY = change.scrollDelta.y
                    val deltaX = change.scrollDelta.x
                    val totalDelta = (deltaY + deltaX) * sensitivity

                    if (totalDelta != 0f) {
                        target += totalDelta
                        change.consume()
                    }
                }
            }
        }
    }
}
