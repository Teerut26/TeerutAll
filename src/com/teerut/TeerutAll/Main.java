package com.teerut.TeerutAll;

import com.teerut.TeerutAll.ConcreteWater.ConcreteWaterEvent;
import com.teerut.TeerutAll.Fishing.FishEvents;
import com.teerut.TeerutAll.GetPlayerLocation.GetPlayerLocation;
import com.teerut.TeerutAll.ItemMerge.ItemMergeEvents;
import com.teerut.TeerutAll.RemoveItem.RemoveItem;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.awt.*;
import java.io.IOException;

public class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new ConcreteWaterEvent(this), this);
        getServer().getPluginManager().registerEvents(new ItemMergeEvents(this), this);
        getServer().getPluginManager().registerEvents(new FishEvents(this), this);

        System.out.println(ChatColor.AQUA + "[TeerutAll] : onEnable");
        super.onEnable();
    }

    @Override
    public void onDisable() {
        System.out.println(ChatColor.AQUA + "[TeerutAll] : onDisable");
        super.onDisable();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            switch (command.getLabel().toLowerCase()) {
                case "lct":
                    new GetPlayerLocation(args, player);
                    break;
                case "rm":
                    new RemoveItem(player);
                    break;
//                case "nvg":
//                    new LineNavigator(player,args);
//                    break;
            }
        }
        return super.onCommand(sender, command, label, args);
    }
}
