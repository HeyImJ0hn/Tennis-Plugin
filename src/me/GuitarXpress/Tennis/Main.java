package me.GuitarXpress.Tennis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Slime;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	public Map<String, ArrayList<Slime>> playerSlimeballs = new HashMap<>();
	public double multiply;
	public double setY;
	public int max;
	public boolean hasAi;

	public void loadConfig() {
		getConfig().options().copyDefaults(true);
		getConfig().options().copyHeader(true);
		multiply = getConfig().getDouble("Entity.Velocity.Multiply");
		setY = getConfig().getDouble("Entity.Velocity.setY");
		max = getConfig().getInt("Entity.Spawn.Max");
		hasAi = getConfig().getBoolean("Entity.Spawn.hasAi");
		saveConfig();
	}

	@Override
	public void onEnable() {
		loadConfig();
		ItemManager.init();
		getServer().getPluginManager().registerEvents(new Events(this), this);
		getCommand("tennis").setExecutor(new Commands(this));
		getCommand("tennis").setTabCompleter(new TabComplete());
		getServer().getLogger().info("[Tennis] Enabled!");
	}

	@Override
	public void onDisable() {
		getServer().getLogger().info("[Tennis] Disabled!");
	}
	

}
