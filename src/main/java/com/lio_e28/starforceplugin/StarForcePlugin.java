package com.lio_e28.starforceplugin;

import com.lio_e28.starforceplugin.command.*;

import com.lio_e28.starforceplugin.event.NpcDamagePreventer;
import com.lio_e28.starforceplugin.event.StarForceClickEvent;

import com.lio_e28.starforceplugin.game.GlobalScoreboard;
import com.lio_e28.starforceplugin.item.StarForceRecipe;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

import static com.lio_e28.starforceplugin.game.PlayerMaxGradeManager.loadScores;
import static com.lio_e28.starforceplugin.game.PlayerMaxGradeManager.saveScores;

public final class StarForcePlugin extends JavaPlugin {
    private static StarForcePlugin instance;
    private Map<String, Integer> scores = new HashMap<>();

    public static StarForcePlugin getInstance() {
        return instance;
    }
    @Override
    public void onEnable() {
        instance = this;
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new StarForceClickEvent(), this);
        getServer().getPluginManager().registerEvents(new NpcDamagePreventer(), this);

        getServer().getPluginCommand("givestarforce").setExecutor(new GiveStarForceCommand());
        getServer().getPluginCommand("spawnsmithingnpc").setExecutor(new SpawnSmithingNpcCommand());
        getServer().getPluginCommand("spawnclericnpc").setExecutor(new SpawnClericNpcCommand());
        getServer().getPluginCommand("removenpc").setExecutor(new RemoveNpcCommand());
        getServer().getPluginCommand("givescroll").setExecutor(new GiveScrollCommand());


        GlobalScoreboard scoreboardHandler = new GlobalScoreboard ();
        scoreboardHandler.initializeScoreboard();

        StarForceRecipe.registerStarForceRecipe(this);

        loadScores();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        saveScores();
    }







}
