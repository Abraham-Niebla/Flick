package uabc.flick.data

import android.content.Context
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class ShowDB() {
    private var shows: List<Show> = emptyList()

    // Función para cargar preguntas desde los assets usando Moshi
    fun loadFromAssets(context: Context) {
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        val listType = Types.newParameterizedType(List::class.java, Show::class.java)
        val jsonAdapter = moshi.adapter<List<Show>>(listType)

        val jsonString = context.assets.open("shows.json").bufferedReader().use {
            it.readText()
        }

        this.shows = jsonAdapter.fromJson(jsonString) ?: emptyList()
    }

    fun get(id: Int): Show {
        return this.shows.find { it.id == id } ?: Show()
    }

    fun getAll(): List<Show>{
        return this.shows
    }
}