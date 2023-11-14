package xyz.gameoholic.hubelytras

import com.charleskorn.kaml.Yaml
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitRunnable
import xyz.gameoholic.hubelytras.config.HubElytrasConfig
import xyz.gameoholic.hubelytras.elytras.HubElytrasManager
import xyz.gameoholic.hubelytras.injection.bind
import xyz.gameoholic.hubelytras.listeners.EntityToggleGlideListener
import xyz.gameoholic.hubelytras.listeners.PlayerMoveListener
import xyz.gameoholic.hubelytras.listeners.PlayerQuitListener
import java.nio.file.Files
import kotlin.io.path.Path

class HubElytrasPlugin : JavaPlugin() {

    lateinit var config: HubElytrasConfig
    lateinit var elytrasManager: HubElytrasManager

    override fun onEnable() {
        bind()

        saveResource("config.yml", false)
        config = Yaml.default.decodeFromString(
            HubElytrasConfig.serializer(),
            Files.readString(Path("$dataFolder/config.yml"))
        )

        Bukkit.getPluginManager().registerEvents(PlayerQuitListener(), this)
        Bukkit.getPluginManager().registerEvents(EntityToggleGlideListener(), this)
        Bukkit.getPluginManager().registerEvents(PlayerMoveListener(), this)

        elytrasManager = HubElytrasManager()
        elytrasManager.onEnable()

    }
}