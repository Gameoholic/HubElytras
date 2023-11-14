package xyz.gameoholic.hubelytras.listeners

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerQuitEvent
import xyz.gameoholic.hubelytras.HubElytrasPlugin
import xyz.gameoholic.hubelytras.injection.inject

class PlayerQuitListener: Listener {
    private val plugin: HubElytrasPlugin by inject()


    @EventHandler
    fun onPlayerQuitEvent(e: PlayerQuitEvent) {
        plugin.elytrasManager.playersWithElytra.remove(e.player.uniqueId)
    }
}