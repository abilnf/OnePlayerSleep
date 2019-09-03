/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.nicole.oneplayersleepalerts;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerBedEnterEvent.BedEnterResult;
import org.bukkit.plugin.Plugin;

/**
 *
 * @author Nicole
 */
public class SleepListener implements Listener {
    OnePlayerSleepAlerts instance;
    
    /**
     * Constructor
     * @param instance 
     */
    public SleepListener(final OnePlayerSleepAlerts instance) {
        instance.getServer().getPluginManager().registerEvents((Listener) this, (Plugin) instance);
        this.instance = instance;
    }
    
    /**
     * Sleep Event
     */
    @EventHandler
    public void onPlayerSleep(final PlayerBedEnterEvent e) {
        //There's nothing to do if sleep fails
        if (e.getBedEnterResult() != BedEnterResult.OK) return;                             
               
        //Message server immediately
        e.getPlayer().getServer().broadcastMessage(String.format("%s%s has entered their bed.", ChatColor.LIGHT_PURPLE, e.getPlayer().getName()));
        
        //Sleep after a moment
        Bukkit.getServer().getScheduler().runTaskLater((Plugin) this.instance, (Runnable) new Runnable() {
           @Override
           public void run() {
               //Check to make sure the player is still in bed
               if (!e.getPlayer().isSleeping()) return;
               
               //Skip time & weather
               e.getPlayer().getWorld().setTime(0L);
               e.getPlayer().getWorld().setStorm(false);
               e.getPlayer().getWorld().setThundering(false);
           }
        }, 100L);
    }
}
