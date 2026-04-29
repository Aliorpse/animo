package tech.aliorpse.animo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import tech.aliorpse.animo.bangumi.BangumiService
import tech.aliorpse.animo.bangumi.model.BangumiTrendingResponse

class HomeViewModel : ViewModel() {
    val trendingData = MutableStateFlow<List<BangumiTrendingResponse.Data>>(emptyList())

    init {
        viewModelScope.launch {
            trendingData.update { BangumiService.getTrending().data }
        }
    }
}
