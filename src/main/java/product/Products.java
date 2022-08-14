package product;

import java.util.ArrayList;
import java.util.List;

/*Class Products contains list of products and method for checking whether product contain certain id,
 return product by given id */
public class Products {

    private final List<Product> products = new ArrayList<>();

    public List<Product> getProductsList() {
        return products;
    }

    public boolean idExists(int id){
        for(Product product: products){
            if(product.getId() == id){
                return true;
            }
        }
        return false;
    }

    public Product getProductById(int id){
        if(idExists(id)){
            for(Product product: products){
                if(product.getId() == id){
                    return product;
                }
            }
        }

        return null;
    }

    @Override
    public String toString() {
        return "List of products: " + products + "\n";
    }

}
