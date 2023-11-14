package xyz.gameoholic.hubelytras.elytras

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.bukkit.Location
import xyz.gameoholic.hubelytras.serialization.LocationSerializer

@Serializable
data class ElytraSpot(
    val location: @Serializable(with = LocationSerializer::class) Location,
    @SerialName("detection-y-level") val detectionYLevel: Double? = null,
    @SerialName("levitation-duration") val levitationDuration: Int,
    @SerialName("levitation-amplifier") val levitationAmplifier: Int,
    @SerialName("radius") val radius: Int
)
