package pl.blackwater.blazingtrack.commands;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import pl.blackwater.blazingtrack.connection.BlazingTrackConnection;
import pl.blackwater.blazingtrack.settings.Config;
import pl.blackwaterapi.commands.PlayerCommand;
import pl.blackwaterapi.utils.Util;

public class BlazingTrackCommand extends PlayerCommand{

	public BlazingTrackCommand() {
		super("blazingtrack", "blazingtrack command", "/blazingtrack", "blazingtrack.command", new String[]{"bt"});
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean onCommand(Player p, String[] args) {
		boolean status = BlazingTrackConnection.checkPlayerLike(p);
		String blazingtrack_serverurl = "https://blazingtrack.pl/s/" + Config.SERVER_IP;
		if(status){
			Util.sendMsg(p, "     &8» &2&lBlazingTrack - &6&lJustPvP &8«     ");
			Util.sendMsg(p, "&8» &7Polubiles nasz serwer na stronie &6&n" + blazingtrack_serverurl);
			Util.sendMsg(p, Config.BLAZINGTRACK_UUID_LIKE.contains(p.getUniqueId().toString()) ? "&8» &7Dziekujemy za polubienie naszego serwer'a, &6to wplywa na nasz rozwoj !" : "&8» &7Za polubienie &6JustPvP.PL &7na stronie &6BlazingTrack &7otrzymujesz nagrode !");
			Util.sendMsg(p, "     &8» &2&lBlazingTrack - &6&lJustPvP &8«     ");
			if(!Config.BLAZINGTRACK_UUID_LIKE.contains(p.getUniqueId().toString())){
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "case givekey " + p.getName() + " chestpvp 2");
				Config config = new Config();
				config.addLikedUUID(p.getUniqueId());
			}
		}else{
			Util.sendMsg(p, "     &8» &2&lBlazingTrack - &6&lJustPvP &8«     ");
			Util.sendMsg(p, "&8» &7Nasz serwer na stronie &6BlazingTrack.PL &7nie zostal przez ciebie polubiony lub jestes u¿ytkownikiem non-premium");
			Util.sendMsg(p, "&8» &7Za polubienie serwer'a &6&n" + Config.SERVER_IP + " &7przewidziane sa nagrody od administracji !");
			Util.SendOpen_URLTextComponent(p, "&8» &7Kliknij aby przejsc do strony serwer'a na serwisie &6BlazingTrack", blazingtrack_serverurl, "&8» &7Kliknij aby przejsc &6&ndalej");
			Util.sendMsg(p, "     &8» &2&lBlazingTrack - &6&lJustPvP &8«     ");

		}
		return false;
	}


}
