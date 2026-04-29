package tech.aliorpse.animo.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.window.WindowDraggableArea
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowScope
import com.composables.icons.lucide.Lucide
import com.composables.icons.lucide.Minus
import com.composables.icons.lucide.X

@Composable
fun WindowScope.TitleBar(
    onClose: () -> Unit,
    onMinimize: () -> Unit
) {
    WindowDraggableArea {
        Surface(Modifier.fillMaxWidth().height(40.dp)) {
            Row(
                modifier = Modifier.fillMaxSize().padding(horizontal = 12.dp),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onMinimize, modifier = Modifier.size(32.dp)) {
                    Icon(Lucide.Minus, "Minimize", Modifier.size(16.dp))
                }
                IconButton(onClick = onClose, modifier = Modifier.size(32.dp)) {
                    Icon(Lucide.X, "Close", Modifier.size(16.dp))
                }
            }
        }
    }
}
