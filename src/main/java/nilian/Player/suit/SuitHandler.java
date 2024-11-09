package nilian.Player.suit;

import java.util.ArrayList;
import java.util.List;

public class SuitHandler {

    private static final List<PlayerSuit> availableSuits = new ArrayList<>();
    private static final ArrayList<String> allSuitNames = new ArrayList<>();

    /**
     * Loads all suits classes
     */
    public static void loadAllSuits() {
        // suit_1
        PlayerSuit suit_1 = new PlayerSuit("/Player/suit_1", "suit_1");
        suit_1.setImagesNum(4, 4, 6, 6);
        suit_1.loadImages();
        suit_1.loadFrames();
        availableSuits.add(suit_1);
        allSuitNames.add("suit_1");

        // Fighter
        PlayerSuit fighter = new PlayerSuit("/Player/Fighter", "fighter");
        fighter.setImagesNum(6, 10, 8, 8);
        fighter.loadImages();
        fighter.loadFrames();
        availableSuits.add(fighter);
        allSuitNames.add("fighter");

        // Samurai
        PlayerSuit samurai = new PlayerSuit("/Player/Samurai", "samurai");
        samurai.setImagesNum(6, 10, 8, 8);
        samurai.loadImages();
        samurai.loadFrames();
        availableSuits.add(samurai);
        allSuitNames.add("samurai");

        // Samurai
        PlayerSuit shinobi = new PlayerSuit("/Player/Shinobi", "shinobi");
        shinobi.setImagesNum(6, 10, 8, 8);
        shinobi.loadImages();
        shinobi.loadFrames();
        availableSuits.add(shinobi);
        allSuitNames.add("shinobi");
    }


    /**
     * returns wanted suit
     * @param suitName name of the suit
     * @return PlayerSuit Object
     */
    public static PlayerSuit getSuit(String suitName) {
        for(PlayerSuit suit: availableSuits) {
            if(suit.getSuitName().equals(suitName)) {
                return suit;
            }
        }
        return null;
    }

    public static ArrayList<String> getAllSuitNames() {
        return allSuitNames;
    }
}
