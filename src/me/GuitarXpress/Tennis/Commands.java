package me.GuitarXpress.Tennis;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.entity.Slime;

public class Commands implements CommandExecutor {
	
	Main plugin;
	
	public Commands(Main plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] arg) {

		if (!(sender instanceof Player)) {
			sender.sendMessage("Only players can use this command!");
			return true;
		}

		Player player = (Player) sender;
		if (player.hasPermission("tennis.use")) {

			if (cmd.getName().equalsIgnoreCase("tennis")) {
				if (arg.length == 0) {
					player.sendMessage("§eHave fun!");
					player.getInventory().addItem(ItemManager.racket);
					player.getInventory().addItem(ItemManager.slimeBall);
					player.getInventory().addItem(ItemManager.killSlimeBall);
					return true;
				}
				if (arg.length == 1) {
					switch (arg[0]) {
					case "racket":
						player.sendMessage("§eHave fun!");
						player.getInventory().addItem(ItemManager.racket);
						break;
					case "ball":
						player.getInventory().addItem(ItemManager.slimeBall);
						break;
					case "killnear":
						if (player.hasPermission("tennis.admin")) {
							player.getInventory().addItem(ItemManager.killNear);
							player.sendMessage(
									"§eAdded Kill Near to your inventory");
						} else {
							player.sendMessage("§cHey! You can't do that!");
						}
						break;
					case "kill":
						if (plugin.playerSlimeballs.containsKey(player.getUniqueId().toString())) {
							for (Slime s : plugin.playerSlimeballs.get(player.getUniqueId().toString())) {
								plugin.playerSlimeballs.remove(player.getUniqueId().toString());
								s.setHealth(0);
							}
						}
						player.sendMessage(prefix() + "§eKilled all Slime Tennis Ball");
					}
				}
				if (arg.length >= 2) {
					player.sendMessage("§cToo many arguments!");
				}
				return true;
			}
		} else {
			player.sendMessage("§cSorry! §7You don't have permisson for that");
			return true;
		}
		return false;
	}

	public static String prefix() {
		return "§8[§bTennis§8] ";
	}

}
