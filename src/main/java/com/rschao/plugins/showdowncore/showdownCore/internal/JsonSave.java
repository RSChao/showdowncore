package com.rschao.plugins.showdowncore.showdownCore.internal;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rschao.plugins.showdowncore.showdownCore.api.enchantment.CustomEnchantment;
import com.rschao.plugins.showdowncore.showdownCore.api.enchantment.util.SaveMode;
import org.bukkit.Bukkit;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class JsonSave {
    public static void saveJson(CustomEnchantment e, SaveMode mode) throws IOException {
        File file = e.getFile();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (BufferedWriter writer = Files.newBufferedWriter(file.toPath(), StandardCharsets.UTF_8)) {
            gson.toJson(e.getJson(), writer);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        File ench = new File(Bukkit.getWorlds().get(0).getWorldFolder() + "/datapacks/ssmp/data/" + e.getEnchantmentNamespace() + "/enchantment/" + e.getEnchantmentName() + ".json");
        if(mode == SaveMode.IF_NEW && ench.isFile()) return;
        ench.mkdirs();
        Files.copy(file.toPath(), ench.toPath(), StandardCopyOption.REPLACE_EXISTING);
        Bukkit.getLogger().info("[ShowdownCore] Saving enchantment to file...");
    }
}
