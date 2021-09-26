package com.teerut.TeerutAll;

import com.teerut.TeerutAll.GetPlayerLocation.GetPlayerLocation;
import com.teerut.TeerutAll.LineNavigator.LineNavigator;
import com.teerut.TeerutAll.RemoveItem.RemoveItem;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new Events(),this);
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
