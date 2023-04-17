package pl.dfurman.productcatalog;

import java.math.BigDecimal;
import java.util.UUID;

public class Product {

    private final String uuid;
    private final String name;
    private final String desc;
    private BigDecimal price;
    private String imageKey;
    private boolean online;

    public Product(UUID uuid, String name, String desc) {

        this.uuid = uuid.toString();
        this.name = name;
        this.desc = desc;
    }

    public String getId() {
        return uuid;
    }

    public UUID getUUID() {
        return UUID.fromString(uuid);
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void changePrice(BigDecimal price) {
        this.price = price;
    }

    public String getImageKey() {
        return imageKey;
    }

    public boolean setOnline() {
        return online;
    }

    public boolean getOnline() {
        return online;
    }
}
