package com.rschao.plugins.showdowncore.showdownCore;

import com.rschao.plugins.showdowncore.showdownCore.command.LoreCommands;
import org.bukkit.plugin.java.JavaPlugin;

public final class ShowdownCore extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        LoreCommands.registerCommands();
        getLogger().info("ShowdownCore has been enabled!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static ShowdownCore getInstance() {
        return getPlugin(ShowdownCore.class);
    }
}
