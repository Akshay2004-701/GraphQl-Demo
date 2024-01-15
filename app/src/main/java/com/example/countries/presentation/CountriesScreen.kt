package com.example.countries.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.countries.domain.DetailedCountry
import com.example.countries.domain.DisplayCountry

@Composable
fun CountriesScreen(
    modifier: Modifier=Modifier,
    state: CountriesState,
    onSelectCountry:(code:String)->Unit,
    onDismissDialog:()->Unit
){
Box(modifier = modifier.fillMaxSize()){
    if (state.isLoading){
        CircularProgressIndicator(
            Modifier.align(Alignment.Center)
        )
    }
    else{
        LazyColumn(modifier = Modifier.fillMaxSize()){
            items(state.countries){
                CountryItem(displayCountry = it,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onSelectCountry(it.code) }
                        .padding(16.dp)
                    )
            }
        }
    }
    if (state.selectedCountry != null){
        CountryDialog(
            onDismissDialog = onDismissDialog,
            country = state.selectedCountry,
            modifier = Modifier.clip(RoundedCornerShape(5.dp))
                .background(Color.White)
                .padding(16.dp))
    }
}
}

@Composable
fun CountryItem(
    modifier: Modifier=Modifier,
    displayCountry: DisplayCountry,

){
    Row(
        modifier, verticalAlignment = Alignment.CenterVertically
    ){
        Text(text = displayCountry.emoji, fontSize = 30.sp)
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier=Modifier.weight(1f)) {
         Text(text = displayCountry.name, fontSize = 24.sp)
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = displayCountry.capital)
        }
    }

}

@Composable
fun CountryDialog(
    modifier: Modifier=Modifier,
    onDismissDialog: () -> Unit,
    country: DetailedCountry
){
    val joinedString = remember(country.languages) {
        country.languages.joinToString()
    }

    Dialog(onDismissRequest = onDismissDialog) {
        Column(modifier=modifier) {
            Row(modifier=Modifier.fillMaxWidth()) {
                Text(text = country.emoji, fontSize = 30.sp)
                Spacer(modifier = Modifier.width(16.dp))
                Text(text = country.name, fontSize = 24.sp)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "continent: " + country.continent)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "currency: " + country.currency)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "capital: " + country.capital)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Language(s): $joinedString")


        }
    }
}










