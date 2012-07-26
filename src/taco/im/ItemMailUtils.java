package taco.im;

import org.bukkit.Material;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class ItemMailUtils {
	
	private ChatColor errorColor = ChatColor.DARK_RED;
	private ChatColor intPlayerColor = ChatColor.WHITE;
	private ChatColor itemColor = ChatColor.AQUA;
	private String imTag = "[ItemMail] ";
	
	
	public Boolean isNumeric(String s){
		try{
			Integer.parseInt(s);
			return true;
		}catch(NumberFormatException e){
			return false;
		}
	}
	
	public boolean testPlayerName(Player player, String targetname) {
		if(player.getServer().getPlayer(targetname) == null) return false;
		else return true;
	}
	
	public boolean testMaterial(String material) {
		if(Material.getMaterial(material) == null) return false;
		else return true;
	}
	
	public boolean testMaterial(int id) {
		if(Material.getMaterial(id) == null) return false;
		else return true;
	}
	
	public String canOnlyBeDoneByPlayer(){
		return imTag + "This can only be done by a player.";
	}
	
	public String cantSendToSelf(){
		return errorColor + imTag + "Why would you send mail to yourself? You're silly.";
	}
	
	public String noSuchBlock(String items){
		return errorColor + imTag + "No such Block/Item " + itemColor + items;
	}
	
	public String noSuchInteger(String integer){
		return errorColor + imTag + intPlayerColor + integer + errorColor + " is not a number.";
	}
	
	public String playerNotFound(String targetName){
		return errorColor + imTag + intPlayerColor + targetName + errorColor +
				" is not online. Do you have an imaginary friend?";
	}
}
