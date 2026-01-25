package com.rschao.plugins.showdowncore.showdownCore.api.runnables.registry;

import com.rschao.plugins.showdowncore.showdownCore.api.runnables.ShowdownScript;
import org.bukkit.NamespacedKey;

import java.util.Map;

public class ScriptRegistry {
    private static final Map<NamespacedKey, ShowdownScript<?>> SCRIPTS = new java.util.HashMap<>();

    public static void registerScript(NamespacedKey key, ShowdownScript<?> script) {
        SCRIPTS.put(key, script);
    }
    public static void registerScript(String namespace, String name, ShowdownScript<?> script) {
        NamespacedKey key = new NamespacedKey(namespace, name);
        SCRIPTS.put(key, script);
    }
    public static void registerScript(String key, ShowdownScript<?> item) {
        String[] parts = key.split(":");
        NamespacedKey namespacedKey = new NamespacedKey(parts[0], parts[1]);
        SCRIPTS.put(namespacedKey, item);
    }



    public static ShowdownScript<?> getScript(NamespacedKey key) {
        return SCRIPTS.get(key);
    }
    public static ShowdownScript<?> getScript(String namespace, String name) {
        NamespacedKey key = new NamespacedKey(namespace, name);
        return SCRIPTS.get(key);
    }
    public static ShowdownScript<?> getScript(String key) {
        String[] parts = key.split(":");
        NamespacedKey namespacedKey = new NamespacedKey(parts[0], parts[1]);
        return SCRIPTS.get(namespacedKey);
    }
}
