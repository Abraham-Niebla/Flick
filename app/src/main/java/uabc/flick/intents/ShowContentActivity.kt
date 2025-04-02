package uabc.flick.intents

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import uabc.flick.data.*
import uabc.flick.presentation.ShowDetails

class ShowDetailsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = intent
        val showsDatabase = ShowDB()
        val showID = intent.getIntExtra("showID", 0)
        val show: Show = showsDatabase.get(showID)

        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                ShowDetails(show)
            }
        }
    }
}