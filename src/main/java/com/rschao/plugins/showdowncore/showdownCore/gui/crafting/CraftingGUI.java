package com.rschao.plugins.showdowncore.showdownCore.gui.crafting;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class CraftingGUI {

    public void openCraftingGUI(Player player) {
        Inventory inv = createCraftingGUI(player);
        player.openInventory(inv);
    }

    private Inventory createCraftingGUI(Player player) {

        Inventory inv = Bukkit.createInventory(player, 54, "Showdown Crafting Grid");
        int[] slotsToEmpty = {19, 20, 21, 28, 29, 30, 37, 38, 39, 33};

        for(int i = 0; i < inv.getSize(); i++) {
            boolean shouldEmpty = false;
            for(int slot : slotsToEmpty) {
                if(i == slot) {
                    shouldEmpty = true;
                    break;
                }
            }
            if(!shouldEmpty) {
                inv.setItem(i, Material.GRAY_STAINED_GLASS_PANE.asItemType().createItemStack(1));
            }
        }
        return inv;
    }
}
