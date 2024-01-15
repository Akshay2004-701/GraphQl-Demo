package com.example.countries.data

import android.content.Context
import com.example.countries.domain.CountryClient

interface Container {
     val countryClient: CountryClient
}

class DefaultContainer(private val context: Context):Container{
    override val countryClient: CountryClient by lazy {
         AppoloCountryClient(provideApolloClient())
    }
}