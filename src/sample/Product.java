package sample;

public class Product {
    String name;
    Float price;
    Float quantity;
    Float total;

    public Product(String name, Float price, Float quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.updateTotal();
    }

    public Product() {
    }

    public Product(Product other) {
        if(this!=other && other!=null){
            this.name = other.name;
            this.price = other.price;
            this.quantity = other.quantity;
            this.total = other.total;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Float getQuantity() {
        return quantity;
    }

    public void setQuantity(Float quantity) {
        this.quantity = quantity;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public void updateTotal(){
        this.setTotal(this.getPrice()*this.getQuantity());
    }

    public void updateQuantity(float quantity){
        this.setQuantity(quantity);
        this.updateTotal();
    }

    public void updatePrice(float price){
        this.setPrice(price);
        this.updateTotal();
    }

    public void updateName(String name){
        this.setName(name);
    }

}