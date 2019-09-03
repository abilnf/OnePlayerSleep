/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.nicole.oneplayersleepalerts;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

/**
 *
 * @author Nicole
 */
public class OnePlayerSleepAlerts extends JavaPlugin {
    OnePlayerSleepAlerts instance;
    List<SleepRunnable> scheduledTasks;
    
    public void onEnable() {
        this.instance = this;
        this.scheduledTasks = new ArrayList<SleepRunnable>();
        
        new SleepListener(instance);
        new WakeCommand(instance);
    }
    
    public void addSleepTask(SleepRunnable sr) {
        this.scheduledTasks.add(sr);
    }
    
    public void cancelSleepTask(Player p) {
        List<SleepRunnable> found = new ArrayList<SleepRunnable>();
        
        //Iterate over all the runnables and cancel all with the matching player
        for (SleepRunnable sr : scheduledTasks) {
            if (sr.player == p && !sr.cancelled) {
                //Set the event to cancelled
                sr.cancelEvent();
                
                //Add to the list of removable
                found.add(sr);
            }
        }
        
        //Remove the found runnables
        this.scheduledTasks.remove(found);
    }
}
