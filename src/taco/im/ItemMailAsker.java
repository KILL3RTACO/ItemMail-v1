package taco.im;

import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ItemMailAsker{

	HashMap<Player, Boolean> hasRequest = new HashMap<Player, Boolean>();		//If Player has a request
	HashMap<Player, Player> requestToFro = new HashMap<Player, Player>();		//TO, FROM (TARGET, SENDER)
	HashMap<Player, ItemStack> requested = new HashMap<Player, ItemStack>();	//FROM, items
	
	ChatColor errorColor = ChatColor.DARK_RED;
	ChatColor successColor = ChatColor.DARK_GREEN;
	ChatColor intPlayerColor = ChatColor.WHITE;
	ChatColor itemColor = ChatColor.DARK_AQUA;
	
	private String imTag = "[ItemMail] ";
	ItemMailUtils utils = new ItemMailUtils();
	ItemMailSender ims = new ItemMailSender();
	
	public void askPlayer(Player sender, Player target, ItemStack items){
		hasRequest.put(target, true);
		requestToFro.put(target, sender);
		requested.put(sender, items);
		sendSuccessMessages(sender, target, items);
	}
	
	public void acceptRequest(Player player){
		if(hasRequest.get(player) == null || hasRequest.get(player) == false){
			player.sendMessage(errorColor + imTag + "You do not have any requests");
		}else{
			Player rSender = requestToFro.get(player);
			ItemStack rItems = requested.get(player);
			if(player.getServer().getPlayer(rSender.getName()) == null){ //player not online
				player.sendMessage(utils.playerNotFound(rSender.getName()));
			}else{ //they are online
				ims.sendItem(player, rSender, rItems);
				if(player.getInventory().contains(rItems.getType(), rItems.getAmount())){
					hasRequest.put(player, false);
				}
			}
		}
	}
	
	public void declineRequest(Player player){
		if(hasRequest.get(player) == null || hasRequest.get(player) == false){
			player.sendMessage(errorColor + imTag + "You do not have any requests");
		}else{
			player.sendMessage(successColor + imTag + "You declined " + intPlayerColor + requestToFro.get(player).getName() + successColor +
					"'s request.");
			hasRequest.put(player, false);
		}
	}
	
	public void getCurrentRequest(Player player){
		if(hasRequest.get(player) == null || hasRequest.get(player) == false){
			player.sendMessage(errorColor + imTag + "You do not have any requests");
		}else{
			Player rSender = requestToFro.get(player);
			ItemStack rItems = requested.get(player);
			
			player.sendMessage(successColor + imTag + "Current request: " + intPlayerColor + rItems.getAmount() + " " + itemColor + 
					rItems.getType() + " " + successColor + "(" + intPlayerColor + rSender.getName() + successColor + ")");
		}
	}
	
	private void sendSuccessMessages(Player sender, Player target, ItemStack items){
		sender.sendMessage(successColor + imTag + "You have asked " + intPlayerColor + target.getName() + successColor + " for " + 
				intPlayerColor + items.getAmount() + " " + itemColor + items.getType());
	}
}
