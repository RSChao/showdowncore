package com.rschao.plugins.showdowncore.showdownCore.api.items.registry;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class ItemRegistry {
    private static final Map<NamespacedKey, ItemStack> items = new HashMap<>();

    public static void registerItem(NamespacedKey key, ItemStack item) {
        items.put(key, item);
    }
    public static void registerItem(String namespace, String name, ItemStack item) {
        NamespacedKey key = new NamespacedKey(namespace, name);
        items.put(key, item);
    }
    public static void registerItem(String key, ItemStack item) {
        String[] parts = key.split(":");
        NamespacedKey namespacedKey = new NamespacedKey(parts[0], parts[1]);
        items.put(namespacedKey, item);
    }



    public static ItemStack getItem(NamespacedKey key) {
        return items.get(key);
    }
    public static ItemStack getItem(String namespace, String name) {
        NamespacedKey key = new NamespacedKey(namespace, name);
        return items.get(key);
    }
    public static ItemStack getItem(String key) {
        String[] parts = key.split(":");
        NamespacedKey namespacedKey = new NamespacedKey(parts[0], parts[1]);
        return items.get(namespacedKey);
    }
}
