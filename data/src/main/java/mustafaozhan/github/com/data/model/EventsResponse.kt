package mustafaozhan.github.com.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EventsResponse(
    @Json(name = "_embedded") val embedded: Embedded,
    @Json(name = "_links") val links: Links,
    @Json(name = "page") val page: Page
)

@JsonClass(generateAdapter = true)
data class Embedded(
    @Json(name = "events") val events: List<Event>
)

@JsonClass(generateAdapter = true)
data class Event(
    @Json(name = "name") val name: String,
    @Json(name = "type") val type: String,
    @Json(name = "id") val id: String,
    @Json(name = "test") val test: Boolean,
    @Json(name = "url") val url: String,
    @Json(name = "locale") val locale: String,
    @Json(name = "images") val images: List<Image>,
    @Json(name = "sales") val sales: Sales,
    @Json(name = "dates") val dates: Dates,
    @Json(name = "classifications") val classifications: List<Classification>,
    @Json(name = "promoter") val promoter: Promoter,
    @Json(name = "promoters") val promoters: List<Promoter>,
    @Json(name = "info") val info: String,
    @Json(name = "pleaseNote") val pleaseNote: String? = null,
    @Json(name = "priceRanges") val priceRanges: List<PriceRange>,
    @Json(name = "products") val products: List<Product>? = null,
    @Json(name = "seatmap") val seatmap: Seatmap,
    @Json(name = "accessibility") val accessibility: Accessibility,
    @Json(name = "ticketLimit") val ticketLimit: TicketLimit? = null,
    @Json(name = "ageRestrictions") val ageRestrictions: AgeRestrictions,

    @Transient val links: EventLinks? = null,
    @Transient val embedded: EventEmbedded? = null
)

@JsonClass(generateAdapter = true)
data class Accessibility(
    @Transient
    val ticketLimit: Long? = null
)

@JsonClass(generateAdapter = true)
data class AgeRestrictions(
    @Transient
    val legalAgeEnforced: Boolean? = null
)

@JsonClass(generateAdapter = true)
data class Classification(
    @Transient val primary: Boolean? = null,
    @Transient val segment: Genre? = null,
    @Transient val genre: Genre? = null,
    @Transient val subGenre: Genre? = null,
    @Transient val type: Genre? = null,
    @Transient val subType: Genre? = null,
    @Transient val family: Boolean? = null
)

@JsonClass(generateAdapter = true)
data class Genre(
    @Transient val id: String? = null,
    @Transient val name: String? = null
)

@JsonClass(generateAdapter = true)
data class Dates(
    @Transient val start: Start? = null,
    @Transient val timezone: String? = null,
    @Transient val status: Status? = null,
    @Transient val spanMultipleDays: Boolean? = null
)

@JsonClass(generateAdapter = true)
data class Start(
    @Transient val localDate: String? = null,
    @Transient val localTime: String? = null,
    @Transient val dateTime: String? = null,
    @Transient val dateTBD: Boolean? = null,
    @Transient val dateTBA: Boolean? = null,
    @Transient val timeTBA: Boolean? = null,
    @Transient val noSpecificTime: Boolean? = null
)

@JsonClass(generateAdapter = true)
data class Status(
    @Transient
    val code: String? = null
)

@JsonClass(generateAdapter = true)
data class EventEmbedded(
    @Transient val venues: List<Venue>? = null,
    @Transient val attractions: List<Attraction>? = null
)

@JsonClass(generateAdapter = true)
data class Attraction(
    @Transient val name: String? = null,
    @Transient val type: String? = null,
    @Transient val id: String? = null,
    @Transient val test: Boolean? = null,
    @Transient val url: String? = null,
    @Transient val locale: String? = null,
    @Transient val images: List<Image>? = null,
    @Transient val classifications: List<Classification>? = null,
    @Transient val upcomingEvents: AttractionUpcomingEvents? = null,

    @Json(name = "_links")
    val links: AttractionLinks
)

@JsonClass(generateAdapter = true)
data class Image(
    @Transient val ratio: Ratio? = null,
    @Transient val url: String? = null,
    @Transient val width: Long? = null,
    @Transient val height: Long? = null,
    @Transient val fallback: Boolean? = null,
    @Transient val attribution: String? = null
)

enum class Ratio(val value: String) {
    The16_9("16_9"),
    The3_2("3_2"),
    The4_3("4_3");

    companion object {
        fun fromValue(value: String): Ratio = when (value) {
            "16_9" -> The16_9
            "3_2" -> The3_2
            "4_3" -> The4_3
            else -> throw IllegalArgumentException()
        }
    }
}

@JsonClass(generateAdapter = true)
data class AttractionLinks(
    @Transient
    val self: First? = null
)

@JsonClass(generateAdapter = true)
data class First(
    @Transient
    val href: String? = null
)

@JsonClass(generateAdapter = true)
data class AttractionUpcomingEvents(
    @Json(name = "_total")
    val total: Long,

    @Json(name = "mfx-de")
    val mfxDe: Long,

    @Transient
    val ticketmaster: Long? = null
)

