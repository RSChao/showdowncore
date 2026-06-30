package com.rschao.plugins.showdowncore.showdownCore.gui.anvil;

import com.rschao.plugins.showdowncore.showdownCore.ShowdownCore;
import com.rschao.plugins.showdowncore.showdownCore.gui.crafting.CraftingRegistry;
import com.rschao.plugins.showdowncore.showdownCore.gui.recipe.CodeRecipe;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class AnvilGUIListener implements Listener {

    @EventHandler
    public void onCraftGUIClick(InventoryClickEvent event) {
        Inventory inv = event.getInventory();
        if(!event.getView().getTitle().equals("Showdown Anvil")) return;
        if(event.getCurrentItem() != null && event.getCurrentItem().getType() == Material.GRAY_STAINED_GLASS_PANE) {
            event.setCancelled(true);
            return;
        }

        inv.setItem(33, checkCraftingRecipe(inv));

        if(event.getRawSlot() == 33) {
            if(checkCraftingRecipe(inv).getType() != Material.AIR) {
                for(int slot : new int[]{28, 30, 33}) {
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
        if(!event.getView().getTitle().equals("Showdown Anvil")) return;
        for(int slot : new int[]{28, 30, 33}) {
            ItemStack item = inv.getItem(slot);
            if(item != null && item.getType() != Material.AIR) {
                event.getPlayer().getInventory().addItem(item);
            }
        }
    }

    @EventHandler
    void onInventoryDrag(InventoryDragEvent event) {
        if(!event.getView().getTitle().equals("Showdown Anvil")) return;
        event.setCancelled(true);
        event.getWhoClicked().sendMessage(ChatColor.RED + "Dragging items is not allowed!");
    }




    public ItemStack checkCraftingRecipe(Inventory inv) {
        ItemStack[] craftingGrid = new ItemStack[3];
        int[] craftingSlots = {28, 30, 33};
        for(int i = 0; i < craftingSlots.length; i++) {
            craftingGrid[i] = inv.getItem(craftingSlots[i]);
        }
        if(craftingGrid[0] == null) craftingGrid[0] = Material.AIR.asItemType().createItemStack();
        if(craftingGrid[1] == null) craftingGrid[1] = Material.AIR.asItemType().createItemStack();
        ItemStack output = AnvilRecipe.getRecipeOutput(craftingGrid);
        if(output != null) {
            return output;
        }
        CodeRecipe output2 = AnvilRecipe.getCodeRecipe(craftingGrid);
        if(output2 != null) {
            Bukkit.getLogger().info("Found code recipe for anvil crafting!");
            if(output2.matches(craftingGrid)){
                return output2.getResult();
            }
        }

        return Material.AIR.asItemType().createItemStack();
    }
}
