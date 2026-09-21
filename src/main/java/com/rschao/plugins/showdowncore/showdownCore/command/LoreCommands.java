package com.rschao.plugins.showdowncore.showdownCore.command;

import com.rschao.plugins.showdowncore.showdownCore.ShowdownCore;
import com.rschao.plugins.showdowncore.showdownCore.gui.lore.LoreMenu;
import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.ArgumentSuggestions;
import dev.jorel.commandapi.arguments.EntitySelectorArgument;
import dev.jorel.commandapi.arguments.StringArgument;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

public class LoreCommands {

    public static void registerCommands() {
        mainCMD.register();
        createCampaign.register();
    }

    public static String[] getCampaigns() {
        File campaignsFolder = new File(ShowdownCore.getInstance().getDataFolder(), "lore/campaigns");
        File[] campaignFiles = campaignsFolder.listFiles();
        if (campaignFiles == null) {
            return new String[0];
        }

        List<String> campaigns = new ArrayList<>();
        for (File campaignFile : campaignFiles) {
            String fileName = campaignFile.getName();
            if (!campaignFile.isFile() || !fileName.endsWith(".yml")) {
                continue;
            }
            String campaign = fileName.substring(0, fileName.length() - ".yml".length());
            FileConfiguration configuration = YamlConfiguration.loadConfiguration(campaignFile);
            if (configuration.get("lore." + campaign + ".name") instanceof String) {
                campaigns.add(campaign);
            }
        }

        return campaigns.toArray(new String[0]);
    }


    public static CommandAPICommand mainCMD = new CommandAPICommand("loreinfo")
            .withArguments(new StringArgument("campaign").replaceSuggestions(ArgumentSuggestions.strings(info -> getCampaigns())), new EntitySelectorArgument.OnePlayer("player").setOptional(true))
            .executes((player, args) -> {;
                String campaign = (String) args.get("campaign");
                if(player instanceof Player) {
                    LoreMenu.getCampaignMenu((Player) player, campaign);
                } else {
                    Player targetPlayer = (Player) args.get("player");
                    if(targetPlayer != null) {
                        LoreMenu.getCampaignMenu(targetPlayer, campaign);
                    } else {
                        player.sendMessage("You must specify a player when running this command from the console.");
                    }
                }
            });


    public static CommandAPICommand createCampaign = new CommandAPICommand("createlore")
            .withArguments(new StringArgument("campaign").replaceSuggestions(ArgumentSuggestions.strings(info -> getCampaigns())))
            .withPermission("showdowncore.lore.admin")
            .executes((player, args) -> {;
                String campaign = (String) args.get("campaign");
                Path campaignsFolder = ShowdownCore.getInstance()
                        .getDataFolder()
                        .toPath()
                        .resolve("lore")
                        .resolve("campaigns");

                Path campaignFile = campaignsFolder.resolve(campaign + ".yml");

                try {
                    Files.createDirectories(campaignsFolder);

                    if (Files.exists(campaignFile)) {
                        player.sendMessage("That campaign already exists.");
                        return;
                    }

                    try (InputStream resource = ShowdownCore.getInstance()
                            .getResource("campaign.yml")) {

                        if (resource == null) {
                            player.sendMessage("The campaign template is missing.");
                            return;
                        }

                        Files.copy(resource, campaignFile,
                                StandardCopyOption.REPLACE_EXISTING);
                        FileConfiguration configuration = YamlConfiguration.loadConfiguration(campaignFile.toFile());
                        ConfigurationSection templateSection =
                                configuration.getConfigurationSection("lore.campaign");

                        if (templateSection == null) {
                            throw new IOException("campaign.yml is missing the lore.campaign section");
                        }

                        String targetPath = "lore." + campaign;

                        // Copy every value from lore.campaign to lore.<campaign>
                        configuration.createSection(targetPath);

                        for (String key : templateSection.getKeys(true)) {
                            String sourcePath = "lore.campaign." + key;
                            String destinationPath = targetPath + "." + key;

                            if (configuration.isConfigurationSection(sourcePath)) {
                                configuration.createSection(destinationPath);
                            } else {
                                configuration.set(destinationPath, configuration.get(sourcePath));
                            }
                        }

                        // Remove the template section
                        configuration.set("lore.campaign", null);
                        configuration.save(campaignFile.toFile());
                    }

                    player.sendMessage("Campaign created: " + campaign);
                } catch (Exception exception) {
                    ShowdownCore.getInstance().getLogger()
                            .severe("Could not create campaign " + campaign
                                    + ": " + exception.getMessage());
                    player.sendMessage("Could not create the campaign.");
                }
            });

}
