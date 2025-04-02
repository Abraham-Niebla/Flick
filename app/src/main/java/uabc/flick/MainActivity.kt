package uabc.flick

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import uabc.flick.ui.theme.FlickTheme
import uabc.flick.presentation.*
import uabc.flick.data.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val showsDatabase = ShowDB()
        showsDatabase.loadFromAssets(this)
        setContent {
            FlickTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainView(
                        modifier = Modifier.padding(innerPadding),
                        showsData = showsDatabase.getAll()
                    )
                }
            }
        }
    }
}

@Composable
fun MainView(modifier: Modifier, showsData: List<Show>){
    ShowList(
        showsData = showsData,
        modifier = modifier
    )
}