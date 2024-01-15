package com.example.countries.data

import com.akshay.CountriesQuery
import com.akshay.CountryQuery
import com.example.countries.domain.DetailedCountry
import com.example.countries.domain.DisplayCountry
import com.apollographql.apollo3.ApolloClient


fun CountryQuery.Country.toDetailedCountry():DetailedCountry {
    return DetailedCountry(
        code = code,
        name = name,
        emoji = emoji,
        capital = capital ?: "capital not found",
        currency = currency ?: "currency not found",
        languages = languages.map {
            it.name
        },
        continent = continent.name
    )
}

fun CountriesQuery.Country.toDisplayCountry():DisplayCountry{
    return DisplayCountry(
        code= code,
        name=name,
        capital = capital?:"capital not found",
        emoji = emoji
    )
}

//this is a util function not a mapper
fun provideApolloClient():ApolloClient{
    return ApolloClient.Builder()
        .serverUrl("https://countries.trevorblades.com/graphql")
        .build()
}
