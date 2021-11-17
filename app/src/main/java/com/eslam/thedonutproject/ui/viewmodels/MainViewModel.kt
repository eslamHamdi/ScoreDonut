package com.eslam.thedonutproject.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.eslam.thedonutproject.repositories.ScoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: ScoreRepository): ViewModel() {
}