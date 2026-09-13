package me.vqlt.godarmor;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class GodArmorCommand implements CommandExecutor {

    private final GodArmorManager godArmorManager;

    public GodArmorCommand(GodArmorManager godArmorManager) {
        this.godArmorManager = godArmorManager;
    }

    @Override
    public boolean onCommand(
            @NotNull CommandSender sender,
            @NotNull Command command,
            @NotNull String label,
            @NotNull String[] args
    ) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Only players can use this command.");
            return true;
        }

        if (!player.isOp()) {
            player.sendMessage("You do not have permission to use this command.");
            return true;
        }

        GodArmorGUI gui = new GodArmorGUI(godArmorManager);
        player.openInventory(gui.getInventory());

        return true;
    }
}