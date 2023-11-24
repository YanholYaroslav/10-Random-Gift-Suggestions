package giftselection;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A class responsible for selecting gifts based on different criteria.
 */
public class GiftSelector {
    private List<Gift> availableGifts;

    /**
     * Constructor for the GiftSelector class.
     *
     * @param availableGifts List of available gifts to choose from.
     */
    public GiftSelector(List<Gift> availableGifts) {
        this.availableGifts = new ArrayList<>(availableGifts);
    }

    /**
     * Selects a random gift from the available gifts.
     *
     * @return A randomly selected gift.
     */
    public Gift selectRandomGift() {
        if (availableGifts.isEmpty()) {
            return null;
        }

        Random random = new Random();
        int randomIndex = random.nextInt(availableGifts.size());
        return availableGifts.get(randomIndex);
    }

    /**
     * Selects a gift based on a specific criterion (e.g., price range).
     *
     * @param maxPrice The maximum price for the gift.
     * @return A gift that fits the specified criterion.
     */
    public Gift selectGiftByCriterion(double maxPrice) {
        for (Gift gift : availableGifts) {
            if (gift.getPrice() <= maxPrice) {
                return gift;
            }
        }
        return null;
    }

    /**
     * Gets a list of all available gifts.
     *
     * @return List of available gifts.
     */
    public List<Gift> getAllAvailableGifts() {
        return new ArrayList<>(availableGifts);
    }

    /**
     * Adds a new gift to the available gifts.
     *
     * @param gift The gift to be added.
     */
    public void addGift(Gift gift) {
        availableGifts.add(gift);
    }

    /**
     * Removes a gift from the available gifts.
     *
     * @param gift The gift to be removed.
     * @return True if the gift was successfully removed, false 
     * otherwise.
     */
    public boolean removeGift(Gift gift) {
        return availableGifts.remove(gift);
    }
}
