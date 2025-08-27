package com.blazegun.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class BlazeGunListener implements Listener {
    
    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getItemInHand();
        
        if (event.getAction() != Action.RIGHT_CLICK_AIR && event.getAction() != Action.RIGHT_CLICK_BLOCK) {
            return;
        }
        
        if (item == null || item.getType() != Material.BLAZE_ROD) {
            return;
        }
        
        if (!item.hasItemMeta() || !item.getItemMeta().hasDisplayName()) {
            return;
        }
        
        String displayName = ChatColor.stripColor(item.getItemMeta().getDisplayName());
        if (!displayName.equals("Blaze Rod Gun")) {
            return;
        }
        
        event.setCancelled(true);
        shootSnowball(player);
    }
    
    private void shootSnowball(Player player) {
        Snowball snowball = player.launchProjectile(Snowball.class);
        snowball.setVelocity(player.getLocation().getDirection().multiply(2.0));
        player.getWorld().playSound(player.getLocation(), Sound.CAT_MEOW, 0.5f, 1.5f);
        player.sendMessage(ChatColor.AQUA + "Pew!");
    }
    
    @EventHandler(priority = EventPriority.HIGH)
    public void onProjectileHit(ProjectileHitEvent event) {
        Projectile projectile = event.getEntity();
        
        if (!(projectile instanceof Snowball)) {
            return;
        }
        
        Snowball snowball = (Snowball) projectile;
        
        if (!(snowball.getShooter() instanceof Player)) {
            return;
        }
        
        Player shooter = (Player) snowball.getShooter();
        snowball.getWorld().playSound(snowball.getLocation(), Sound.CAT_MEOW, 1.0f, 1.0f);
        
        if (shooter.isOnline()) {
            shooter.sendMessage(ChatColor.LIGHT_PURPLE + "Meow! Direct hit!");
        }
    }
}