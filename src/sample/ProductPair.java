package sample;

public class ProductPair {
    String name;
    Float quantity;

    public ProductPair(String name, Float quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public ProductPair() {
    }

    public ProductPair(ProductPair other) {
        if(this!=other && other!=null) {
            this.name = other.name;
            this.quantity = other.quantity;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getQuantity() {
        return quantity;
    }

    public void setQuantity(Float quantity) {
        this.quantity = quantity;
    }
}
