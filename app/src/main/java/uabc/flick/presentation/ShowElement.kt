package uabc.flick.presentation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import uabc.flick.R
import kotlin.String

@Composable
fun ShowElement(
    calificacion: Double,
    imagen: String,
    nombre: String,
    generos: List<String>,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier,
//        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
//            model = imagen,
//            contentDescription = stringResource(R.string.imagen_description, nombre),
//            contentScale = ContentScale.Crop,
//            modifier = Modifier
//                .fillMaxWidth()
//                .size(88.dp)
//                .clip(CircleShape),
            model = imagen,
            contentDescription = stringResource(R.string.imagen_description, nombre),
            contentScale = ContentScale.Fit, // Muestra la imagen completa
            modifier = modifier
                .fillMaxWidth()
                .size(88.dp) // Ajusta el tamaño según lo necesites
        )
        Text(
            text = nombre,
            modifier = Modifier.paddingFromBaseline(top = 24.dp, bottom = 8.dp),
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = generos.joinToString(separator = ", "),
            modifier = Modifier.paddingFromBaseline(top = 24.dp, bottom = 8.dp),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun AlignYourBodyElementPreview() {
    ShowElement(
        calificacion = 9.5,
        imagen = "https://static.tvmaze.com/uploads/images/medium_portrait/81/202627.jpg",
        nombre = "Under the Dome",
        generos = listOf("Comedia", "Tragedia"),
        modifier = Modifier
    )
}