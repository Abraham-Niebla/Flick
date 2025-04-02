package uabc.flick.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import uabc.flick.R
import uabc.flick.data.Show

@Composable
fun ShowList(showsData: List<Show>, modifier: Modifier) {
    Column (modifier = modifier) {
        // Mueve el título fuera de LazyVerticalGrid
        Text(
            text = stringResource(R.string.list_top_bar),
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2), // Establece dos columnas
            contentPadding = PaddingValues(horizontal = 16.dp),
            modifier = Modifier
        ) {
            items(showsData) { show ->
                ShowElement(show, LocalContext.current)
            }
        }
    }
}
