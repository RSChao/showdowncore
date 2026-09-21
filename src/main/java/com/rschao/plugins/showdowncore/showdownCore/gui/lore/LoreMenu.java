package com.rschao.plugins.showdowncore.showdownCore.gui.lore;

import com.rschao.plugins.showdowncore.showdownCore.gui.lore.file.LoreFileManager;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import xyz.xenondevs.invui.gui.Gui;
import xyz.xenondevs.invui.item.Item;
import xyz.xenondevs.invui.window.Window;

import java.util.ArrayList;
import java.util.List;

public class LoreMenu {

    static Item getLocationItem(Player p, String campaign) {
        ItemStack itemStack = new ItemStack(Material.COMPASS);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(PlaceholderAPI.setPlaceholders(p, LoreFileManager.getCampaignStartLocText(campaign).replace("&", "§")));
        itemStack.setItemMeta(itemMeta);
        return Item.builder().setItemProvider(itemStack).addClickHandler((click -> {
            click.player().teleport(LoreFileManager.getCampaignStartLoc(campaign));
        })).build();
    }

    static Item getBossesItem(Player p, String campaign) {
        ItemStack itemStack = new ItemStack(Material.BLAZE_SPAWN_EGG);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(PlaceholderAPI.setPlaceholders(p, "§6Bosses"));
        List<String> lore = new ArrayList<>();
        for (String line : LoreFileManager.getCampaignBosses(campaign).split("\n")) {
            lore.add(PlaceholderAPI.setPlaceholders(p, ChatColor.BOLD + line));
        }
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        return Item.builder().setItemProvider(itemStack).build();
    }

    static Item getReleaseDateItem(Player p, String campaign) {
        ItemStack itemStack = new ItemStack(Material.CLOCK);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(PlaceholderAPI.setPlaceholders(p, "§6Release Date"));
        List<String> lore = new ArrayList<>();
        lore.add(PlaceholderAPI.setPlaceholders(p, ChatColor.BOLD + LoreFileManager.getCampaignReleaseDate(campaign)));
        lore.add(("dd-mm-yyyy"));
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        return Item.builder().setItemProvider(itemStack).build();
    }

    static Item getDifficultyItem(Player p, String campaign) {
        ItemStack itemStack = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(PlaceholderAPI.setPlaceholders(p, "§6Difficulty"));
        List<String> lore = new ArrayList<>();
        for (String line : LoreFileManager.getCampaignDifficulty(campaign).split("\n")) {
            lore.add(PlaceholderAPI.setPlaceholders(p, LoreFileManager.getDifficultyColor(LoreFileManager.getCampaignDifficulty(campaign, (lore.isEmpty() ? "bosses" : "hints"))) + line));
        }
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        return Item.builder().setItemProvider(itemStack).build();
    }

    static Item getDifficultyGuideItem(Player p) {
        ItemStack itemStack = new ItemStack(Material.BOOK);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(PlaceholderAPI.setPlaceholders(p, "§6Difficulty Guide"));
        List<String> lore = new ArrayList<>();
        for (String line : LoreFileManager.getCampaignDifficultyGuide()) {
            lore.add(PlaceholderAPI.setPlaceholders(p, ChatColor.BOLD + line));
        }
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        return Item.builder().setItemProvider(itemStack).build();
    }
    static Item getFillerItem(Player p) {
        ItemStack itemStack = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(PlaceholderAPI.setPlaceholders(p, "§6Filler"));
        List<String> lore = new ArrayList<>();
        lore.add(PlaceholderAPI.setPlaceholders(p, "§cThis is a filler item."));
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        return Item.builder().setItemProvider(itemStack).build();
    }

    static Item getChallengeTranslatedItem(Player p, String campaign) {
        ItemStack itemStack = new ItemStack(Material.ENCHANTED_BOOK);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(PlaceholderAPI.setPlaceholders(p, "§6Challenge / Translated"));
        List<String> lore = new ArrayList<>();
        if (LoreFileManager.isCampaignChallenge(campaign)) {
            lore.add(PlaceholderAPI.setPlaceholders(p, "§cThis campaign is a challenge campaign."));
        } else {
            lore.add(PlaceholderAPI.setPlaceholders(p, "§aThis campaign is not a challenge campaign."));
        }
        if (LoreFileManager.isCampaignTranslated(campaign)) {
            lore.add(PlaceholderAPI.setPlaceholders(p, "§aThis campaign is translated to both languages."));
        } else {
            lore.add(PlaceholderAPI.setPlaceholders(p, "§cThis campaign is not translated to both languages."));
        }
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        return Item.builder().setItemProvider(itemStack).build();
    }

    public static void getCampaignMenu(Player p, String campaign) {
        ItemStack itemStack = new ItemStack(Material.PAPER);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(PlaceholderAPI.setPlaceholders(p, LoreFileManager.getCampaignName(campaign).replace("&", "§")));
        List<String> lore = new ArrayList<>();
        for (String line : LoreFileManager.getCampaignDescription(campaign)) {
            lore.add(PlaceholderAPI.setPlaceholders(p, line).replace("&", "§"));
        }
        itemMeta.setLore(lore);
        itemMeta.setItemModel(NamespacedKey.minecraft(LoreFileManager.getItemModel(campaign)));
        itemStack.setItemMeta(itemMeta);

        Item i_info = Item.builder().setItemProvider(itemStack).build();
        Item i_location = getLocationItem(p, campaign);
        Item i_bosses = getBossesItem(p, campaign);
        Item i_releaseDate = getReleaseDateItem(p, campaign);
        Item i_difficulty = getDifficultyItem(p, campaign);
        Item i_difficultyGuide = getDifficultyGuideItem(p);
        Item i_challengeTranslated = getChallengeTranslatedItem(p, campaign);
        Item i_filler = getFillerItem(p);

        Gui gui = Gui.builder().setStructure(
                "* * * * I * * * *",
                "* x x x x x x x *",
                "* x L x B x D x *",
                "* x R x x x G x *",
                "* x x x E x x x *",
                "* * * * * * * * *")
                .addIngredient('I', i_info)
                .addIngredient('L', i_location)
                .addIngredient('B', i_bosses)
                .addIngredient('R', i_releaseDate)
                .addIngredient('D', i_difficulty)
                .addIngredient('G', i_difficultyGuide)
                .addIngredient('E', i_challengeTranslated)
                .addIngredient('*', i_filler)
        .build();
        Window window = Window.builder().setUpperGui(gui).setTitle(PlaceholderAPI.setPlaceholders(p, LoreFileManager.getCampaignName(campaign).replace("&", "§"))).setViewer(p).build();
        window.open();
    }
}
