package com.teerut.TeerutAll.Fishing;

import com.teerut.TeerutAll.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FishEvents implements Listener {

    public Main plugin;

    public FishEvents(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onFishingEvent(PlayerFishEvent event) {
        if (event.getState().equals(PlayerFishEvent.State.CAUGHT_FISH)) {
            if (event.getCaught().getType().equals(EntityType.DROPPED_ITEM)) {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();
                event.getPlayer().sendMessage("["+dtf.format(now)+"] "+ChatColor.YELLOW + ChatColor.BOLD.toString() + event.getCaught().getName() + ChatColor.RESET);
            }
        }

    }
}
