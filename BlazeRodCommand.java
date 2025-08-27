package com.blazegun.commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class BlazeRodCommand implements CommandExecutor {
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "This command can only be used by players!");
            return true;
        }
        
        Player player = (Player) sender;
        
        if (!player.hasPermission("blazegun.use")) {
            player.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
            return true;
        }
        
        ItemStack blazeRod = createBlazeRodGun();
        
        if (player.getInventory().firstEmpty() == -1) {
            player.sendMessage(ChatColor.RED + "Your inventory is full! Make some space first.");
            return true;
        }
        
        player.getInventory().addItem(blazeRod);
        player.sendMessage(ChatColor.GREEN + "You have received a Blaze Rod Gun!");
        player.sendMessage(ChatColor.YELLOW + "Right-click to shoot snowballs!");
        
        return true;
    }
    private ItemStack createBlazeRodGun() {
        ItemStack blazeRod = new ItemStack(Material.BLAZE_ROD, 1);
        ItemMeta meta = blazeRod.getItemMeta();
        
        meta.setDisplayName(ChatColor.GOLD + "Blaze Rod Gun");
        meta.setLore(Arrays.asList(
            ChatColor.GRAY + "Right-click to shoot snowballs!",
            ChatColor.GRAY + "Makes cat sounds on impact!",
            ChatColor.DARK_PURPLE + "Special Weapon"
        ));
        
        blazeRod.setItemMeta(meta);
        return blazeRod;
    }
}
