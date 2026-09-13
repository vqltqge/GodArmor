package me.vqlt.godarmor;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;

public class GodArmorGUI implements InventoryHolder {

    private final Inventory inventory;
    private final GodArmorManager godArmorManager;

    public GodArmorGUI(GodArmorManager godArmorManager) {
        this.godArmorManager = godArmorManager;

        inventory = Bukkit.createInventory(
                this,
                9,
                Component.text("God Armor")
                        .color(NamedTextColor.GOLD)
        );

        setupItems();
    }

    private void setupItems() {
        inventory.setItem(1, godArmorManager.createHelmet());
        inventory.setItem(3, godArmorManager.createChestplate());
        inventory.setItem(5, godArmorManager.createLeggings());
        inventory.setItem(7, godArmorManager.createBoots());
    }

    @Override
    public @NotNull Inventory getInventory() {
        return inventory;
    }
}