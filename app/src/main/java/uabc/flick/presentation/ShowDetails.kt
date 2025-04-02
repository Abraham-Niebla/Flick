package uabc.flick.presentation

import android.content.Intent
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.*
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import uabc.flick.R
import uabc.flick.data.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowDetails(show: Show) {
    val activity = LocalActivity.current
    val context = LocalContext.current

    Box(
        contentAlignment = Alignment.TopStart,
        modifier = Modifier.fillMaxWidth()
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            IconButton(onClick = {
                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    val site = show.officialSite ?: ""
                    putExtra(Intent.EXTRA_TEXT, "Sitio oficial de ${show.name}: ${site}")
                    type = "text/plain"
                }
                val shareIntent = Intent.createChooser(sendIntent, null)
                context.startActivity(shareIntent)
            }) {
                Icon(
                    Icons.Default.Share,
                    contentDescription = "Compartir"
                )
            }
        }
    }
    Column {
        TopAppBar(title = {
            IconButton(onClick = { activity?.finish() }) {
                Icon(
                    Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Regresar"
                )
            }
        })
        Column(modifier = Modifier.fillMaxSize()) {
            Row {
                ElevatedCard(
                    modifier = Modifier.fillMaxSize(),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    )
                ) {
                    Column(
                        Modifier
                            .fillMaxWidth(),
                    ) {
                        Box(
                            contentAlignment = Alignment.TopStart,
                        ) {
                            AsyncImage(
                                model = show.image.original, // Sustituye con la URL de la imagen
                                contentDescription = stringResource(
                                    R.string.imagen_description,
                                    show.name
                                ),
                                contentScale = ContentScale.Crop,
//                        modifier = Modifier
//                            .height(200.dp) // Ajusta la altura de la imagen
//                            .fillMaxWidth()
                            )
                            Box(
                                modifier = Modifier
                                    .background(
                                        MaterialTheme.colorScheme.surfaceVariant, shape =
                                            RoundedCornerShape(1.dp)
                                    )
                            ) {
                                Text(text = show.rating.average.toString())
                            }
                        }
                    }
                }
                Column {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = show.name,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant // Contraste automático
                    )
                    val modifier = Modifier.padding(vertical = 4.dp)

                    Text(
                        text = stringResource(R.string.generos, show.genres.joinToString(", ")),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = modifier
                    )

                    Text(
                        text = stringResource(R.string.estreno, show.premiered),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = modifier
                    )

                    Text(
                        text = stringResource(R.string.pais, show.network.country.name),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = modifier
                    )

                    Text(
                        text = stringResource(R.string.idioma, show.language),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = modifier
                    )
                }
            }
            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
            Text(
                text = stringResource(R.string.sumary),
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant // Contraste automático
            )
            Text(
                text = show.summary.replace(Regex("<.*?>"), ""),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant // Contraste automático
            )
        }
    }
}