package de.marcely.bedwarsaddon.kits.storage.types;

import de.marcely.bedwarsaddon.kits.BWPlayer;
import de.marcely.bedwarsaddon.kits.builders.BWKit;
import de.marcely.bedwarsaddon.kits.storage.IDatabase;

public class MySQL implements IDatabase {
    @Override
    public BWKit getPlayerKit(BWPlayer player) {
        return null;
    }

    @Override
    public BWKit getPlayerKit(String uuid) {
        return null;
    }
}
