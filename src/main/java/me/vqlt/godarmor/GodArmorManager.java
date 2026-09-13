package me.vqlt.godarmor;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;

public class GodArmorManager {
    // Field - store the dependency
    private final GodArmor plugin;
    private final NamespacedKey armorKey;

    // Constructor - receive the dependency
    public GodArmorManager(GodArmor plugin) {
        this.plugin = plugin;
        this.armorKey = new NamespacedKey(plugin, "god_armor_piece");
    }

    // Method - create the helmet
    public ItemStack createHelmet() {
        // Create the helmet
        ItemStack helmet = new ItemStack(Material.NETHERITE_HELMET);
        // Get the ItemMeta of the helmet
        ItemMeta helmetMeta = helmet.getItemMeta();
        // Modify the PDC
        PersistentDataContainer container = helmetMeta.getPersistentDataContainer();
        container.set(armorKey, PersistentDataType.STRING, "helmet");
        // Modify the ItemMeta displayName
        helmetMeta.displayName(Component.text("God Helmet").color(NamedTextColor.GOLD).decoration(TextDecoration.BOLD, true).decoration(TextDecoration.ITALIC, false));
        // Add lore
        helmetMeta.lore(
                List.of(
                        Component.text(
                                "Worn only by the greatest of rulers.",
                                NamedTextColor.GRAY
                        ),
                        Component.empty(),
                        Component.text("◆ PASSIVE")
                                .color(NamedTextColor.YELLOW)
                                .decorate(TextDecoration.BOLD)
                                .decoration(TextDecoration.ITALIC, false),
                        Component.text(
                                "Hero of the Village X").color(NamedTextColor.WHITE).decoration(TextDecoration.ITALIC, false)
                )
        );
        // Set it back ot the chestplate
        helmet.setItemMeta(helmetMeta);
        return helmet;
    }


    // Method - create the chestplate
    public ItemStack createChestplate() {
        // Create the chestplate
        ItemStack chestplate = new ItemStack(Material.NETHERITE_CHESTPLATE);
        // Get the ItemMeta of the chestplate
        ItemMeta chestplateMeta = chestplate.getItemMeta();
        // Modify the PDC
        PersistentDataContainer container = chestplateMeta.getPersistentDataContainer();
        container.set(armorKey, PersistentDataType.STRING, "chestplate");
        // Modify the ItemMeta displayName
        chestplateMeta.displayName(Component.text("God Chestplate").color(NamedTextColor.GOLD).decoration(TextDecoration.BOLD, true).decoration(TextDecoration.ITALIC, false));
        // Add lore
        chestplateMeta.lore(
                List.of(
                        Component.text(
                                "An armour plate blessed with divine strength.",
                                NamedTextColor.GRAY
                        ),
                        Component.empty(),
                        Component.text("◆ PASSIVE")
                                .color(NamedTextColor.YELLOW)
                                .decorate(TextDecoration.BOLD)
                                .decoration(TextDecoration.ITALIC, false),
                        Component.text(
                                "Strength II").color(NamedTextColor.WHITE).decoration(TextDecoration.ITALIC, false)
                )
        );
        // Set it back ot the chestplate
        chestplate.setItemMeta(chestplateMeta);
        return chestplate;
    }

    // Method - create the leggings
    public ItemStack createLeggings() {
        // Create the leggings
        ItemStack leggings = new ItemStack(Material.NETHERITE_LEGGINGS);
        // Get the ItemMeta of the leggings
        ItemMeta leggingsMeta = leggings.getItemMeta();
        // Modify the PDC
        PersistentDataContainer container = leggingsMeta.getPersistentDataContainer();
        container.set(armorKey, PersistentDataType.STRING, "leggings");
        // Modify the ItemMeta displayName
        leggingsMeta.displayName(Component.text("God Leggings").color(NamedTextColor.GOLD).decoration(TextDecoration.BOLD, true).decoration(TextDecoration.ITALIC, false));
        // Add lore
        leggingsMeta.lore(
                List.of(
                        Component.text(
                                "Moves faster than mortal eyes can follow.",
                                NamedTextColor.GRAY
                        ),
                        Component.empty(),
                        Component.text("◆ PASSIVE")
                                .color(NamedTextColor.YELLOW)
                                .decorate(TextDecoration.BOLD)
                                .decoration(TextDecoration.ITALIC, false),
                        Component.text(
                                "Speed II").color(NamedTextColor.WHITE).decoration(TextDecoration.ITALIC, false)
                )
        );
        // Set it back to the leggings
        leggings.setItemMeta(leggingsMeta);
        return leggings;
    }

