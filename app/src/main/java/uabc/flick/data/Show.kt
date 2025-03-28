package uabc.flick.data

import android.content.Context
import com.squareup.moshi.Json
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

data class Show(
    val id: Int,
    val url: String,
    val name: String,
    val type: String,
    val language: String,
    val genres: List<String>,
    val status: String,
    val runtime: Int,
    val averageRuntime: Int,
    val premiered: String,
    val ended: String,
    val officialSite: String,
    val schedule: Json,
    val rating: Json,
    val weight: Int,
    val network: Json,
    val webChannel: String,
    val dvdCountry: String,
    val externals: Json,
    val image: Json,
    val summary: String,
    val updated: Long,
    val _links: Json,
)

// Función para cargar preguntas desde los assets usando Moshi
fun loadFromAssets(context: Context): List<Show> {
    val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    val listType = Types.newParameterizedType(List::class.java, Show::class.java)
    val jsonAdapter = moshi.adapter<List<Show>>(listType)

    val jsonString = context.assets.open("shows.json").bufferedReader().use {
        it.readText()
    }

    return jsonAdapter.fromJson(jsonString) ?: emptyList()
}
