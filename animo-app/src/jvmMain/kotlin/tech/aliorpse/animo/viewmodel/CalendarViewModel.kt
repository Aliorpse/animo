package tech.aliorpse.animo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import tech.aliorpse.animo.AnimoService
import tech.aliorpse.animo.model.AniCalendarResponse

class CalendarViewModel : ViewModel() {
    val calendarData = MutableStateFlow<List<AniCalendarResponse.Season>>(emptyList())

    init {
        viewModelScope.launch {
            calendarData.update { AnimoService.getCalendar().data }
        }
    }
}
