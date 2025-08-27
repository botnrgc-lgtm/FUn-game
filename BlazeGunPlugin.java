package com.blazegun;

import com.blazegun.commands.BlazeRodCommand;
import com.blazegun.listeners.BlazeGunListener;
import org.bukkit.plugin.java.JavaPlugin;
public class BlazeGunPlugin extends JavaPlugin {
    
    private static BlazeGunPlugin instance;
    
    @Override
    public void onEnable() {
        instance = this;
        
        getCommand("br").setExecutor(new BlazeRodCommand());

        getServer().getPluginManager().registerEvents(new BlazeGunListener(), this);
        
        getLogger().info("BlazeGun plugin has been enabled!");
        getLogger().info("Use /br to get your blaze rod gun!");
    }
    
    @Override
    public void onDisable() {
        getLogger().info("BlazeGun plugin has been disabled!");
    }
    

    public static BlazeGunPlugin getInstance() {
        return instance;
    }
}
