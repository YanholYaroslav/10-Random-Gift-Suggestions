package tests;

import giftselection.Gift;
import giftselection.GiftSelector;

import java.util.List;

public class TestGiftSelector {

    public static void main(String[] args) {
        // Create some sample gifts
        Gift gift1 = new Gift("Book", 20.0, "A good read for any occasion");
        Gift gift2 = new Gift("Watch", 100.0, "Classy and stylish timepiece");
        Gift gift3 = new Gift("Plant", 15.0, "A gift that keeps on growing");

        // Create a GiftSelector instance
        GiftSelector giftSelector = new GiftSelector(List.of(gift1, gift2, gift3));

        // Test selecting a random gift
        Gift randomGift = giftSelector.selectRandomGift();
        System.out.println("Randomly Selected Gift: " + randomGift);

        // Test selecting a gift by criterion (e.g., maximum price)
        double maxPrice = 50.0;
        Gift selectedGift = giftSelector.selectGiftByCriterion(maxPrice);
        if (selectedGift != null) {
            System.out.println("Selected Gift within $" + maxPrice + ": " + selectedGift);
        } else {
            System.out.println("No gift found within $" + maxPrice);
        }

        // Test retrieving all available gifts
        List<Gift> allAvailableGifts = giftSelector.getAllAvailableGifts();
        System.out.println("All Available Gifts:");
        for (Gift gift : allAvailableGifts) {
            System.out.println(gift);
        }

        // Test adding a new gift
        Gift newGift = new Gift("Headphones", 50.0, "High-quality sound experience");
        giftSelector.addGift(newGift);

        // Test removing a gift
        boolean removalSuccess = giftSelector.removeGift(gift2);
        if (removalSuccess) {
            System.out.println("Gift removed successfully.");
        } else {
            System.out.println("Failed to remove gift.");
        }

        // Display the final list of available gifts
        System.out.println("Final List of Available Gifts:");
        allAvailableGifts = giftSelector.getAllAvailableGifts();
        for (Gift gift : allAvailableGifts) {
            System.out.println(gift);
        }
    }
}

