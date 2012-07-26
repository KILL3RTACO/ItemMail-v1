package taco.im;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class ItemMailSender {

	PlayerInventory playerInventory;
	PlayerInventory targetInventory;
	private ChatColor errorColor = ChatColor.DARK_RED;
	private ChatColor successColor = ChatColor.DARK_GREEN;
	private ChatColor intPlayerColor = ChatColor.WHITE;
	private ChatColor itemColor = ChatColor.DARK_AQUA;
	private String imTag = "[ItemMail] ";
	
	
	public void sendItem(Player player, Player target, ItemStack items){
		playerInventory = player.getInventory();
		targetInventory = target.getInventory();
		if(!playerInventory.contains(items.getType(), items.getAmount())){
			player.sendMessage(notEnoughItems(player, items));
		}else{
			if(!canHold(target, items)){ //can't fit
				player.sendMessage(cannotFitItems(target.getName()));
			}else{
				playerInventory.removeItem(items);
				targetInventory.addItem(items);
				sendSuccessMessages(player, target, items);
			}
		}
	}	
	
	private boolean canHold(Player target, ItemStack items){
		int amount = items.getAmount();
		int emptySlots = 0;
		for(ItemStack x : target.getInventory().getContents()){
			if(x == null){
				emptySlots++;
			}else{
				amount -= (items.getMaxStackSize() - x.getAmount());
				if(amount == 0){
				return true;
				}
			}
		}

		if(amount != 0 && emptySlots == 0){
			return false;
		}else if(amount != 0 && emptySlots > 0){
			int slotsNeeded = amount/items.getMaxStackSize();
			if(amount % items.getMaxStackSize() != 0){
				slotsNeeded++;
			}
			
			if(slotsNeeded > emptySlots){
				return false;
			}else if(slotsNeeded <= emptySlots){
				return true;
			}
		}	
		return false;
	}
	
	
	private void sendSuccessMessages(Player player, Player target, ItemStack items){
		player.sendMessage(sentSuccess(target, items));
		target.sendMessage(recieveSuccess(player, items) + successColor +  " You should thank them.");
		
	}

	private String cannotFitItems(String targetName){
		return errorColor + imTag + intPlayerColor + targetName + errorColor + " cannot fit the specified items in their inventory";
	}
	
	private String notEnoughItems(Player player, ItemStack items){
		return errorColor + imTag + "You don't have " + intPlayerColor + items.getAmount() + " " + itemColor + items.getType();
	}
	
	private String recieveSuccess(Player sender, ItemStack items){
		return successColor + imTag + intPlayerColor + sender.getName() + successColor + " sent you "
				+ intPlayerColor + items.getAmount() + " " + itemColor + items.getType();
	}
	
	private String sentSuccess(Player target, ItemStack items){
		return successColor + imTag + "Successfully sent " + intPlayerColor + items.getAmount() + " " + itemColor + items.getType() +
				" to " + intPlayerColor + target.getName();
	}
}
