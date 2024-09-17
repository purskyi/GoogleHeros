package com.example.googleheros

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
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
    Scaffold(topBar = { TopAppBarWithAppName() })
    { it ->
        LazyColumn(contentPadding = it) {
            items(HeroesRepository.heroes) { hero ->
                HeroCard(hero)
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarWithAppName(modifier: Modifier = Modifier) {
   /* CenterAlignedTopAppBar(
        title = { Image(modifier = modifier
            .size(72.dp)
            .padding(8.dp),
        painter = painterResource(id = R.drawable.ic_launcher_foreground),
        contentDescription = null ) }
    )*/
    CenterAlignedTopAppBar(
        title = {
            Text(text = stringResource(id = R.string.app_name),
                fontWeight = FontWeight.Bold)
        })


}


@Composable
fun HeroCard(hero: Hero, modifier: Modifier = Modifier) {

Box(modifier = modifier){
    Card(
        modifier = modifier
            .padding(start = 16.dp, end = 16.dp, bottom = 8.dp)
            .clip(RoundedCornerShape(16.dp)),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = modifier
                .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)
                .height(104.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Absolute.SpaceBetween,
            verticalAlignment = Alignment.Top) {

            Information(hero = hero,modifier.weight(1f))
         //   Spacer(modifier = Modifier.weight(1f))
            InfoImage(hero = hero)
        }
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
        modifier = modifier
            .padding(16.dp)
            .size(72.dp)
            .aspectRatio(1f)
            .clip(RoundedCornerShape(8.dp)),
        contentScale = ContentScale.Crop)
}


@Composable
fun Information(
    hero: Hero,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier
        .padding(16.dp)) {
        Text(
            text = stringResource(hero.name),
            style = MaterialTheme.typography.labelLarge,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = stringResource(hero.description),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}
