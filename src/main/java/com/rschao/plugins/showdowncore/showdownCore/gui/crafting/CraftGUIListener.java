package com.rschao.plugins.showdowncore.showdownCore.gui.crafting;

import com.rschao.plugins.showdowncore.showdownCore.ShowdownCore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class CraftGUIListener implements Listener {

    @EventHandler
    public void onCraftGUIClick(InventoryClickEvent event) {
        Inventory inv = event.getInventory();
        if(!event.getView().getTitle().equals("Showdown Crafting Grid")) return;
        if(event.getCurrentItem() != null && event.getCurrentItem().getType() == Material.GRAY_STAINED_GLASS_PANE) {
            event.setCancelled(true);
            return;
        }

        inv.setItem(33, checkCraftingRecipe(inv));

        if(event.getRawSlot() == 33) {
            if(checkCraftingRecipe(inv).getType() != Material.AIR) {
                for(int slot : new int[]{19, 20, 21, 28, 29, 30, 37, 38, 39}) {
                    ItemStack item = inv.getItem(slot);
                    if(item != null && item.getType() != Material.AIR) {
                        item.setAmount(item.getAmount() - 1);
                        inv.setItem(slot, item);
                    }
                }
            }
        }
    }
    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        Inventory inv = event.getInventory();
        if(!event.getView().getTitle().equals("Showdown Crafting Grid")) return;
        for(int slot : new int[]{19, 20, 21, 28, 29, 30, 37, 38, 39}) {
            ItemStack item = inv.getItem(slot);
            if(item != null && item.getType() != Material.AIR) {
                event.getPlayer().getInventory().addItem(item);
            }
        }
    }

    @EventHandler
    void onInventoryDrag(InventoryDragEvent event) {
        if(!event.getView().getTitle().equals("Showdown Crafting Grid")) return;
        event.setCancelled(true);
        event.getWhoClicked().sendMessage(ChatColor.RED + "Dragging items in the crafting grid is not allowed!");
    }




    public ItemStack checkCraftingRecipe(Inventory inv) {
        ItemStack[] craftingGrid = new ItemStack[9];
        int[] craftingSlots = {19, 20, 21, 28, 29, 30, 37, 38, 39};
        for(int i = 0; i < craftingSlots.length; i++) {
            craftingGrid[i] = inv.getItem(craftingSlots[i]);
        }
        ItemStack output = CraftingRegistry.getRecipeOutput(craftingGrid);
        if(output != null) {
            return output;
        }
        return Material.AIR.asItemType().createItemStack();
    }
}
