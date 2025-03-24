package com.example;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class NoStackTotems extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        getLogger().info("NoStackTotems has been enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("NoStackTotems has been disabled!");
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        spreadTotems(event.getPlayer()); // Spread totems on login.
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        // Prevent stacking during inventory interactions.
        if (event.getCurrentItem() != null && event.getCurrentItem().getType() == Material.TOTEM_OF_UNDYING) {
            event.getCurrentItem().setAmount(1);
        }
        if (event.getCursor() != null && event.getCursor().getType() == Material.TOTEM_OF_UNDYING) {
            event.getCursor().setAmount(1);
        }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        // Spread totems whenever the inventory is closed
        if (event.getPlayer() instanceof Player) { // Ensure the player closing the inventory is a player
            spreadTotems((Player) event.getPlayer());
        }
    }

    private void spreadTotems(Player player) {
        Inventory inventory = player.getInventory();
        ItemStack[] contents = inventory.getContents();

        List<ItemStack> totemsToSpread = new ArrayList<>();
        for (int i = 0; i < contents.length; i++) {
            ItemStack item = contents[i];
            if (item != null && item.getType() == Material.TOTEM_OF_UNDYING && item.getAmount() > 1) {
                totemsToSpread.add(item); // Collect stacks of totems to spread.
                inventory.setItem(i, null); // Remove the stacked item.
            }
        }

        // Now spread the totems in the inventory
        for (ItemStack totemStack : totemsToSpread) {
            int amount = totemStack.getAmount();
            for (int i = 0; i < amount; i++) {
                ItemStack singleTotem = new ItemStack(Material.TOTEM_OF_UNDYING, 1);
                int firstEmpty = inventory.firstEmpty();

                if (firstEmpty != -1) {
                    inventory.setItem(firstEmpty, singleTotem);  // Place a single totem in the empty slot.
                } else {
                    // No empty slots.  Drop the remaining totems at the player's location.
                    player.getWorld().dropItem(player.getLocation(), singleTotem);
                }
            }
        }
    }
}