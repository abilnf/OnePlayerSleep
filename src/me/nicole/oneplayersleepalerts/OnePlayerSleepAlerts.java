/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.nicole.oneplayersleepalerts;

import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author Nicole
 */
public class OnePlayerSleepAlerts extends JavaPlugin {
    public void onEnable() {
        new SleepListener(this);
    }
}
