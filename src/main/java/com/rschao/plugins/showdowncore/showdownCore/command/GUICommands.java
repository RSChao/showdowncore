package com.rschao.plugins.showdowncore.showdownCore.command;

import com.rschao.plugins.showdowncore.showdownCore.gui.anvil.AnvilGUI;
import com.rschao.plugins.showdowncore.showdownCore.gui.crafting.CraftingGUI;
import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.EntitySelectorArgument;
import org.bukkit.entity.Player;

public class GUICommands {

    public static CommandAPICommand guiCommand = new CommandAPICommand("gui")
            .withSubcommand(new CommandAPICommand("crafting")
                    .withArguments(new EntitySelectorArgument.OnePlayer("player").setOptional(true))
                    .executesPlayer((sender, args) -> {
                        CraftingGUI craftingGUI = new CraftingGUI();
                        craftingGUI.openCraftingGUI(sender);
                    }))
            .executes((sender, args) -> {
                Player player = (Player) args.get("player");
                if(player == null) {
                    sender.sendMessage("You must specify a player when running this command from the console.");
                    return;
                }
                CraftingGUI craftingGUI = new CraftingGUI();
                craftingGUI.openCraftingGUI(player);
            })
            .withSubcommand(new CommandAPICommand("anvil")
                    .withArguments(new EntitySelectorArgument.OnePlayer("player").setOptional(true))
                    .executesPlayer((sender, args) -> {
                        AnvilGUI anvilGUI = new AnvilGUI();
                        anvilGUI.openAnvilGUI(sender);
                    }))
            .executes((sender, args) -> {
                Player player = (Player) args.get("player");
                if(player == null) {
                    sender.sendMessage("You must specify a player when running this command from the console.");
                    return;
                }
                AnvilGUI anvilGUI = new AnvilGUI();
                anvilGUI.openAnvilGUI(player);
            })
            .withSubcommand(new CommandAPICommand("custom")
                    .executes((sender, args) -> {
                        // Open custom GUI for the player
                    }));
}
