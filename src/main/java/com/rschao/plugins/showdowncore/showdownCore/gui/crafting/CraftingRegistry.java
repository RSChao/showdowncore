package com.rschao.plugins.showdowncore.showdownCore.gui.crafting;

import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.Map;

public class CraftingRegistry {

    private static final Map<ItemStack[], ItemStack> recipes = new java.util.HashMap<>();

    public static void registerRecipe(ItemStack[] input, ItemStack output) {
        recipes.put(input, output);
        Bukkit.getLogger().info("Registered recipe for " + output.getItemMeta().getItemName());
    }

    public static ItemStack getRecipeOutput(ItemStack[] input) {
        for (Map.Entry<ItemStack[], ItemStack> entry : recipes.entrySet()) {
            if (Arrays.equals(entry.getKey(), input)) {
                return entry.getValue();
            }
        }
        return null;
    }
}
