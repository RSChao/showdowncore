package com.rschao.plugins.showdowncore.showdownCore.gui.recipe;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface RecipeAction {
    /**
     * Ejecuta la acción de receta.
     * @param inputs arreglo con los ItemStack usados en la receta (copias)
     * @return ItemStack resultante a mostrar en el slot de output (puede ser AIR)
     */
    ItemStack apply(ItemStack[] inputs);
}
