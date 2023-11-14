package xyz.gameoholic.hubelytras.elytras

import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import org.bukkit.scheduler.BukkitRunnable
import xyz.gameoholic.hubelytras.HubElytrasPlugin
import xyz.gameoholic.hubelytras.injection.inject
import xyz.gameoholic.hubelytras.particle.HubElytraParticle
import java.util.UUID

class HubElytrasManager {
    var playersWithElytra = mutableListOf<UUID>()

    val plugin: HubElytrasPlugin by inject()

    fun onEnable() {
        object : BukkitRunnable() {
            override fun run() {
                for (player in Bukkit.getOnlinePlayers()) {
                    if (playersWithElytra.any { player.uniqueId == it } && !plugin.config.allowSpotReuse) continue

                    val elytraSpot = plugin.config.elytraSpots.firstOrNull {
                        var y = it.location.y
                        it.detectionYLevel?.let { detectionY -> y = detectionY } // Prioritize detectionYLevel if explicitly provided

                        player.location.x > it.location.x - it.radius && player.location.x < it.location.x + it.radius &&
                            player.location.z > it.location.z - it.radius && player.location.z < it.location.z + it.radius &&
                            player.location.y == y
                    } ?: continue

                    activateElytra(player, elytraSpot)
                }
            }
        }.runTaskTimer(plugin, 0L, 1L)

        // Delay particle start until Partigon has fully loaded
        object : BukkitRunnable() {
            override fun run() {
                plugin.config.elytraSpots.forEach {
                    HubElytraParticle.startParticle(it.location)
                }
            }
        }.runTask(plugin)
    }

    fun activateElytra(player: Player, elytraSpot: ElytraSpot) {
        player.addPotionEffect(
            PotionEffect(
                PotionEffectType.LEVITATION,
                elytraSpot.levitationDuration,
                elytraSpot.levitationAmplifier,
                false,
                false
            )
        )
        player.inventory.chestplate = ItemStack(Material.ELYTRA, 1)
        plugin.config.elytraActivateMessage?.let {
            player.sendActionBar(MiniMessage.miniMessage().deserialize(it))
        }
        plugin.config.elytraActivateSound?.let {
            player.playSound(it)
        }
        if (!playersWithElytra.contains(player.uniqueId))
            playersWithElytra += player.uniqueId
    }

    fun deactivateElytra(player: Player) {
        player.inventory.chestplate = null
        playersWithElytra.remove(player.uniqueId)
        plugin.config.elytraDeactivateMessage?.let {
            player.sendActionBar(MiniMessage.miniMessage().deserialize(it))
        }
        plugin.config.elytraDeactivateSound?.let {
            player.playSound(it)
        }
    }
}