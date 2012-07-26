package taco.im;

import java.util.logging.Logger;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

/*
 *         ItemMail by KILL3RTACO 
 */

public class ItemMail extends JavaPlugin{

	private final Logger log = Logger.getLogger("Minecraft");
	
	public void onDisable() {
		
		
	}

	public void onEnable() {
		PluginDescriptionFile pdfFile = this.getDescription();
		log.info("Server is now running " + pdfFile.getFullName());
		getCommand("im").setExecutor(new ItemMailCommand(this));
	}
	
	

}
