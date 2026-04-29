package tech.aliorpse.animo

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import tech.aliorpse.animo.component.AnimoView
import tech.aliorpse.animo.component.SideBar
import tech.aliorpse.animo.component.TitleBar

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        state = rememberWindowState(width = 1200.dp, height = 800.dp),
        undecorated = true,
    ) {
        var currentView by remember { mutableStateOf<AnimoView>(AnimoView.Home) }

        MaterialTheme(
            colorScheme = if (true) darkColorScheme() else lightColorScheme()
        ) {
            Surface {
                Row {
                    SideBar(currentView) { currentView = it }

                    Column {
                        TitleBar(
                            onClose = ::exitApplication,
                            onMinimize = { window.isMinimized = true }
                        )

                        Box(Modifier.fillMaxSize().weight(1f)) {
                            currentView.content()
                        }
                    }
                }
            }
        }
    }
}
