package xyz.gameoholic.hubelytras.config

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.kyori.adventure.sound.Sound
import xyz.gameoholic.hubelytras.elytras.ElytraSpot
import xyz.gameoholic.hubelytras.serialization.SoundSerializer

@Serializable
data class HubElytrasConfig(
    @SerialName("allow-spot-reuse") val allowSpotReuse: Boolean,
    @SerialName("elytra-activate-message") val elytraActivateMessage: String? = null,
    @SerialName("elytra-deactivate-message") val elytraDeactivateMessage: String? = null,
    @SerialName("elytra-activate-sound") val elytraActivateSound: @Serializable(with = SoundSerializer::class) Sound? = null,
    @SerialName("elytra-deactivate-sound") val elytraDeactivateSound: @Serializable(with = SoundSerializer::class) Sound? = null,
    @SerialName("elytra-spots") val elytraSpots: List<ElytraSpot>
)