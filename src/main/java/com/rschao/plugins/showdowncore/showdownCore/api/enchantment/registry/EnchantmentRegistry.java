package com.rschao.plugins.showdowncore.showdownCore.api.enchantment.registry;

import com.rschao.plugins.showdowncore.showdownCore.api.enchantment.CustomEnchantment;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;

import java.util.HashMap;
import java.util.Map;

public class EnchantmentRegistry {

    private static final Map<NamespacedKey, CustomEnchantment> ENCHANTMENTS = new HashMap<>();

    public static void registerEnchantment(CustomEnchantment enchantment) {
        ENCHANTMENTS.put(enchantment.getKey(), enchantment);
    }

    public static CustomEnchantment getEnchantment(NamespacedKey key) {
        return ENCHANTMENTS.get(key);
    }

    public static CustomEnchantment getEnchantment(String namespace, String name) {
        return ENCHANTMENTS.get(new NamespacedKey(namespace, name));
    }
    public static CustomEnchantment getEnchantment(String key) {

        String[] parts = key.split(":");
        NamespacedKey namespacedKey = new NamespacedKey(parts[0], parts[1]);
        return getEnchantment(namespacedKey);
    }

    public static Enchantment getCustomEnchantment(NamespacedKey key) {
        CustomEnchantment customEnchantment = ENCHANTMENTS.get(key);
        return customEnchantment != null ? customEnchantment.toBukkitEnchantment() : null;
    }

    public static Enchantment getCustomEnchantment(String namespace, String name) {
        CustomEnchantment customEnchantment = ENCHANTMENTS.get(new NamespacedKey(namespace, name));
        return customEnchantment != null ? customEnchantment.toBukkitEnchantment() : null;
    }
}
