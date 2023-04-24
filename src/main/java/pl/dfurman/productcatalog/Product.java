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
    private String image;

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

    public void setOnline(boolean online) {
        this.online=online;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public boolean getOnline() {
        return online;
    }

    public void setImage(String imageKey) {

        image = imageKey;
    }

    public String getImage() {
        return image;
    }
}
