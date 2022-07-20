package me.GuitarXpress.Tennis;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Slime;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class Events implements Listener {

	Main plugin;

	public Events(Main plugin) {
		this.plugin = plugin;
	}

	private Slime slime;

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		if (plugin.playerSlimeballs.containsKey(event.getPlayer().getUniqueId().toString())) {
			if (plugin.playerSlimeballs.get(event.getPlayer().getUniqueId().toString()).size() > 0) {
				Bukkit.dispatchCommand(event.getPlayer(), "tennis kill");
			}
		}
	}

	@EventHandler
	public void onEntityClick(PlayerInteractEntityEvent event) {
		if (event.getRightClicked().getType() == EntityType.SLIME) {
			Player player = event.getPlayer();
			if (player.getInventory().getItemInMainHand().hasItemMeta()) {
				if (player.getInventory().getItemInMainHand().getItemMeta().equals(ItemManager.racket.getItemMeta())) {
					for (Entity e : player.getNearbyEntities(4, 4, 4)) {
						if (e instanceof Slime && e.getName().equals("§6Ball")) {
							e.setVelocity(
									player.getLocation().getDirection().multiply(plugin.multiply).setY(plugin.setY));
							player.playSound(player.getLocation(), Sound.BLOCK_WOOD_BREAK, 10.0f, 3.0f);
							player.spawnParticle(Particle.SMOKE_NORMAL, e.getLocation(), 5);
						}
					}
				}
			}
		}
	}

	@EventHandler
	public void onRightClick(PlayerInteractEvent event) {
		if (event.getItem() != null) {
			if (event.getItem().hasItemMeta()) {
				if (event.getItem().getItemMeta().equals(ItemManager.killSlimeBall.getItemMeta())) {
					event.setCancelled(true);
					Player player = event.getPlayer();
					if (killSlimes(player)) {
						player.sendMessage(Commands.prefix() + "§eKilled Slime Tennis Ball");
					} else {
						player.sendMessage(Commands.prefix() + "§cNo slimes to kill.");
					}
				}
			} else if (event.getItem().getItemMeta().equals(ItemManager.killNear.getItemMeta())) {
				Player player = event.getPlayer();
				killSlimesNearby(player);
				player.sendMessage(Commands.prefix() + "§eKilled all nearby Slime Tennis Ball");
			}
		}
		
		if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if (event.getItem() != null) {
				if (event.getItem().hasItemMeta()) {
					if (event.getItem().getItemMeta().equals(ItemManager.slimeBall.getItemMeta())) {
						event.setCancelled(true);
						Player player = event.getPlayer();
						World locW = event.getClickedBlock().getLocation().getWorld();
						float locX = event.getClickedBlock().getLocation().getBlockX() + .5f;
						int locY = event.getClickedBlock().getLocation().getBlockY() + 1;
						float locZ = event.getClickedBlock().getLocation().getBlockZ() + .5f;
						Location loc = new Location(locW, locX, locY, locZ);
						if (!plugin.playerSlimeballs.containsKey(player.getUniqueId().toString())) {
							plugin.playerSlimeballs.put(player.getUniqueId().toString(), new ArrayList<Slime>());
						}

						if (plugin.playerSlimeballs.get(player.getUniqueId().toString()).size() < plugin.max) {
							slime = (Slime) loc.getWorld().spawnEntity(loc, EntityType.SLIME);
							slime.setSize(0);
							slime.setCustomName("§6Ball");
							slime.setInvulnerable(true);
							slime.setAware(plugin.hasAi);

							plugin.playerSlimeballs.get(player.getUniqueId().toString()).add(slime);

							player.sendMessage(Commands.prefix() + "§eSpawned Slime Tennis Ball");
						} else {
							player.sendMessage(Commands.prefix() + "§cSorry! §7You have enough tennis balls!");
						}
					}
				}
			}
		}
	}

	@EventHandler
	public void onMobDeath(EntityDeathEvent event) {
		if (event.getEntityType() == EntityType.SLIME && event.getEntity().getName().equals("§6Ball")) {
			event.getDrops().clear();
		}
	}

	public boolean killSlimes(Player player) {
		if (plugin.playerSlimeballs.containsKey(player.getUniqueId().toString())) {
			for (Slime s : plugin.playerSlimeballs.get(player.getUniqueId().toString())) {
				s.setHealth(0);
				plugin.playerSlimeballs.remove(player.getUniqueId().toString());
			}
		} else {
			return false;
		}
		return true;
	}

	public boolean killSlimesNearby(Player player) {
		for (Entity e : player.getNearbyEntities(10, 10, 10)) {
			if (e instanceof Slime && e.getName().equals("§6Ball")) {
				e.remove();
			}
		}
		return true;
	}
}
