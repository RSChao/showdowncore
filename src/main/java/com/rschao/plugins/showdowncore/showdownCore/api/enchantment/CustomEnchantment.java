// src/main/java/com/rschao/plugins/showdowncore/showdownCore/api/CustomEnchantment.java
package com.rschao.plugins.showdowncore.showdownCore.api.enchantment;

import com.google.gson.*;
import com.rschao.plugins.showdowncore.showdownCore.api.enchantment.registry.EnchantmentRegistry;
import com.rschao.plugins.showdowncore.showdownCore.api.enchantment.util.SaveMode;
import com.rschao.plugins.showdowncore.showdownCore.internal.JsonCopy;
import com.rschao.plugins.showdowncore.showdownCore.internal.JsonSave;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class CustomEnchantment {
    private final File file;
    private JsonObject json;
    private final NamespacedKey key;

    public CustomEnchantment(String namespace, String name) {
        this.file = JsonCopy.getJsonFileCopy(name);
        this.key = new NamespacedKey(namespace, name.replace(".json", ""));
        loadJson();

        clearSupportedItems();
        clearExclusiveSet();
        EnchantmentRegistry.registerEnchantment(this);
    }

    public Enchantment toBukkitEnchantment() {
        return Enchantment.getByKey(key);
    }

    public NamespacedKey getKey() {
        return key;
    }

    public String getEnchantmentNamespace() {
        return key.getNamespace();
    }

    public String getEnchantmentName() {
        return key.getKey();
    }

    public File getFile() {
        return file;
    }

    public JsonObject getJson() {
        return json;
    }

    private void loadJson() {
        try (BufferedReader reader = Files.newBufferedReader(file.toPath(), StandardCharsets.UTF_8)) {
            json = JsonParser.parseReader(reader).getAsJsonObject();
        } catch (Exception e) {
            json = new JsonObject();
        }
    }

    // Ejemplos de métodos modificadores dentro de esta clase
    public void setDescription(String description) {
        json.addProperty("description", description);
    }

    public void setWeight(int weight) {
        json.addProperty("weight", weight);
    }

    public void setMaxLevel(int maxLevel) {
        json.addProperty("max_level", maxLevel);
    }

    public void setAnvilCost(int cost) {
        json.addProperty("anvil_cost", cost);
    }

    public void addSupportedItem(String item) {
        JsonArray arr = json.has("supported_items") ? json.getAsJsonArray("supported_items") : new JsonArray();
        arr.add(item);
        json.add("supported_items", arr);
    }
    public void setSupportedItem(String item) {
        JsonElement e = item == null ? new JsonNull() : new JsonPrimitive(item);
        json.add("supported_items", e);
    }
    public void addExcludedEnchantment(String enchantment) {
        JsonArray arr = json.has("exclusive_set") ? json.getAsJsonArray("exclusive_set") : new JsonArray();
        arr.add(enchantment);
        json.add("exclusive_set", arr);
    }
    public void setExclusiveSet(String ench) {
        JsonElement e = ench == null ? new JsonNull() : new JsonPrimitive(ench);
        json.add("exclusive_set", e);
    }

    public void clearSupportedItems() {
        json.add("supported_items", new JsonArray());
    }

    public void clearExclusiveSet() {
        json.add("exclusive_set", new JsonArray());
    }

    // Guarda el JSON en disco usando JsonSave
    public void save(SaveMode mode) {
        try{
            JsonSave.saveJson(this, mode);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
