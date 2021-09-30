package com.teerut.TeerutAll.ItemMerge;

import com.teerut.TeerutAll.Main;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemMergeEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class ItemMergeEvents implements Listener {
    public Main plugin;

    private double maxDistance = 32.0D;

    private int updateTimer = 40;

    private boolean displayNames = true;

    private boolean nullPlayerName = false;

    private String amountColor = "&c&l";
    private String itemNameColor = "&7";

    private String playerNameColor = "&e";

    public ItemMergeEvents(Main plugin) {
        this.plugin = plugin;
        this.setupPreferences();
    }

    void setupPreferences() {
        this.amountColor = this.plugin.getConfig().getString("Amount Color");
        this.itemNameColor = this.plugin.getConfig().getString("ItemName Color");
    }

    @EventHandler
    public void onPlayerDrop(PlayerDropItemEvent event) {
        Item item = event.getItemDrop();
        item.setCustomName(color(item.getName()));
    }

    @EventHandler
    public void onDropSpawn(final ItemSpawnEvent event) {
        final ItemStack item = event.getEntity().getItemStack();
        final ItemMeta meta = item.getItemMeta();
        int amount = event.getEntity().getItemStack().getAmount();
        if (this.displayNames)
            if (event.getEntity().getItemStack().getMaxStackSize() > 1) {
                event.getEntity().setCustomName(color(ItemMergeEvents.this.itemNameColor + String.valueOf(String.valueOf(setDropName(event.getEntity()))) + this.amountColor + " x" + amount));
            } else {
                event.getEntity().setCustomName(color(setDropName(event.getEntity())));
            }
        (new BukkitRunnable() {
            public void run() {
                if (event.getEntity().isDead())
                    cancel();
                List<Entity> entities = event.getEntity().getNearbyEntities(ItemMergeEvents.this.maxDistance, ItemMergeEvents.this.maxDistance, ItemMergeEvents.this.maxDistance);
                boolean isPlayerNearby = false;
                for (Entity entity : entities) {
                    if (entity.getType() != EntityType.PLAYER)
                        continue;
                    isPlayerNearby = true;
                }
                if (!isPlayerNearby || (meta != null && meta
                        .hasDisplayName() && meta.getDisplayName().equalsIgnoreCase(""))) {
                    event.getEntity().setCustomNameVisible(false);
                } else if (isPlayerNearby) {
                    event.getEntity().setCustomNameVisible(true);
                }
            }
        }).runTaskTimer(this.plugin, 2L, this.updateTimer);
    }

    @EventHandler
    public void onItemMerge(final ItemMergeEvent event) {
        final String name = setDropName(event.getTarget());
        final int amount = event.getEntity().getItemStack().getAmount() + event.getTarget().getItemStack().getAmount();
        if (!this.displayNames)
            return;
        (new BukkitRunnable() {
            public void run() {
                event.getTarget().setCustomName(ItemMergeEvents.this.color(ItemMergeEvents.this.itemNameColor + name + ItemMergeEvents.this.amountColor + " x" + amount));
            }
        }).runTaskLater(this.plugin, 1L);
    }

    public String color(String msg) {
        StringBuilder coloredMsg = new StringBuilder();
        for (int i = 0; i < msg.length(); i++) {
            if (msg.charAt(i) == '&') {
                coloredMsg.append('§');
            } else {
                coloredMsg.append(msg.charAt(i));
            }
        }
        return coloredMsg.toString();
    }

    private String setDropName(Item item) {
        String name;
        if (item.getItemStack().getItemMeta() != null) {
            if (item.getItemStack().getItemMeta().getDisplayName().equalsIgnoreCase("")) {
                if (item.getCustomName() == null) {
                    name = item.getName();
                } else {
                    name = item.getCustomName();
                }
            } else {
                name = item.getItemStack().getItemMeta().getDisplayName();
            }
        } else if (item.getCustomName() != null) {
            name = item.getCustomName();
        } else {
            name = item.getName();
            if (name.contains("."))
                name = getFriendlyName(item.getItemStack());
        }
        if (name.contains(color(String.valueOf(String.valueOf(this.amountColor)) + " x")) && name.contains(""))
            name = name.subSequence(0, name.length() - 6).toString();
        return name;
    }

    private String format(String s) {
        if (!s.contains("_"))
            return capitalize(s);
        String[] j = s.split("_");
        StringBuilder c = new StringBuilder();
        for (String f : j) {
            f = capitalize(f);
            c.append(c.toString().equalsIgnoreCase("") ? f : (" " + f));
        }
        return c.toString();
    }

    private String capitalize(String text) {
        String firstLetter = text.substring(0, 1).toUpperCase();
        String next = text.substring(1).toLowerCase();
        return firstLetter + next;
    }

    private String getFriendlyName(ItemStack item) {
        Material m = item.getType();
        return format(m.name());
    }


}
