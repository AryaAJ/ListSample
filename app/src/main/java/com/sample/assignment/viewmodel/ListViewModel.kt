package com.sample.assignment.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.assignment.db.data.MainList
import com.sample.assignment.repository.Repository
import com.sample.assignment.util.ApiResponse
import com.sample.assignment.util.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
Created by Ajay Arya on 28/08/20
 */
class ListViewModel @ViewModelInject constructor(private val listRepository: Repository) : ViewModel() {

    val showError = SingleLiveEvent<String>()
    val mainList = MutableLiveData<MainList>()

    /**
     *      On Failure - Update error msg in MutableLiveData object
     *
     * @param msg       Error Message
     */
    private fun showErrorOnFailure(msg: String) {
        viewModelScope.launch(Dispatchers.Main) {
            showError.postValue(msg)
        }
    }

    /**
     * Fetches venues list data from venue list repository and update date in MutableLiveData object
     *
     */
    fun getList() {

        viewModelScope.launch(Dispatchers.IO) {
            when (val result = listRepository.getList()) {
                is ApiResponse.Success -> {
                    showPlacesOnSuccess(result.successData)
                }
                is ApiResponse.Error -> result.exception.message?.let {
                    showErrorOnFailure(it)
                }
            }
        }
    }

    /**
     *  On Success - Update date in MutableLiveData object
     *
     * @param mainList       Response Object
     */
    private fun showPlacesOnSuccess(mainList: MainList) {
        viewModelScope.launch(Dispatchers.Main) {


//            mainList.rows.filter {
//                return if (!it.title.isNullOrEmpty()) else it
//            }
            this@ListViewModel.mainList.postValue(mainList)
        }
    }
}