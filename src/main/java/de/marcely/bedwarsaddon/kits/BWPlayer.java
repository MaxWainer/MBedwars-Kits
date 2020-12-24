package de.marcely.bedwarsaddon.kits;

import de.marcely.bedwarsaddon.kits.builders.BWKit;
import lombok.Getter;
import org.bukkit.entity.Player;

@Getter
public class BWPlayer {

    private final Player player;

    public BWPlayer(Player player) {
        this.player = player;
    }

    public BWKit getTempKit() {
        return BWKitAddon.getCachedStorage().getKitMap().get(of(player));
    }

    public BWKit getPermanentKit() {
        return BWKitAddon.getDbProvider().getPlayerKit(of(player));
    }

    public BWPlayer of(Player player) {
        return new BWPlayer(player);
    }

}
