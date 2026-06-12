import java.util.ArrayList;

public class BPlusTreeDemo {

    static class Product {
        String category;
        int price;

        Product(String category, int price) {
            this.category = category;
            this.price = price;
        }
    }

    public static void main(String[] args) {

        ArrayList<Product> products = new ArrayList<>();

        products.add(new Product("electronics", 11800));
        products.add(new Product("electronics", 12300));
        products.add(new Product("electronics", 12900));
        products.add(new Product("electronics", 13500));
        products.add(new Product("electronics", 14100));
        products.add(new Product("electronics", 14700));
        products.add(new Product("electronics", 15400));

        System.out.println("Products in Range ₹12000 - ₹14800:");

        for(Product p : products) {
            if(p.price >= 12000 && p.price <= 14800) {
                System.out.println(p.category + " - ₹" + p.price);
            }
        }
    }
}