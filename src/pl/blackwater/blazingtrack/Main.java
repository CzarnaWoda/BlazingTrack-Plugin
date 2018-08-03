package pl.blackwater.blazingtrack;

import org.bukkit.plugin.java.JavaPlugin;
import org.json.simple.JSONObject;

import lombok.Getter;
import pl.blackwater.blazingtrack.commands.BlazingTrackCommand;
import pl.blackwater.blazingtrack.connection.BlazingTrackConnection;
import pl.blackwater.blazingtrack.settings.Config;
import pl.blackwaterapi.API;

public class Main extends JavaPlugin{
	@Getter private static Main plugin;
	public void onEnable(){
		plugin = this;
		API.registerConfig(new Config());
		new Config();
		System.out.println("» Laczenie z BlazingTrack....");
		if(BlazingTrackConnection.checkConnection()){
			System.out.println("» Serwer zostal znaleziony i blazingtrack dziala poprawnie");
			JSONObject json = BlazingTrackConnection.getJSONBlazingTrackServerInfo();
			send("Nazwa Serwera: " + json.get("name"));
			send("Address Serwera: " + json.get("address"));
			send("Polubienia: " + json.get("likes"));
			send("Pozycja: " + json.get("position"));
			send("Data Startu: " + json.get("startdate"));
			send("Blazing Level: " + json.get("blazing_level"));
		}else{
			System.out.println("» Nie udalo sie poloaczyc do BlazingTrack, sprawdz SERVER_IP w config.yml !");
		}
		API.registerCommand(new BlazingTrackCommand());
	}
	public void onDisable(){
		
	}
	private static void send(String s){
		System.out.println("(BlazingTrack) » " + s);
	}
}
