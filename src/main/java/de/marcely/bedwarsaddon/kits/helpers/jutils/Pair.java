package de.marcely.bedwarsaddon.kits.helpers.jutils;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data@AllArgsConstructor
public class Pair<K, V> {

    private final K key;
    private final V value;

}
