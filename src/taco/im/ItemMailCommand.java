package taco.im;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ItemMailCommand implements CommandExecutor{
	
	ItemMail plugin = null;
	ItemMailUtils utils = new ItemMailUtils();
	ItemMailSender ims = new ItemMailSender();
	ItemMailAsker ima = new ItemMailAsker();
	
	public ItemMailCommand(ItemMail instance) {
		this.plugin = instance;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String cmdLabel, String[] args){
		if(sender instanceof Player) {
			Player player = (Player) sender;
			getCommand(player, args);
			return true;
		}
		return false;
	}
	
	private void getCommand(Player player, String[] args) {
		if(args.length == 0) {
			
		}else if((args[0].equalsIgnoreCase("send")) || 
			(args[0]).equalsIgnoreCase("s")) {
			sendCommand(player, args);
		}
	}
	
	private void sendCommand(Player player, String[] args) {
		String playername = null;
		Material material = null;
		int amount = 1;
		if(args.length >= 2) playername = args[1];
		if(args.length == 2) {
			if(utils.testPlayerName(player, playername) == false) {
				player.sendMessage(utils.playerNotFound(playername));
			}else {
				ims.sendItem(player, player.getServer().getPlayer(playername), player.getItemInHand());
			}
		}else if(args.length == 3) {
			
		}
	}
	
	private void help(Player player) {
		ChatColor tc = ChatColor.GOLD;
		ChatColor cc = ChatColor.AQUA;
		ChatColor pc = ChatColor.DARK_AQUA;
		ChatColor dc = ChatColor.GRAY;
		player.sendMessage(tc + ".o0o.____________[ItemMail Commands]____________.o0o.");
		player.sendMessage(cc + "/im send" + pc + " <player> [id | name] [#]" + dc + "   send items");
		player.sendMessage(cc + "/im ask" + pc + "  <player> <id | name> [#]" + dc + "   ask for items");
	}
	
	private boolean isMaterial(String material) {
		if(Material.getMaterial(material) == null) {
			return false;
		}else { return true; }
	}
	
	private boolean isMaterial(int material) {
		if(Material.getMaterial(material) == null) {
			return false;
		}else { return true; }
	}

	
}
