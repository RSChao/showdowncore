package com.rschao.plugins.showdowncore.showdownCore.gui.recipe;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import java.util.Arrays;
import java.util.concurrent.Callable;
import java.util.function.Function;

public class CodeRecipe {
    private final ItemStack[] pattern; // la "entrada"
    private final RecipeAction action; // la acción a ejecutar
    private Function<ItemStack[], Boolean> condition; // condición opcional para ejecutar la acción

    public CodeRecipe(ItemStack[] pattern, RecipeAction action) {
        this.pattern = pattern;
        this.action = action;
    }

    public void setCondition(Function<ItemStack[], Boolean> condition) {
        this.condition = condition;
    }

    public boolean matches(ItemStack[] input) {
        // Implementa la lógica de comparación deseada.
        // Ejemplo simple: Arrays.equals (considera metadatos y amounts)

        if(condition != null) {
            try {
                if (condition.apply(input)) {
                    return true; // Si la condición no se cumple, no coincide
                }
            } catch (Exception e) {
                e.printStackTrace();
                return false; // En caso de error en la condición, no coincide
            }
        }
        for(int i = 0; i < pattern.length; i++) {
            if (!input[i].isSimilar(pattern[i]) ) {
                return false;
            }
        }
        return true;
    }

    public ItemStack getResult()  {
        ItemStack result = action.apply(pattern); // Valor por defecto
        return result;
    }

}
