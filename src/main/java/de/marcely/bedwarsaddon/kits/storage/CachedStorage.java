package de.marcely.bedwarsaddon.kits.storage;

import de.marcely.bedwarsaddon.kits.BWPlayer;
import de.marcely.bedwarsaddon.kits.builders.BWKit;
import lombok.Getter;

import java.util.LinkedHashMap;
import java.util.Map;

public class CachedStorage {

    @Getter
    private Map<BWPlayer, BWKit> kitMap;

    public CachedStorage() {
        kitMap = new LinkedHashMap<>();
    }

}
