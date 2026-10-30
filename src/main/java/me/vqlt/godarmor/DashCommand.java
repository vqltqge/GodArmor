package me.vqlt.godarmor;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
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

    private static final TextColor DASH_COLOR = TextColor.fromHexString("#FFD700");

    private final HashMap<UUID, Long> cooldowns = new HashMap<>();

    public DashCommand(
            GodArmor plugin,
            GodArmorManager godArmorManager
    ) {
        this.plugin = plugin;
        this.godArmorManager = godArmorManager;
    }

    public long getDashCooldownMillis() {
        long cooldownSeconds = plugin.getConfig().getLong(
                "dash.cooldown-seconds",
                5
        );

        return cooldownSeconds * 1000L;
    }

    public boolean isOnCooldown(UUID id) {
        return getRemainingMillis(id) > 0;
    }

    public long getRemainingMillis(UUID id) {
        Long lastUsed = cooldowns.get(id);

        if (lastUsed == null) {
            return 0;
        }

        long cooldownMillis = getDashCooldownMillis();
        long timePassed = System.currentTimeMillis() - lastUsed;
        long remaining = cooldownMillis - timePassed;

        if (remaining <= 0) {
            cooldowns.remove(id);
            return 0;
        }

        return remaining;
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

        if (isOnCooldown(playerId)) {
            double remainingSeconds = getRemainingMillis(playerId) / 1000.0;

            player.sendMessage(Component.text("➤ Dash is on cooldown for " + String.format("%.1f", remainingSeconds) + "s").color(DASH_COLOR));

            return true;
        }

        boolean dashed = godArmorManager.dash(player);

        if (!dashed) {
            player.sendMessage(Component.text("✦ You must wear the full God Armor Set to dash!").color(DASH_COLOR));
            return true;
        }

        cooldowns.put(playerId, System.currentTimeMillis());

        return true;
    }
}