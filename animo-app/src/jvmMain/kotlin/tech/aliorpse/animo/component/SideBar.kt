package tech.aliorpse.animo.component

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.composables.icons.lucide.*
import tech.aliorpse.animo.view.HomeView
import tech.aliorpse.animo.view.CalendarView
import tech.aliorpse.animo.view.SearchView
import tech.aliorpse.animo.view.SettingsView

@Composable
fun SideBar(
    currentView: AnimoView,
    onScreenSelected: (AnimoView) -> Unit
) {
    NavigationRail(
        containerColor = MaterialTheme.colorScheme.surfaceContainerLow,
        modifier = Modifier.fillMaxHeight()
    ) {
        Spacer(Modifier.height(20.dp))

        IconButton(
            onClick = { onScreenSelected(AnimoView.Search) },
            modifier = Modifier.size(50.dp),
            colors = IconButtonDefaults.iconButtonColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
            ),
            content = { Icon(AnimoView.Search.icon, AnimoView.Search.label) }
        )

        Spacer(Modifier.weight(1f))

        AnimoView.all.forEach {
            NavigationRailItem(
                selected = currentView == it,
                onClick = { onScreenSelected(it) },
                icon = { Icon(it.icon, it.label) },
                modifier = Modifier.padding(vertical = 5.dp),
                label = { Text(it.label) },
                alwaysShowLabel = false
            )
        }

        Spacer(Modifier.height(5.dp))
    }
}

sealed class AnimoView(val label: String, val icon: ImageVector, val content: @Composable () -> Unit) {
    object Home : AnimoView("首页", Lucide.House, ::HomeView)
    object Profile : AnimoView("新番时间表", Lucide.Calendar, ::CalendarView)
    object Settings : AnimoView("设置", Lucide.Settings, ::SettingsView)
    object Search : AnimoView("搜索", Lucide.Search, ::SearchView)

    companion object {
        val all by lazy { listOf(Home, Profile, Settings) }
    }
}
