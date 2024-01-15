package com.example.countries.data

import com.akshay.CountriesQuery
import com.akshay.CountryQuery
import com.apollographql.apollo3.ApolloClient
import com.example.countries.domain.CountryClient
import com.example.countries.domain.DetailedCountry
import com.example.countries.domain.DisplayCountry

class AppoloCountryClient(private val appoloClient: ApolloClient):CountryClient {

    override suspend fun getCountries(): List<DisplayCountry> {
        return appoloClient.query(CountriesQuery())
            .execute()
            .data
            ?.countries
            ?.map {
                it.toDisplayCountry()
            }
            ?: emptyList()
    }

    override suspend fun getCountry(code: String): DetailedCountry? {
        return appoloClient.query(CountryQuery(code))
            .execute()
            .data
            ?.country
            ?.toDetailedCountry()
    }

}