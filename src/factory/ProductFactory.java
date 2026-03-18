package factory;

import entity.DigitalProduct;
import entity.PhysicalProduct;
import entity.Product;

public class ProductFactory {
    public static Product createProduct(int type, String id, String name, double price, double otherValue) {
        switch (type) {
            case 1:
                return new PhysicalProduct(id, name, price, otherValue);
            case 2:
                return new DigitalProduct(id, name, price, otherValue);
            default:
                return null;
        }
    }
}
