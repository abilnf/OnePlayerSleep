/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.nicole.oneplayersleepalerts;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerBedEnterEvent.BedEnterResult;
import org.bukkit.event.player.PlayerBedLeaveEvent;
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
        
        //Create runnable and add to list
        SleepRunnable sr = new SleepRunnable(e);
        instance.addSleepTask(sr);
        
        //Create text event
        TextComponent message = new TextComponent(String.format("%s%s has entered their bed. Click here to wake them.", ChatColor.LIGHT_PURPLE, e.getPlayer().getName()));
        message.setClickEvent(new ClickEvent( ClickEvent.Action.RUN_COMMAND, String.format("/wakeplayer %s", e.getPlayer().getName())));
               
        //Message server immediately
        e.getPlayer().getServer().spigot().broadcast(message);
        
        //Sleep after a moment
        Bukkit.getServer().getScheduler().runTaskLater((Plugin) this.instance, sr, 100L);
    }
    
    /**
     * Wake Event
     */
    @EventHandler
    public void onPlayerWake(final PlayerBedLeaveEvent e) {
        //Cancel the player's sleep event
        instance.cancelSleepTask(e.getPlayer());
    }
}
