package com.teerut.TeerutAll.ConcreteWater;

import com.teerut.TeerutAll.ItemMerge.ItemMergeEvents;
import com.teerut.TeerutAll.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

public class ConcreteWaterEvent implements Listener {
    public ArrayList<Material> blockConcrete = new ArrayList<Material>();
    public ArrayList<Material> blockPower = new ArrayList<Material>();
    private Main plugin;

    public ConcreteWaterEvent(Main plugin) {

        this.plugin = plugin;

        blockPower.add(Material.WHITE_CONCRETE_POWDER);
        blockPower.add(Material.ORANGE_CONCRETE_POWDER);
        blockPower.add(Material.MAGENTA_CONCRETE_POWDER);
        blockPower.add(Material.LIGHT_BLUE_CONCRETE_POWDER);
        blockPower.add(Material.YELLOW_CONCRETE_POWDER);
        blockPower.add(Material.LIME_CONCRETE_POWDER);
        blockPower.add(Material.PINK_CONCRETE_POWDER);
        blockPower.add(Material.GRAY_CONCRETE_POWDER);
        blockPower.add(Material.LIGHT_GRAY_CONCRETE_POWDER);
        blockPower.add(Material.CYAN_CONCRETE_POWDER);
        blockPower.add(Material.PURPLE_CONCRETE_POWDER);
        blockPower.add(Material.BLUE_CONCRETE_POWDER);
        blockPower.add(Material.BROWN_CONCRETE_POWDER);
        blockPower.add(Material.GREEN_CONCRETE_POWDER);
        blockPower.add(Material.RED_CONCRETE_POWDER);
        blockPower.add(Material.BLACK_CONCRETE_POWDER);

        blockConcrete.add(Material.WHITE_CONCRETE);
        blockConcrete.add(Material.ORANGE_CONCRETE);
        blockConcrete.add(Material.MAGENTA_CONCRETE);
        blockConcrete.add(Material.LIGHT_BLUE_CONCRETE);
        blockConcrete.add(Material.YELLOW_CONCRETE);
        blockConcrete.add(Material.LIME_CONCRETE);
        blockConcrete.add(Material.PINK_CONCRETE);
        blockConcrete.add(Material.GRAY_CONCRETE);
        blockConcrete.add(Material.LIGHT_GRAY_CONCRETE);
        blockConcrete.add(Material.CYAN_CONCRETE);
        blockConcrete.add(Material.PURPLE_CONCRETE);
        blockConcrete.add(Material.BLUE_CONCRETE);
        blockConcrete.add(Material.BROWN_CONCRETE);
        blockConcrete.add(Material.GREEN_CONCRETE);
        blockConcrete.add(Material.RED_CONCRETE);
        blockConcrete.add(Material.BLACK_CONCRETE);
    }

    @EventHandler
    public void PlayerDropItemEvent(PlayerDropItemEvent event) {
        if (!(this.blockPower.contains(event.getItemDrop().getItemStack().getType())))
            return;
        int start = -1;
        int end = 1;
        for (int x = start; x < end; x++) {
            for (int y = start-1; y < end; y++) {
                for (int z = start; z < end; z++) {
                    if (event.getItemDrop().getLocation().getBlock().getRelative(x, y, z).getType().equals(Material.WATER)) {
                        int index = ConcreteWaterEvent.this.blockPower.indexOf(event.getItemDrop().getItemStack().getType());
                        event.getItemDrop().getItemStack().setType(blockConcrete.get(index));
                        Bukkit.getServer().getWorld(event.getPlayer().getWorld().getName()).spawnParticle(Particle.GLOW_SQUID_INK, event.getPlayer().getLocation(), 20);
                    }
                }
            }
        }

    }
}
