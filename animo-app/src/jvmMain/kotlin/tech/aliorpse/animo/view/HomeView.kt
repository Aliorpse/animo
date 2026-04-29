package tech.aliorpse.animo.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import tech.aliorpse.animo.component.BangumiCard
import tech.aliorpse.animo.util.mouseScrolling
import tech.aliorpse.animo.viewmodel.HomeViewModel

@Composable
fun HomeView() {
    val viewModel = viewModel { HomeViewModel() }

    Column(Modifier.padding(16.dp)) {
        Text("热门", fontSize = 30.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(16.dp))

        val rowState = rememberLazyListState()
        val trendingData by viewModel.trendingData.collectAsStateWithLifecycle()

        LazyRow(
            Modifier.mouseScrolling(rowState),
            state = rowState,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            if (trendingData.isNotEmpty()) {
                items(trendingData) {
                    BangumiCard(it.subject.images.large, it.subject.name)
                }
            } else {
                items(Int.MAX_VALUE) {
                    BangumiCard(null, null)
                }
            }
        }
    }
}
