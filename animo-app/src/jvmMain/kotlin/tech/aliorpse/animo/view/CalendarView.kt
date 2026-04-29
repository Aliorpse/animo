package tech.aliorpse.animo.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlinx.datetime.todayIn
import tech.aliorpse.animo.AnimoService
import tech.aliorpse.animo.component.BangumiCard
import tech.aliorpse.animo.viewmodel.CalendarViewModel
import kotlin.time.Clock
import kotlin.time.Instant

@Composable
fun CalendarView() {
    val viewModel = viewModel { CalendarViewModel() }

    val days = listOf("周一", "周二", "周三", "周四", "周五", "周六", "周日")
    var selectedIndex by remember {
        mutableStateOf(Clock.System.todayIn(TimeZone.of("UTC+8")).dayOfWeek.ordinal)
    }

    val calendarData by viewModel.calendarData.collectAsStateWithLifecycle()

    val groupedItems = remember(calendarData, selectedIndex) {
        val allItems = calendarData.firstOrNull()?.list ?: emptyList()

        val itemsForSelectedDay = allItems.filter { item ->
            if (item.recurrence == null) return@filter false

            runCatching {
                val localDateTime = Instant.parse(item.recurrence!!.startTime).toLocalDateTime(TimeZone.of("UTC+8"))
                localDateTime.dayOfWeek.ordinal == selectedIndex
            }.getOrDefault(false)
        }

        itemsForSelectedDay.groupBy { item ->
            runCatching {
                val localTime = Instant.parse(item.recurrence!!.startTime).toLocalDateTime(TimeZone.of("UTC+8")).time
                "${localTime.hour.toString().padStart(2, '0')}:${localTime.minute.toString().padStart(2, '0')}"
            }.getOrDefault("未知时间")
        }.toSortedMap()
    }

    Column(Modifier.padding(16.dp)) {
        Text("新番时间表", fontSize = 30.sp, fontWeight = FontWeight.Bold)

        Spacer(Modifier.height(16.dp))

        SecondaryTabRow(selectedIndex) {
            days.forEachIndexed { index, day ->
                val isSelected = selectedIndex == index
                Tab(
                    isSelected,
                    onClick = { selectedIndex = index },
                    text = {
                        Text(
                            text = day,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                        )
                    }
                )
            }
        }

        Spacer(Modifier.height(16.dp))

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            groupedItems.forEach { (time, itemsAtThisTime) ->
                item(time) {
                    Column {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = time,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.padding(end = 8.dp)
                            )
                            HorizontalDivider(
                                modifier = Modifier.weight(1f),
                                color = MaterialTheme.colorScheme.outlineVariant
                            )
                        }

                        Spacer(Modifier.height(8.dp))

                        FlowRow(
                            Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            itemsAtThisTime.forEach { item ->
                                BangumiCard(
                                    model = AnimoService.getImageUrlFromBangumiId(item.bangumiId),
                                    title = item.aliases.firstOrNull() ?: item.name
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
