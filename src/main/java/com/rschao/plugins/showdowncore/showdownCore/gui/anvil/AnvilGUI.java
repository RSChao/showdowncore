package com.rschao.plugins.showdowncore.showdownCore.gui.anvil;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class AnvilGUI {


    public void openAnvilGUI(Player player) {
        Inventory inv = createAnvilGUI(player);
        player.openInventory(inv);
    }

    private Inventory createAnvilGUI(Player player) {

        Inventory inv = Bukkit.createInventory(player, 54, "Showdown Anvil");
        int[] slotsToEmpty = { 28, 30, 33};

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
