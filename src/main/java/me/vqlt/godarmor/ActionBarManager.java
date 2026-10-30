package me.vqlt.godarmor;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

public class ActionBarManager {
    private final GodArmorManager godArmorManager;
    private final DashCommand dashCommand;
    private final GodArmor plugin;

    private static final TextColor READY_COLOR = TextColor.fromHexString("#00ff00");
    private static final TextColor COOLDOWN_COLOR = TextColor.color(NamedTextColor.GRAY);
    private static final TextColor DASH_COLOR = TextColor.fromHexString("#FFD700");

    public ActionBarManager(GodArmorManager godArmorManager, DashCommand dashCommand, GodArmor plugin) {
        this.godArmorManager = godArmorManager;
        this.dashCommand = dashCommand;
        this.plugin = plugin;
    }

    public void start() {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    ItemStack helmet = player.getInventory().getHelmet();

                    if (!godArmorManager.isGodHelmet(helmet)) {
                        continue;
                    }

                    ItemStack chestplate = player.getInventory().getChestplate();

                    if (!godArmorManager.isGodChestplate(chestplate)) {
                        continue;
                    }

                    ItemStack leggings = player.getInventory().getLeggings();

                    if (!godArmorManager.isGodLeggings(leggings)) {
                        continue;
                    }

                    ItemStack boots = player.getInventory().getBoots();

                    if (!godArmorManager.isGodBoots(boots)) {
                        continue;
                    }

                    sendAbilityBar(player);
                }
            }
        }.runTaskTimer(plugin, 0L, 2L);
    }

    private void sendAbilityBar(Player player) {
        UUID id = player.getUniqueId();

        Component dash = Component.text("➜ Dash  ").color(DASH_COLOR);

        Component status = getDashState(id);

        player.sendActionBar(dash.append(status));
    }

    private Component getDashState(UUID id) {
        if (dashCommand.isOnCooldown(id)) {
            return Component.text(String.format("%.1fs", dashCommand.getRemainingMillis(id) / 1000.0)).color(COOLDOWN_COLOR).decorate(TextDecoration.BOLD);
        }

        return Component.text("READY").color(READY_COLOR).decorate(TextDecoration.BOLD);
    }
}

