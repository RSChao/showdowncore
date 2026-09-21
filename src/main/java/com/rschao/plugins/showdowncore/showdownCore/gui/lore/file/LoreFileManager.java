package com.rschao.plugins.showdowncore.showdownCore.gui.lore.file;

import com.rschao.plugins.showdowncore.showdownCore.ShowdownCore;
import com.rschao.plugins.showdowncore.showdownCore.command.LoreCommands;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.util.List;

public class LoreFileManager {

    public static File loadLoreFile(String campaign) {
        return new File(ShowdownCore.getInstance().getDataFolder() + "/lore/campaigns/" + campaign + ".yml");
    }


    public static FileConfiguration getLoreFileConfiguration(String campaign) {
        return YamlConfiguration.loadConfiguration(loadLoreFile(campaign));
    }

    public static String getItemModel(String campaign) {
        return getLoreFileConfiguration(campaign).getString("lore." + campaign + ".item_model");
    }

    public static String getCampaignName(String campaign) {
        return getLoreFileConfiguration(campaign).getString("lore." + campaign + ".name");
    }

    public static String getCampaignStartLocText(String campaign) {
        return getLoreFileConfiguration(campaign).getString("lore." + campaign + ".location.text");
    }

    public static List<String> getCampaignDescription(String campaign) {
        return getLoreFileConfiguration(campaign).getStringList("lore." + campaign + ".description");
    }

    public static String getCampaignReleaseDate(String campaign) {
        return getLoreFileConfiguration(campaign).getString("lore." + campaign + ".release_date");
    }

    public static Location getCampaignStartLoc(String campaign) {
        int x = getLoreFileConfiguration(campaign).getInt("lore." + campaign + ".location.x");
        int y = getLoreFileConfiguration(campaign).getInt("lore." + campaign + ".location.y");
        int z = getLoreFileConfiguration(campaign).getInt("lore." + campaign + ".location.z");
        String worldName = getLoreFileConfiguration(campaign).getString("lore." + campaign + ".location.world");
        return new Location(ShowdownCore.getInstance().getServer().getWorld(worldName), x, y, z);
    }

    public static String getCampaignBosses(String campaign) {
        return getLoreFileConfiguration(campaign).getString("lore." + campaign + ".bosses.total") + " bosses\n" + getLoreFileConfiguration(campaign).getString("lore." + campaign + ".bosses.multiphase") + " multiphase\n" +
                getLoreFileConfiguration(campaign).getString("lore." + campaign + ".bosses.multiphase_above3") + " above 3 phases";
    }

    public static String getCampaignDifficulty(String campaign) {
        return "Bosses: " + getLoreFileConfiguration(campaign).getString("lore." + campaign + ".difficulty.bosses") + "\nHints: " + getLoreFileConfiguration(campaign).getString("lore." + campaign + ".difficulty.hints");
    }

    public static String getCampaignDifficulty(String campaign, String type) {
        return getLoreFileConfiguration(campaign).getString("lore." + campaign + ".difficulty." + type);
    }

    public static List<ItemStack> getCampaignItems(String campaign) {
        return (List<ItemStack>) getLoreFileConfiguration(campaign).get("lore." + campaign + ".key_items");
    }

    public static List<String> getCampaigns() {
        return List.of(LoreCommands.getCampaigns());
    }

    public static boolean isCampaignChallenge(String campaign) {
        return getLoreFileConfiguration(campaign).getBoolean("lore." + campaign + ".challenge");
    }

    public static boolean isCampaignTranslated(String campaign) {
        return getLoreFileConfiguration(campaign).getBoolean("lore." + campaign + ".translated");
    }


    public static List<String> getCampaignDifficultyGuide() {
        return List.of("Difficulty Guide:\n",
                ChatColor.GRAY + "E. No bosses, obvious hints.\n",
                ChatColor.AQUA + "D. Few and easy bosses, very easy hints.\n",
                ChatColor.GREEN + "C. Easy bosses (prot5 level), easy but not obvious hints.\n",
                ChatColor.RED + "B. Decent bosses but no multiphase, hints are no longer obvious.\n",
                ChatColor.YELLOW + "A. Might struggle once or twice, hints get harder.\n",
                ChatColor.DARK_PURPLE + "S. Demands powerful armor and C-hearts, hints are very hard but fair.\n",
                ChatColor.DARK_RED + "S+. Extremely powerful bosses, hints are hard and tedious.\n");
    }

    public static ChatColor getDifficultyColor(String difficulty) {
        if(difficulty == null) {
            return ChatColor.WHITE;
        }
        else if(difficulty.equalsIgnoreCase("S+")) {
            return ChatColor.DARK_RED;
        }
        return switch (difficulty.substring(0, 1)) {
            case "E" -> ChatColor.GRAY;
            case "D" -> ChatColor.AQUA;
            case "C" -> ChatColor.GREEN;
            case "B" -> ChatColor.RED;
            case "A" -> ChatColor.YELLOW;
            case "S" -> ChatColor.BLUE;
            default -> ChatColor.WHITE;
        };
    }
}
