package com.example.countries.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun CountriesApp(
    viewModel: CountriesViewModel = viewModel(factory=CountriesViewModel.Factory)
){
    val state by viewModel.state.collectAsState()
    Scaffold(
        topBar = { AppBar() }
    ) {padding->
        CountriesScreen(
            state = state,
            onSelectCountry = viewModel::selectCountry,
            onDismissDialog = {viewModel.dismissDialog()},
            modifier = Modifier.padding(padding))

    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(modifier: Modifier=Modifier){
    CenterAlignedTopAppBar(title = {
            Text(text = "COUNTRIES", style = MaterialTheme.typography.titleLarge,
                color = Color.Black , fontWeight = FontWeight.Bold)
    }
    )

}