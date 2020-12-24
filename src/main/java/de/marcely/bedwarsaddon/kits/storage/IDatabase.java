package de.marcely.bedwarsaddon.kits.storage;

import de.marcely.bedwarsaddon.kits.BWPlayer;
import de.marcely.bedwarsaddon.kits.builders.BWKit;

public interface IDatabase {

    BWKit getPlayerKit(BWPlayer player);
    BWKit getPlayerKit(String uuid);

}
