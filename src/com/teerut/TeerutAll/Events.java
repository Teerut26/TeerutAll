package com.teerut.TeerutAll;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

import java.util.ArrayList;

public class Events implements Listener {
    public ArrayList<Material> blockList = new ArrayList<Material>();

    public Events() {
        blockList.add(Material.WHITE_CONCRETE_POWDER);
    }

    @EventHandler
    public void PlayerDropItemEvent(PlayerDropItemEvent e) {
        if (!(e.getItemDrop().getLocation().getBlock().getType().equals(Material.WATER)))
            return;
        if (!(this.blockList.contains(e.getItemDrop().getItemStack().getType())))
            return;

        e.getItemDrop().getItemStack().setType(Material.WHITE_CONCRETE);
        Bukkit.getServer().getWorld(e.getPlayer().getWorld().getName()).spawnParticle(Particle.GLOW_SQUID_INK, e.getPlayer().getLocation(), 20);

    }
}
