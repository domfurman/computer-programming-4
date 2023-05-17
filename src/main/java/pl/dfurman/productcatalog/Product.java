package pl.dfurman.productcatalog;

import java.math.BigDecimal;
import java.util.UUID;

public class Product {

    private final UUID uuid;
    private final String name;
    private final String desc;
    private BigDecimal price;
    private boolean online;
    private String image;

    public Product(UUID uuid, String name, String desc) {

        this.uuid = uuid;
        this.name = name;
        this.desc = desc;
    }

    public String getId() {
        return uuid.toString();
    }

    //public UUID getUUID() {
    //    return UUID.fromString(uuid);
    //}

    public BigDecimal getPrice() {
        return price;
    }

    public void changePrice(BigDecimal price) {
        this.price = price;
    }

    public void setOnline(boolean online) {
        this.online=online;
    }

    public boolean getOnline() {
        return online;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return desc;
    }

    public void setImage(String imageKey) {
        image = imageKey;
    }

    public String getImage() {
        return image;
    }
}
