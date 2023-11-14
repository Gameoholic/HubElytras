package xyz.gameoholic.hubelytras.listeners

import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityToggleGlideEvent
import xyz.gameoholic.hubelytras.HubElytrasPlugin
import xyz.gameoholic.hubelytras.injection.inject

class EntityToggleGlideListener: Listener {
    private val plugin: HubElytrasPlugin by inject()
    @EventHandler
    fun onEntityToggleGlideEvent(e: EntityToggleGlideEvent) {
        val player = e.entity as? Player ?: return
        if (e.isGliding || !plugin.elytrasManager.playersWithElytra.contains(player.uniqueId)) return

        plugin.elytrasManager.deactivateElytra(player)
    }
}