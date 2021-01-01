package de.marcely.bedwarsaddon.kits.gui.helpers;

public class SizeController {

    public static int isAllowed(int input) throws Exception {
        switch (input) {
            case 1:
                return 1;
            case 2:
                return 2;
            case 3:
                return 3;
            case 4:
                return 4;
            case 5:
                return 5;
            case 6:
            default:
                return 6;
        }
    }

}
