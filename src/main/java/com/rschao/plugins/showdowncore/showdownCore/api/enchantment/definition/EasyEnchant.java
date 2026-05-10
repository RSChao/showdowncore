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

import java.io.IOException;

public abstract class EasyEnchant implements Listener {
    private final NamespacedKey key;
    private CustomEnchantment customEnchantment;
    public EasyEnchant(String key) {
        this.key = NamespacedKey.minecraft(key);
        this.customEnchantment = new CustomEnchantment("minecraft", key + ".json");
    }
    public EasyEnchant(String key, String display) {
        this.key = NamespacedKey.minecraft(key);
        this.customEnchantment = makeEnchantment(display);
        saveBukkitEnchantment(customEnchantment);
    }
    public EasyEnchant(String key, String namespace, String display) {
        this.key = NamespacedKey.minecraft(key);
        this.customEnchantment = makeEnchantment(namespace, display);
        saveBukkitEnchantment(customEnchantment);
    }

    public CustomEnchantment makeEnchantment(String displayName) {
        CustomEnchantment bukkitEnchant = new CustomEnchantment("minecraft", key.getKey() + ".json");
        bukkitEnchant.setDescription(displayName);
        this.customEnchantment = bukkitEnchant;
        return bukkitEnchant;
    }

    public CustomEnchantment makeEnchantment(String namespace, String displayName) {
        CustomEnchantment bukkitEnchant = new CustomEnchantment(namespace, key.getKey() + ".json");
        bukkitEnchant.setDescription(displayName);
        this.customEnchantment = bukkitEnchant;
        return bukkitEnchant;
    }

    public void saveBukkitEnchantment(CustomEnchantment e) {
        try{
            e.save(SaveMode.IF_NEW);
        } catch (Exception ex) {

        }
        this.customEnchantment = e;
        EnchantmentRegistry.registerEnchantment(e);
    }
    @Deprecated
    public void setCustomEnchantment(CustomEnchantment customEnchantment) {
        this.customEnchantment = customEnchantment;
    }

    public CustomEnchantment getCustomEnchantment() {
        return customEnchantment;
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
