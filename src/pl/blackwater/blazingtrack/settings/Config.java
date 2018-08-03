package pl.blackwater.blazingtrack.settings;

import java.util.List;
import java.util.UUID;

import org.bukkit.configuration.file.FileConfiguration;

import pl.blackwater.blazingtrack.Main;
import pl.blackwaterapi.configs.ConfigCreator;

public class Config extends ConfigCreator
{
	public static String SERVER_IP;
    public static List<String> BLAZINGTRACK_UUID_LIKE;
    public Config() {
		super("config.yml", ";)", Main.getPlugin());
		FileConfiguration config = getConfig();
		SERVER_IP = config.getString("server.ip");
		BLAZINGTRACK_UUID_LIKE = config.getStringList("server.likeduuid");
	}
    public void addLikedUUID(UUID u){
    	addToListField("server.likeduuid", u.toString());
    	new Config();
    }
    
}
