package uabc.flick.data

data class Show(
    val id: Int = -1,
    val url: String = "",
    val name: String = "Desconocido",
    val type: String = "",
    val language: String = "",
    val genres: List<String> = emptyList(),
    val status: String = "",
    val runtime: Int = 0,
    val averageRuntime: Int = 0,
    val premiered: String = "",
    val ended: String? = null,
    val officialSite: String? = null,
    val schedule: Schedule = Schedule(),
    val rating: Rating = Rating(),
    val weight: Int = 0,
    val network: Network = Network(),
    val webChannel: String? = null,
    val dvdCountry: String? = null,
    val externals: Externals = Externals(),
    val image: Image = Image(),
    val summary: String = "",
    val updated: Long = 0,
    val _links: Links = Links()
)

data class Schedule(
    val time: String? = null,
    val days: List<String> = emptyList()
)

data class Rating(
    val average: Double? = null
)

data class Network(
    val id: Int = -1,
    val name: String = "",
    val country: Country = Country(),
    val officialSite: String? = null
)

data class Country(
    val name: String = "",
    val code: String = "",
    val timezone: String = ""
)

data class Externals(
    val imdb: String? = null,
    val thetvdb: Int? = null,
    val tvrage: Int? = null
)

data class Image(
    val medium: String = "",
    val original: String = ""
)

data class Links(
    val self: Link = Link(),
    val previousepisode: Link = Link()
)

data class Link(
    val href: String = ""
)
