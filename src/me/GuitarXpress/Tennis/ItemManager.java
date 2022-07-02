package me.GuitarXpress.Tennis;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemManager {

	public static ItemStack racket;
	public static ItemStack slimeBall;
	public static ItemStack killSlimeBall;
	public static ItemStack killNear;
	
	public static void init() {
		createRacket();
		createSlimeBall();
		createKillSlimeBall();
		createKillNear();
	}
	
	private static void createRacket() {
		ItemStack item = new ItemStack(Material.STICK, 1);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("§6Tennis Racket");
		meta.setCustomModelData(30051);
		ArrayList<String> lore = new ArrayList<>();
		lore.add("§7Yes it's just a stick.");
		lore.add("§6Right Click §7on the ball to use.");
		meta.setLore(lore);
		item.setItemMeta(meta);
		racket = item;
	}
	
	private static void createSlimeBall() {
		ItemStack item = new ItemStack(Material.SLIME_BALL, 1);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("§6Spawn Slime Tennis Ball");
		meta.setCustomModelData(30052);
		ArrayList<String> lore = new ArrayList<>();
		lore.add("§7Right click a block to spawn a Slime Tennis Ball");
		meta.setLore(lore);
		item.setItemMeta(meta);
		slimeBall = item;
	}
	
	private static void createKillSlimeBall() {
		ItemStack item = new ItemStack(Material.BONE, 1);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("§6Kill Slime Tennis Ball");
		meta.setCustomModelData(30053);
		ArrayList<String> lore = new ArrayList<>();
		lore.add("§7Right click a block to kill the Slime Tennis Ball");
		meta.setLore(lore);
		item.setItemMeta(meta);
		killSlimeBall = item;
	}
	
	private static void createKillNear() {
		ItemStack item = new ItemStack(Material.BONE, 1);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("§6Kill Near Slime Tennis Ball");
		meta.setCustomModelData(30054);
		ArrayList<String> lore = new ArrayList<>();
		lore.add("§7Right click a block to kill all the Slime Tennis Ball");
		meta.setLore(lore);
		item.setItemMeta(meta);
		killNear = item;
	}
	
	
}
