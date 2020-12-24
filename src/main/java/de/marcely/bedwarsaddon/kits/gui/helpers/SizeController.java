package de.marcely.bedwarsaddon.kits.gui.helpers;

public class SizeController {

    public static boolean isAllowed(int input) throws Exception {
        switch (input) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                return true;
            default:
                throw new Exception("Error while drawing menu! Input size is: " + input + ", maximum: 6");
        }
    }

}
