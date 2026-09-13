package me.vqlt.godarmor;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class GodArmor extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();

        GodArmorManager godArmorManager = new GodArmorManager(this);

        getCommand("godarmor").setExecutor(new GodArmorCommand(godArmorManager));
        getCommand("dash").setExecutor(new DashCommand(this, godArmorManager));

        getServer().getPluginManager().registerEvents(new GodArmorGUIListener(), this);

        Bukkit.getScheduler().runTaskTimer(this, () -> {
            for (Player player : Bukkit.getOnlinePlayers()) {
                godArmorManager.updateArmorEffects(player);
            }
        }, 0L, 20L);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
