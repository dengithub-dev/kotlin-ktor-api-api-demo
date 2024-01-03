package den.model

import kotlinx.serialization.Serializable

@Serializable
data class GetDataPhilippines(
    val startOfWeek: String,
    val flag: String
)

@Serializable
data class ClientData(
    val id: Int,
    val uid: String,
    val password: String,
    val address: AddressData
)

@Serializable
data class AddressData (
    val city: String,
    val state: String,
    val country: String
)
@Serializable
data class Sample(
    val a: String,
    val b: Int,
    val c: Int
)
