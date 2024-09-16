package com.example.googleheros

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.googleheros.data.Hero
import com.example.googleheros.data.HeroesRepository
import com.example.googleheros.ui.theme.GoogleHerosTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GoogleHerosTheme {
                SuperHeroesApp()
            }
        }
    }
}

@Composable
fun SuperHeroesApp() {
    LazyColumn() {
        items(HeroesRepository.heroes) { hero ->
            HeroCard(hero)
        }
    }
}


@Composable
fun HeroCard(hero: Hero, modifier: Modifier = Modifier) {

    Card(modifier = modifier
        .padding(16.dp)
        .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)) {

        Row (modifier = modifier
            .height(104.dp)){

           Information(hero = hero)
            Spacer(modifier = Modifier.weight(1f))
            InfoImage(hero = hero)
        }
    }
}
@Composable
fun InfoImage(
    hero: Hero,
    modifier: Modifier = Modifier
) {
    Image(painter = painterResource(id = hero.imageRes),
        contentDescription = null ,
        modifier = Modifier
            .height(72.dp)
            .aspectRatio(1f),
        contentScale = ContentScale.Crop)
}


@Composable
fun Information(
    hero: Hero,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(hero.name),
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(top = 8.dp)
        )
        Text(
            text = stringResource(hero.description),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}