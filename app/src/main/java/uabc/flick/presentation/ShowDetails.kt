package uabc.flick.presentation

import android.content.Intent
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
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import android.app.Activity
import coil.compose.AsyncImage
import uabc.flick.R
import uabc.flick.data.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowDetails(show: Show, activity: Activity, modifier: Modifier) {
    val context = LocalContext.current

    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(title = {
            IconButton(onClick = { activity.finish() }) {
                Icon(
                    Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Regresar"
                )
            }
        })

        Row(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
            ElevatedCard(
                modifier = Modifier.weight(1f),
            ) {
                Box(contentAlignment = Alignment.TopStart) {
                    AsyncImage(
                        model = show.image.original,
                        contentDescription = stringResource(R.string.imagen_description, show.name),
                    )
                    Box(
                        modifier = Modifier
                            .padding(5.dp)
                            .background(
                                MaterialTheme.colorScheme.secondaryContainer, shape =
                                    RoundedCornerShape(1.dp)
                            )
                    ) {
                        Text(text = show.rating.average.toString())
                    }
                }
            }

            //Información del Show
            Column(modifier = Modifier.weight(1f).padding(8.dp)) {
                Text(text = show.name, style = MaterialTheme.typography.bodyLarge)
                Text(text = stringResource(R.string.generos, show.genres.joinToString(", ")))
                Text(text = stringResource(R.string.estreno, show.premiered))
                Text(text = stringResource(R.string.pais, show.network.country.name))
                Text(text = stringResource(R.string.idioma, show.language))
            }
        }

        HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

        // Resumen
        Column(modifier = Modifier.padding(horizontal = 8.dp)){
            Text(text = stringResource(R.string.sumary),
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant)

            Text(text = show.summary.replace(Regex("<.*?>"), ""),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant)
        }

        Spacer(modifier = Modifier.height(16.dp))

        IconButton(onClick = {
            val sendIntent = Intent().apply {
                action = Intent.ACTION_SEND
                val site = show.officialSite ?: ""
                putExtra(Intent.EXTRA_TEXT, "Sitio oficial de ${show.name}: ${site}")
                type = "text/plain"
            }
            val shareIntent = Intent.createChooser(sendIntent, null)

            if (!activity.isFinishing && sendIntent.resolveActivity(activity.packageManager) != null) {
                activity.startActivity(shareIntent)
            }
        }) {
            Icon(Icons.Default.Share, contentDescription = "Compartir")
        }
    }
}
