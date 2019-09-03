/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.nicole.oneplayersleepalerts;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.scheduler.BukkitRunnable;

/**
 *
 * @author Nicole
 */
public class SleepRunnable implements Runnable {
    final PlayerBedEnterEvent e;
    Player player;
    boolean cancelled;
    
    public SleepRunnable(PlayerBedEnterEvent e) {
        this.e = e;
        player = e.getPlayer();
        this.cancelled = false;
    }
    
    @Override
    public void run() {
        //Check to make sure the player is still in bed or the event was cancelled.
        if (!e.getPlayer().isSleeping() || cancelled) return;

        //Skip time & weather
        e.getPlayer().getWorld().setTime(0L);
        e.getPlayer().getWorld().setStorm(false);
        e.getPlayer().getWorld().setThundering(false);
        
        //Set to cancelled
        this.cancelled = true;
    }
    
    public void cancelEvent() {
        //Cancel the event
        this.cancelled = true;
    }
}
