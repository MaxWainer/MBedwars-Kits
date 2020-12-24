package de.marcely.bedwarsaddon.kits;

import java.util.Random;

import de.marcely.bedwars.api.event.arena.ArenaStatusChangeEvent;
import de.marcely.bedwars.api.event.player.PlayerJoinArenaEvent;
import de.marcely.bedwars.api.event.player.PlayerQuitArenaEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class Events implements Listener {
	
	public static Random rand = new Random();
	
	@EventHandler
	public void onPlayerJoinArenaEvent(PlayerJoinArenaEvent event) {
		//if(BedwarsAddonKits.kits.size() >= 1)
		//	BedwarsAddonKits.selectedKits.put(event.getPlayer(), BedwarsAddonKits.kits.get(rand.nextInt(BedwarsAddonKits.kits.size())));
	}
	
	@EventHandler
	public void onPlayerQuitArenaEvent(PlayerQuitArenaEvent event) {
		//BedwarsAddonKits.selectedKits.remove(event.getPlayer());
	}
	
	@EventHandler
	public void onArenaStatusUpdateEvent(final ArenaStatusChangeEvent event){
		/**if(BedwarsAddonKits.kits.size() >= 1 && event.getOldStatus() == ArenaStatus.LOBBY && event.getNewStatus() == ArenaStatus.RUNNING) {
			new BukkitRunnable(){
				@Override
				public void run(){
					for(Player player:event.getArena().getPlayers()){
						for(ItemStack is : BedwarsAddonKits.selectedKits.get(player).getItems())
							player.getInventory().addItem(is);
					}
				}
			}.runTaskLater(BedwarsAddonKits.plugin, 20);
		}**/
	}
}
