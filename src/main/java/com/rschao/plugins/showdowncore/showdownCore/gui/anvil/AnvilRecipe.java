package com.rschao.plugins.showdowncore.showdownCore.gui.anvil;

import com.rschao.plugins.showdowncore.showdownCore.gui.recipe.CodeRecipe;
import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;

public class AnvilRecipe {
    private static final Map<ItemStack[], ItemStack> recipes = new java.util.HashMap<>();
    private static final Set<CodeRecipe> codeRecipes = new java.util.HashSet<>();

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

    public static void registerCodeRecipe(CodeRecipe recipe) {
        codeRecipes.add(recipe);
        Bukkit.getLogger().info("Registered code recipe for action ");
    }

    public static CodeRecipe getCodeRecipe(ItemStack[] input) {
        for (CodeRecipe recipe : codeRecipes) {
            if (recipe.matches(input)) {
                return recipe;
            }
        }
        return null;
    }
}
