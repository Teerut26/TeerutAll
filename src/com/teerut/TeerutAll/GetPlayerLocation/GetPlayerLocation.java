package com.teerut.TeerutAll.GetPlayerLocation;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class GetPlayerLocation {
    public GetPlayerLocation(String[] args, Player player) {
        if (args.length != 0) {
            String player_name = args[0];
            Location location = getLocation(player_name);
            Player player2 = Bukkit.getServer().getPlayer(player_name);
            player2.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 600, 0));
            double distance = this.getDistance(player, player2);
            player2.sendMessage(
                    "Your Location get by : " + ChatColor.GREEN + ChatColor.BOLD + player.getName());
            sendToPlayer(player.getName(),
                    "X:" + location.getBlockX() + " Y:" + location.getBlockY() + " Z:" + location.getBlockZ() + " (" + String.format("%.2f", distance) + ")" + " At " + location.getWorld().getName());
        } else {
            Location location = getLocation(player.getName());
            player.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 600, 0));
            sendToPlayer(player.getName(), "X:" + location.getBlockX() + " Y:" + location.getBlockY() + " Z:"
                    + location.getBlockZ() + " At " + location.getWorld().getName());
        }
    }

    public double getDistance(Player p1, Player p2) {
        double x = Math.pow((p1.getLocation().getBlockX() - p2.getLocation().getBlockX()), 2);
        double y = Math.pow((p1.getLocation().getBlockY() - p2.getLocation().getBlockY()), 2);
        double distance = Math.sqrt(x + y);
        return distance;
    }

    public Location getLocation(String player_name) {
        Location location = Bukkit.getServer().getPlayer(player_name).getLocation();
        return location;

    }

    public void sendToPlayer(String player_name, String message) {
        Player player = Bukkit.getPlayer(player_name);
        player.sendMessage(message);
    }
}
