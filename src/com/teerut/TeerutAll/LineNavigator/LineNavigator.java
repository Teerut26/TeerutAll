package com.teerut.TeerutAll.LineNavigator;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

public class LineNavigator {
    public LineNavigator(Player player, String[] args) {
        if (args.length != 0) {
            String player_name = args[0];
            Location location1 = player.getLocation();
            Location location2 = getLocation(player_name);
            int diffYabs = Math.abs(location2.getBlockZ() - location1.getBlockZ());
            int diffY = Math.abs(location2.getBlockZ() - location1.getBlockZ());
            float m = this.getSlope(location1, location2);
            Bukkit.broadcastMessage("++++++++++++++++++++++++++++++++++");
            Bukkit.broadcastMessage("M: " + String.format("%.3f", m));

            for (int i = 0; i <= diffYabs; i++) {
                double X = (i / m) + location1.getBlockX();
                double Y = player.getLocation().getY();
//                double Z = (((diffY*-1)+i)*-1) + location2.getBlockZ();
                double Z = player.getLocation().getZ();
                Bukkit.getServer().getWorld(player.getWorld().getName()).spawnParticle(Particle.VILLAGER_HAPPY,X,Y+1,Z,2);
                Bukkit.broadcastMessage("X: " + String.valueOf(X) + " Y: " + String.valueOf(Y) + " Z: " + String.valueOf(Z));
            }

//            for (int i = 0; i <= x_list.length; i++) {
//                Bukkit.broadcastMessage("X: " + String.valueOf(x_list[i]) + " Y: " + String.valueOf(y_list[i]) + " Z: " + String.valueOf(z_list[i]));
//            }
        }


    }

    static void reverse(int a[], int n)
    {
        int[] b = new int[n];
        int j = n;
        for (int i = 0; i < n; i++) {
            b[j - 1] = a[i];
            j = j - 1;
        }

        /*printing the reversed array*/
        System.out.println("Reversed array is: \n");
        for (int k = 0; k < n; k++) {
            System.out.println(b[k]);
        }
    }

    public void spawnParticle(Player player, double X, double Y, double Z) {
        Bukkit.getServer().getWorld(player.getWorld().getName()).spawnParticle(Particle.VILLAGER_HAPPY, X, Y, Z, 1);
    }

    public Location getLocation(String player_name) {
        Location location = Bukkit.getServer().getPlayer(player_name).getLocation();
        return location;

    }

    public float getSlope(Location location1, Location location2) {
        float y = location2.getBlockZ() - location1.getBlockZ();
        float x = location2.getBlockX() - location1.getBlockX();
        float m = y / x;
        return m;
    }

    public double getDistance(Player p1, Player p2) {
        double x = Math.pow((p1.getLocation().getBlockX() - p2.getLocation().getBlockX()), 2);
        double y = Math.pow((p1.getLocation().getBlockY() - p2.getLocation().getBlockY()), 2);
        double distance = Math.sqrt(x + y);
        return distance;
    }
}
