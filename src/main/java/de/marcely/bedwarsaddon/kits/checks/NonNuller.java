package de.marcely.bedwarsaddon.kits.checks;

public class NonNuller {

    public static void check(Object in, String errorMessage) {
        if(in == null)
            throw new NullPointerException(errorMessage);
    }

    public static <T> T revert(T in, T error) {
        if(in == null)
            return error;
        else
            return in;
    }

}
