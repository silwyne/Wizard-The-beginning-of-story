package nilian.Player.suit;

import java.util.ArrayList;
import java.util.List;

public class SuitHandler {

    private static final List<PlayerSuit> availableSuits = new ArrayList<>();

    /**
     * Loads all suits classes
     */
    public static void loadAllSuits() {
        // suit_1
        PlayerSuit suit_1 = new PlayerSuit("/Player/suit_1");
        suit_1.setImagesNum(4, 4, 6, 6);
        suit_1.getImages();
        suit_1.separate();
        availableSuits.add(suit_1);

        // Fighter
        PlayerSuit fighter = new PlayerSuit("/Player/Fighter");
        fighter.setImagesNum(6, 10, 8, 8);
        fighter.getImages();
        fighter.separate();
        availableSuits.add(fighter);
    }
}