    // Method - create the boots
    public ItemStack createBoots() {
        // Create the boots
        ItemStack boots = new ItemStack(Material.NETHERITE_BOOTS);
        // Get the ItemMeta of the boots
        ItemMeta bootsMeta = boots.getItemMeta();
        // Modify the PDC
        PersistentDataContainer container = bootsMeta.getPersistentDataContainer();
        container.set(armorKey, PersistentDataType.STRING, "boots");
        // Modify the ItemMeta displayName
        bootsMeta.displayName(Component.text("God Boots").color(NamedTextColor.GOLD).decoration(TextDecoration.BOLD, true).decoration(TextDecoration.ITALIC, false));
        // Add lore
        bootsMeta.lore(
                List.of(
                        Component.text(
                                "Forged for the ruler of the seas.",
                                NamedTextColor.GRAY
                        ),
                        Component.empty(),
                        Component.text("◆ PASSIVE")
                                .color(NamedTextColor.YELLOW)
                                .decorate(TextDecoration.BOLD)
                                .decoration(TextDecoration.ITALIC, false),
                        Component.text(
                                "Dolphin's Grace").color(NamedTextColor.WHITE).decoration(TextDecoration.ITALIC, false),
                        Component.text(
                                "Water Breathing").color(NamedTextColor.WHITE).decoration(TextDecoration.ITALIC, false)
                )
        );
        // Set it back to the boots
        boots.setItemMeta(bootsMeta);
        return boots;
    }

    // Method - check whether an item is the real God Helmet
    public boolean isGodHelmet(ItemStack item) {
        // Check the player is actually wearing a helmet
        if (item == null) {
            return false;
        }

        // Check if the item has metadata
        if (!item.hasItemMeta()) {
            return false;
        }

        // Get the itemmeta
        ItemMeta itemMeta = item.getItemMeta();
        PersistentDataContainer container = itemMeta.getPersistentDataContainer();
        String armorPiece = container.get(armorKey, PersistentDataType.STRING);
        return "helmet".equals(armorPiece);
    }

    // Method - check whether an item is the real God Chestplate
    public boolean isGodChestplate(ItemStack item) {
        // Check the player is actually wearing a helmet
        if (item == null) {
            return false;
        }

        // Check if the item has metadata
        if (!item.hasItemMeta()) {
            return false;
        }

        // Get the itemmeta
        ItemMeta itemMeta = item.getItemMeta();
        PersistentDataContainer container = itemMeta.getPersistentDataContainer();
        String armorPiece = container.get(armorKey, PersistentDataType.STRING);
        return "chestplate".equals(armorPiece);
    }

    // Method - check whether an item is the real God Leggings
    public boolean isGodLeggings(ItemStack item) {
        // Check the player is actually wearing a helmet
        if (item == null) {
            return false;
        }

        // Check if the item has metadata
        if (!item.hasItemMeta()) {
            return false;
        }

        // Get the itemmeta
        ItemMeta itemMeta = item.getItemMeta();
        PersistentDataContainer container = itemMeta.getPersistentDataContainer();
        String armorPiece = container.get(armorKey, PersistentDataType.STRING);
        return "leggings".equals(armorPiece);
    }

    // Method - check whether an item is the real God Boots
    public boolean isGodBoots(ItemStack item) {
        // Check the player is actually wearing a helmet
        if (item == null) {
            return false;
        }

        // Check if the item has metadata
        if (!item.hasItemMeta()) {
            return false;
        }

        // Get the itemmeta
        ItemMeta itemMeta = item.getItemMeta();
        PersistentDataContainer container = itemMeta.getPersistentDataContainer();
        String armorPiece = container.get(armorKey, PersistentDataType.STRING);
        return "boots".equals(armorPiece);
    }

    public void updateArmorEffects(Player player) {
        ItemStack helmet = player.getInventory().getHelmet();
        if (isGodHelmet(helmet)) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.HERO_OF_THE_VILLAGE, 40, 9));
        }

        ItemStack chestplate = player.getInventory().getChestplate();
        if (isGodChestplate(chestplate)) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH, 40, 1));
        }

        ItemStack leggings = player.getInventory().getLeggings();
        if (isGodLeggings(leggings)) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 40, 1));
        }

        ItemStack boots = player.getInventory().getBoots();
        if (isGodBoots(boots)) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 40, 0));
            player.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 40, 0));
        }


    }


    public boolean dash(Player player) {

        ItemStack helmet = player.getInventory().getHelmet();
        if (!isGodHelmet(helmet)) {
            return false;
        }

        ItemStack chestplate = player.getInventory().getChestplate();
        if (!isGodChestplate(chestplate)) {
            return false;
        }

        ItemStack leggings = player.getInventory().getLeggings();
        if (!isGodLeggings(leggings)) {
            return false;
        }

        ItemStack boots = player.getInventory().getBoots();
        if (!isGodBoots(boots)) {
            return false;
        }

        double dashStrength = plugin.getConfig().getDouble("dash.strength", 3.0);

        player.setVelocity(player.getLocation().getDirection().multiply(dashStrength));
        return true;
    }

    public boolean isGodArmor(ItemStack item) {
        return isGodHelmet(item)
                || isGodChestplate(item)
                || isGodLeggings(item)
                || isGodBoots(item);
    }
}
