package com.rschao.plugins.showdowncore.showdownCore.api.enchantment.definition;

import com.rschao.plugins.showdowncore.showdownCore.api.enchantment.CustomEnchantment;
import com.rschao.plugins.showdowncore.showdownCore.api.enchantment.registry.EnchantmentRegistry;
import com.rschao.plugins.showdowncore.showdownCore.api.enchantment.util.SaveMode;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.ItemStack;

public abstract class EasyEnchant implements Listener {
    private final NamespacedKey key;
    public EasyEnchant(String key) {
        this.key = NamespacedKey.minecraft(key);
    }

    public CustomEnchantment makeEnchantment(String displayName) {
        CustomEnchantment bukkitEnchant = new CustomEnchantment("minecraft", key.getKey() + ".json");
        bukkitEnchant.setDescription(displayName);
        return bukkitEnchant;
    }

    public void saveBukkitEnchantment(CustomEnchantment e) {
        e.save(SaveMode.IF_NEW);
        EnchantmentRegistry.registerEnchantment(e);
    }

    public boolean hasEnchantment(ItemStack item) {
        return item.hasItemMeta() && item.getItemMeta().hasEnchant(Enchantment.getByKey(key));
    }
    public NamespacedKey getKey() {
        return key;
    }
    @EventHandler
    public void onPlayerDamage(EntityDamageByEntityEvent ev) {
        // Implementación vacía opcional
    }

    @EventHandler
    public void onArrowShoot(EntityShootBowEvent ev) {
        // Implementación vacía opcional
    }
    @EventHandler
    public void onArmorDamage(PlayerItemDamageEvent ev){

    }
}
