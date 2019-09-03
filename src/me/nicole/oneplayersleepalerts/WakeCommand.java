/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.nicole.oneplayersleepalerts;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 *
 * @author Nicole
 */
public class WakeCommand implements CommandExecutor {
    OnePlayerSleepAlerts instance;
    
    public WakeCommand(final OnePlayerSleepAlerts instance) {
        instance.getCommand("wakeplayer").setExecutor(this);
        this.instance = instance;
    }
    
    @Override
    public boolean onCommand(CommandSender cs, Command cmnd, String string, String[] args) {
        //Incorrect args count
        if (args.length != 1) {
            cs.sendMessage(String.format("%sRequires one argument in the form of a player.", ChatColor.RED));
            return true;
        }
        
        //Ensure the player is a player
        Player target = Bukkit.getPlayer(args[0]);
        
        if (target == null) {
            cs.sendMessage(String.format("%sMust be a valid player.", ChatColor.RED));
            return true;
        }
        
        //If the player is sleeping, wake them up
        if (target.isSleeping()) {
            //Wake
            target.wakeup(true);
        }
        
        return true;
    }
}
