package pl.dfurman.productcatalog;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class ProductCatalogTest {

    @Test
    void itExposeEmptyProductList() {
        ProductCatalog catalog = thereIsProductCatalog();
        List<Product> products = catalog.allProducts();
        assertEmptyList(products);

    }@Test
    void publishedProductsAreEmptyForNewCatalog() {
        ProductCatalog catalog = thereIsProductCatalog();
        List<Product> products = catalog.allPublishedCatalogs();
        assertEmptyList(products);
    }

    @Test
    void itAllowsToAddProduct() {
        //Arrange
        ProductCatalog catalog = thereIsProductCatalog();
        //Act
        String productId = catalog.addProduct("lego 8398", "nic");
        //Assert
        List<Product> products = catalog.allProducts();
        assert 1 == products.size();
    }

    @Test
    void itAllowsToLoadProductDetails() {
        ProductCatalog catalog = thereIsProductCatalog();
        String productId = catalog.addProduct("lego 8398", "nic");

        Product loaded = catalog.loadById(productId);
        assert productId.equals(loaded.getId());
    }

    @Test
    void itAllowsToChangePrice() {
        ProductCatalog catalog = thereIsProductCatalog();
        String productId = catalog.addProduct("lego 8398", "nic");

        catalog.changePrice(productId, BigDecimal.valueOf(20.20));

        Product loaded = catalog.loadById(productId);
        assertEquals(BigDecimal.valueOf(20.20), loaded.getPrice());

    }

    @Test
    void itAllowsToAssignImage() {
        ProductCatalog catalog = thereIsProductCatalog();
        String productId = catalog.addProduct("lego 8398", "nic");

        catalog.assignImage(productId, "nice/picture.jpg" );

        Product loaded = catalog.loadById(productId);
        assertEquals("nice/picture.jpg", loaded.getImage());

    }

    @Test
    void productCantBePublishedWithoutImageAndPrice() {
        ProductCatalog catalog = thereIsProductCatalog();
        String productId = catalog.addProduct("lego 8398", "nic");

        assertThrows(
                ProductCantBePublishedException.class,
                () -> catalog.publish(productId)
        );
    }

    @Test
    void itAllowsToPublishProduct() {
        ProductCatalog catalog = thereIsProductCatalog();
        String productId = catalog.addProduct("lego 8398", "nic");

        catalog.assignImage(productId, "nice,jpg");
        catalog.changePrice(productId, BigDecimal.valueOf(10));

        catalog.publish(productId);

        List<Product> products = catalog.allPublishedProducts();
        assertEquals(1, products.size());
    }

    private void assertEmptyList(List<Product> products) {
        assert 0 == products.size();
    }

    private ProductCatalog thereIsProductCatalog() {
        return new ProductCatalog(
                new HashMapProductStorage()
        );
    }
}
