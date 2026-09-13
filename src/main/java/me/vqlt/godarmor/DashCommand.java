package me.vqlt.godarmor;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.UUID;

public class DashCommand implements CommandExecutor {

    private final GodArmor plugin;
    private final GodArmorManager godArmorManager;

    private final HashMap<UUID, Long> cooldowns = new HashMap<>();

    public DashCommand(
            GodArmor plugin,
            GodArmorManager godArmorManager
    ) {
        this.plugin = plugin;
        this.godArmorManager = godArmorManager;
    }

    private long getDashCooldownMillis() {
        long cooldownSeconds = plugin.getConfig().getLong(
                "dash.cooldown-seconds",
                5
        );

        return cooldownSeconds * 1000L;
    }

    @Override
    public boolean onCommand(
            @NotNull CommandSender sender,
            @NotNull Command command,
            @NotNull String label,
            @NotNull String @NotNull [] args
    ) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Players only");
            return true;
        }

        UUID playerId = player.getUniqueId();
        long currentTime = System.currentTimeMillis();
        long cooldownMillis = getDashCooldownMillis();

        if (cooldowns.containsKey(playerId)) {
            long lastUsed = cooldowns.get(playerId);
            long timePassed = currentTime - lastUsed;

            if (timePassed < cooldownMillis) {
                double remainingTime =
                        (cooldownMillis - timePassed) / 1000.0;

                player.sendMessage(
                        String.format(
                                "Dash is on cooldown for %.1f more seconds.",
                                remainingTime
                        )
                );

                return true;
            }
        }

        boolean dashed = godArmorManager.dash(player);

        if (!dashed) {
            player.sendMessage(
                    "You must wear the full God Armor Set to dash!"
            );
            return true;
        }

        cooldowns.put(
                playerId,
                System.currentTimeMillis()
        );

        return true;
    }
}