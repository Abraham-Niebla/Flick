package uabc.flick.presentation

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import uabc.flick.R
import uabc.flick.data.*
import uabc.flick.intents.*

@Composable
fun ShowElement(
    show: Show, context: Context
) {
    ElevatedCard(
        modifier = Modifier
            .width(160.dp) // Ancho de la carta
            .padding(8.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer // Se adapta al tema
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp), // Sombra adaptable
        onClick = {
            val intent = Intent(context, ShowDetailsActivity::class.java)
            intent.putExtra("showID", show.id)
            context.startActivity(intent)
        }
    ) {
        Column (
            Modifier
                .fillMaxWidth(),
        ) {
            // Imagen con calificación superpuesta
            Box(
                contentAlignment = Alignment.TopStart,
                modifier = Modifier.fillMaxWidth()
            ) {
                AsyncImage(
                    model = show.image.medium, // Sustituye con la URL de la imagen
                    contentDescription = stringResource(R.string.imagen_description, show.name),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(200.dp) // Ajusta la altura de la imagen
                        .fillMaxWidth()
                )

                // Calificación (posición superior izquierda)
                Box(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(5.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(MaterialTheme.colorScheme.surfaceVariant, shape =
                                                RoundedCornerShape(1.dp))
                ) {
                    Text(
                        text = show.rating.average.toString(),
                        style = MaterialTheme.typography.labelSmall.copy(fontSize = 10.sp),
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(3.dp)
                    )
                }
            }

            // Título de la serie
            Text(
                text = show.name,
                modifier = Modifier
                    .padding(horizontal = 8.dp, vertical = 4.dp),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface // Texto adaptable
            )

            // Géneros de la serie
            Text(
                text = show.genres.joinToString(", "),
                modifier = Modifier
                    .padding(horizontal = 8.dp, vertical = 4.dp),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 1, // Limita a una línea
                overflow = TextOverflow.Ellipsis // Agrega "..." si se excede
            )
        }
    }
}
