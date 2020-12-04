package com.example.runnershi_develop.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.runnershi_develop.data.*
import kotlinx.coroutines.launch

class RunningDetailViewModel(
    private val runningRepository: RunningRepository,
    private val runIdx: Int,
    private val gameIdx: Int
) : ViewModel() {
    val running = runningRepository.getRunning(runIdx)
    private val _coordinates = MutableLiveData<ArrayList<Coordinate>>()
    val coordinates: LiveData<ArrayList<Coordinate>>?
        get() = _coordinates


    init {
        viewModelScope.launch {
            val runningDetail = runningRepository.getRunningDetail(runIdx)
            _coordinates.value = runningDetail?.coordinates
            val myRunningDetail = runningRepository.getMyRunningDetail(runIdx)
            val opponentRunningDetail = runningRepository.getOpponentRunningDetail(gameIdx)
            runningRepository.updateRunningDetail(
                runIdx,
                runningDetail?.asRunningDetail(),
                myRunningDetail?.asMyRunningDetail(),
                opponentRunningDetail?.asOpponentRunningDetail()
            )
        }
    }
}