@JsonClass(generateAdapter = true)
data class Venue(
    @Transient val name: String? = null,
    @Transient val type: String? = null,
    @Transient val id: String? = null,
    @Transient val test: Boolean? = null,
    @Transient val url: String? = null,
    @Transient val locale: String? = null,
    @Transient val images: List<Image>? = null,
    @Transient val postalCode: String? = null,
    @Transient val timezone: String? = null,
    @Transient val city: City? = null,
    @Transient val state: State? = null,
    @Transient val country: Country? = null,
    @Transient val address: Address? = null,
    @Transient val location: Location? = null,
    @Transient val markets: List<Genre>? = null,
    @Transient val dmas: List<DMA>? = null,
    @Transient val social: Social? = null,
    @Transient val boxOfficeInfo: BoxOfficeInfo? = null,
    @Transient val parkingDetail: String? = null,
    @Transient val generalInfo: GeneralInfo? = null,
    @Transient val upcomingEvents: VenueUpcomingEvents? = null,

    @Json(name = "_links")
    val links: AttractionLinks
)

@JsonClass(generateAdapter = true)
data class Address(
    @Transient
    val line1: String? = null
)

@JsonClass(generateAdapter = true)
data class BoxOfficeInfo(
    @Transient val phoneNumberDetail: String? = null,
    @Transient val openHoursDetail: String? = null,
    @Transient val acceptedPaymentDetail: String? = null,
    @Transient val willCallDetail: String? = null
)

@JsonClass(generateAdapter = true)
data class City(
    @Transient
    val name: String? = null
)

@JsonClass(generateAdapter = true)
data class Country(
    @Transient
    val name: String? = null,
    @Transient
    val countryCode: String? = null
)

@JsonClass(generateAdapter = true)
data class DMA(
    @Transient
    val id: Long? = null
)

@JsonClass(generateAdapter = true)
data class GeneralInfo(
    @Transient
    val generalRule: String? = null,
    @Transient
    val childRule: String? = null
)

@JsonClass(generateAdapter = true)
data class Location(
    @Transient
    val longitude: String? = null,
    @Transient
    val latitude: String? = null
)

@JsonClass(generateAdapter = true)
data class Social(
    @Transient
    val twitter: Twitter? = null
)

@JsonClass(generateAdapter = true)
data class Twitter(
    @Transient
    val handle: String? = null
)

@JsonClass(generateAdapter = true)
data class State(
    @Transient
    val name: String? = null,
    @Transient
    val stateCode: String? = null
)

@JsonClass(generateAdapter = true)
data class VenueUpcomingEvents(
    @Json(name = "_total")
    val total: Long,

    @Transient
    val ticketmaster: Long? = null
)

@JsonClass(generateAdapter = true)
data class EventLinks(
    @Transient val self: First? = null,
    @Transient val attractions: List<First>? = null,
    @Transient val venues: List<First>? = null
)

@JsonClass(generateAdapter = true)
data class PriceRange(
    @Transient val type: String? = null,
    @Transient val currency: String? = null,
    @Transient val min: Long? = null,
    @Transient val max: Long? = null
)

@JsonClass(generateAdapter = true)
data class Product(
    @Transient val name: String? = null,
    @Transient val id: String? = null,
    @Transient val url: String? = null,
    @Transient val type: String? = null,
    @Transient val classifications: List<Classification>? = null
)

@JsonClass(generateAdapter = true)
data class Promoter(
    @Transient val id: String? = null,
    @Transient val name: String? = null,
    @Transient val description: String? = null
)

@JsonClass(generateAdapter = true)
data class Sales(
    @Transient val public: Public? = null,
    @Transient val presales: List<Presale>? = null
)

@JsonClass(generateAdapter = true)
data class Presale(
    @Transient val startDateTime: String? = null,
    @Transient val endDateTime: String? = null,
    @Transient val name: String? = null
)

@JsonClass(generateAdapter = true)
data class Public(
    @Transient val startDateTime: String? = null,
    @Transient val startTBD: Boolean? = null,
    @Transient val startTBA: Boolean? = null,
    @Transient val endDateTime: String? = null
)

@JsonClass(generateAdapter = true)
data class Seatmap(
    @Json(name = "staticUrl")
    val staticURL: String
)

@JsonClass(generateAdapter = true)
data class TicketLimit(
    @Transient
    val info: String? = null
)

@JsonClass(generateAdapter = true)
data class Links(
    @Transient val first: First? = null,
    @Transient val self: First? = null,
    @Transient val next: First? = null,
    @Transient val last: First? = null
)

@JsonClass(generateAdapter = true)
data class Page(
    @Json(name = "size") val size: Long,
    @Json(name = "totalElements") val totalElements: Long,
    @Json(name = "totalPages") val totalPages: Long,
    @Json(name = "number") val number: Long
)