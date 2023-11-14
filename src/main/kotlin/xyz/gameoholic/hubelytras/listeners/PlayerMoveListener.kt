package xyz.gameoholic.hubelytras.listeners

import org.bukkit.Material
import org.bukkit.block.BlockFace
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerMoveEvent
import org.bukkit.potion.PotionEffectType
import xyz.gameoholic.hubelytras.HubElytrasPlugin
import xyz.gameoholic.hubelytras.injection.inject

class PlayerMoveListener: Listener {
    private val plugin: HubElytrasPlugin by inject()

    @EventHandler
    fun onEntityMoveEvent(e: PlayerMoveEvent) {
        val player = e.player
        if (!plugin.elytrasManager.playersWithElytra.contains(player.uniqueId)) return
        if (e.to.block.getRelative(BlockFace.DOWN).type == Material.AIR ||
            player.hasPotionEffect(PotionEffectType.LEVITATION)) return

        plugin.elytrasManager.deactivateElytra(player)
    }
}