package com.example.countries.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.countries.CountriesApplication
import com.example.countries.data.AppoloCountryClient
import com.example.countries.domain.CountryClient
import com.example.countries.domain.DetailedCountry
import com.example.countries.domain.DisplayCountry
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class CountriesViewModel(private val appoloCountryClient: CountryClient): ViewModel(){

    private var _state = MutableStateFlow(CountriesState())
    val state= _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true
                )
            }
            _state.update {
                it.copy(
                    countries = getCountries(),
                    isLoading = false
                )
            }
        }
    }

    fun selectCountry(code:String){
        viewModelScope.launch {
            _state.update {
                it.copy(
                    selectedCountry = getCountry(code)
                )
            }
        }
    }

    fun dismissDialog(){
        viewModelScope.launch {
            _state.update {
                it.copy(
                    selectedCountry = null
                )
            }
        }
    }

    private suspend fun getCountries():List<DisplayCountry>{
       return appoloCountryClient.getCountries().sortedBy { it.name}
    }
    private suspend fun getCountry(code:String):DetailedCountry?{
        return appoloCountryClient.getCountry(code)
    }
    companion object{
        val Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as CountriesApplication)
                CountriesViewModel(application.container.countryClient)
            }
        }
    }
}
data class CountriesState(
    val countries:List<DisplayCountry> = emptyList(),
    val isLoading:Boolean=false,
    val selectedCountry:DetailedCountry?=null
)