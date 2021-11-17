package com.eslam.thedonutproject.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eslam.thedonutproject.data.remote.Result
import com.eslam.thedonutproject.domain.DataSource
import com.eslam.thedonutproject.domain.model.ScoreModel
import com.eslam.thedonutproject.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: DataSource): ViewModel() {


    private var _scoreLiveData:MutableLiveData<ScoreModel?> = MutableLiveData(null)
   val scoreLiveData:LiveData<ScoreModel?>
   get() = _scoreLiveData

    private var _errorEvent:SingleLiveEvent<String?> = SingleLiveEvent()

    val errorevent:SingleLiveEvent<String?>
    get() = _errorEvent


    var loadingState:MutableLiveData<Boolean> = MutableLiveData(false)






    fun getTheScore()
    {
        viewModelScope.launch {

            repository.getRemoteData().collect {

                when(it)
                {
                    is Result.Success ->{
                        _scoreLiveData.value =it.data
                        loadingState.value = false
                    }
                    is Result.Error ->{
                        _errorEvent.setValue(it.message)
                        loadingState.value = false
                    }
                    is Result.Loading ->{
                        loadingState.value = true
                    }
                }


            }
        }

    }
}