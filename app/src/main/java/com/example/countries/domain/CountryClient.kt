package com.example.countries.domain

interface CountryClient {
    suspend fun getCountries():List<DisplayCountry>
    suspend fun getCountry(code:String):DetailedCountry?
}