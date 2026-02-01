package com.rschao.plugins.showdowncore.showdownCore.api.enchantment;

import com.rschao.plugins.showdowncore.showdownCore.ShowdownCore;
import com.rschao.plugins.showdowncore.showdownCore.api.enchantment.definition.EasyEnchant;

public  class EasyEnchantManager{
    public static void addEasyEnchant(EasyEnchant enchant) {
        ShowdownCore.getPlugin(ShowdownCore.class).getServer().getPluginManager().registerEvents(enchant, ShowdownCore.getPlugin(ShowdownCore.class));
    }
}