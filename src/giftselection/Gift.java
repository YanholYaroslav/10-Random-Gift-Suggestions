package giftselection;

/**
 * A class representing a gift and containing basic information about 
 * it.
 */
public class Gift {
    private String giftName;    // Name of the gift
    private double price;       // Price of the gift
    private String description; // Description of the gift

    /**
     * Constructor for the Gift class.
     *
     * @param giftName    Name of the gift.
     * @param price       Price of the gift.
     * @param description Description of the gift.
     */
    public Gift(String giftName, double price, String description) {
        this.giftName = giftName;
        this.price = price;
        this.description = description;
    }

    /**
     * Get the name of the gift.
     *
     * @return Name of the gift.
     */
    public String getGiftName() {
        return giftName;
    }

    /**
     * Set the name of the gift.
     *
     * @param giftName Name of the gift.
     */
    public void setGiftName(String giftName) {
        this.giftName = giftName;
    }

    /**
     * Get the price of the gift.
     *
     * @return Price of the gift.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Set the price of the gift.
     *
     * @param price Price of the gift.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Get the description of the gift.
     *
     * @return Description of the gift.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the description of the gift.
     *
     * @param description Description of the gift.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Overridden toString() method for easy printing of information 
     * about the object.
     *
     * @return String with information about the object.
     */
    @Override
    public String toString() {
        return "Gift{" +
                "giftName='" + giftName + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                '}';
    }
}
