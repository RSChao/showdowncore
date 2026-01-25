package com.rschao.plugins.showdowncore.showdownCore;

import com.rschao.plugins.showdowncore.showdownCore.api.enchantment.CustomEnchantment;
import com.rschao.plugins.showdowncore.showdownCore.api.enchantment.util.ColorCodes;
import com.rschao.plugins.showdowncore.showdownCore.api.enchantment.util.SaveMode;
import org.bukkit.plugin.java.JavaPlugin;

public final class ShowdownCore extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("ShowdownCore has been enabled!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
