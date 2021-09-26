package com.teerut.TeerutAll.RemoveItem;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class RemoveItem {
    public RemoveItem(Player player){
        ItemStack itemStack = player.getInventory().getItemInMainHand();
        player.getInventory().removeItem(itemStack);
    }
}
