package market;

import exceptions.NotEnoughMoneyToBuy;
import product.Product;
import product.Products;
import user.User;
import user.Users;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * Class contains information about users and their products
 * 'market history' contains all information about completed purchases
 *  fields 'users' and 'products' need to keep information about all existing users and products
 */
public class Market {

    private final Map<User, List<Product>> marketHistory = new HashMap<>();
    private Users users;
    private Products products;


    /*
     * Method that purchase product and throw exception if user has not enough money;
     * here check whether users and products are exists, and if user has above or equal amount of money
     * the purchase done; also checked whether user has already done any purchases before and if did, just append
     * product to exist list of products;
     */
    public void makePurchase(int userId, int productId) throws NotEnoughMoneyToBuy {
        if (users != null && products != null) {
            if (users.idExists(userId) && products.idExists(productId)) {
                User user = users.getUserById(userId);
                Product product = products.getProductById(productId);
                if (user.getAmountOfMoney() >= product.getPrice()) {
                    user.setAmountOfMoney(user.getAmountOfMoney() - product.getPrice());
                    if (marketHistory.containsKey(user)) {
                        appendProduct(user, product);
                    } else
                        marketHistory.put(user, new ArrayList<>(List.of(product)));
                    System.out.println(user.getFirstName() + " " + user.getLastName() + " successfully bought " + product.getName() + "\n");
                } else
                    throw new NotEnoughMoneyToBuy(user.getFirstName() + " " + user.getLastName() + " has not enough money to buy " + product.getName() + "\n");
            } else
                System.err.println("Sorry! User or product was not found\n");
        } else
            System.err.println("Market is not initialised. Purchases can`t be done.\n");
    }

    /*appending products to existing list*/
    public void appendProduct(User key, Product product) {
        List<Product> productList = marketHistory.get(key);
        productList.add(product);
    }

    /////////////////////////////////////Display
    /*
     * method that display products that user buy
     * */
    public void displayProductsByUserId(int userId) {
        if (!marketHistory.isEmpty()) {
            if (marketHistory.containsKey(users.getUserById(userId))) {
                System.out.println("List of products: " + marketHistory.get(users.getUserById(userId)) + "\n");
            }
        } else
            System.err.println("Market history is not empty.\n");
    }


    /*
     * method that display users that buy certain product
     * */
    public void displayUsersByProductId(int id) {
        if (!marketHistory.isEmpty()) {
            List<User> userList = new ArrayList<>();
            for (Map.Entry<User, List<Product>> entry : marketHistory.entrySet()) {
                List<Product> productList = entry.getValue();
                for (Product product : productList) {
                    if (product.getId() == id) {
                        userList.add(entry.getKey());
                    }
                }
            }
            System.out.println("List of users: " + userList + "\n");
        } else
            System.err.println("Market history is not empty.\n");
    }

    public void displayListOfUsers() {
        if (users == null) {
            System.err.println("List of users is empty.\n");
        } else
            System.out.println(users);
    }

    public void displayListOfProducts() {
        if (products == null) {
            System.err.println("List of products is empty.\n");
        } else {
            System.out.println(products);
        }
    }


    /////////////////////////////////////Add
    /*Method add new user to 'users';
     * here checked whether 'users' exist and if not - create it, also validate user field and if something is not
     * suitable it shows message*/
    public void addUsers(String firstName, String lastName, double amountOfMoney) {
        if (users == null) {
            users = new Users();
        }
        if (firstName != null && lastName != null && amountOfMoney > 0) {
            users.getUsersList().add(new User(getAvailableUserId(), firstName, lastName, amountOfMoney));
            System.out.println("User added.\n");
        } else
            System.err.println("Not suitable values! Try again.");
    }

    /*
     * Method for autoGenerating usersID;
     * it just checks whether id is already exists and return first available value
     * */
    private int getAvailableUserId() {
        for (int i = 1; ; i++) {
            if (!users.idExists(i)) {
                return i;
            }
        }
    }

    public void addProduct(String name, double price) {
        if (products == null) {
            products = new Products();
        }
        if (name != null && price > 0) {
            products.getProductsList().add(new Product(getAvailableProductId(), name, price));
            System.out.println("Product added.\n");
        } else
            System.err.println("Not suitable values! Try again.");
    }

    private int getAvailableProductId() {
        for (int i = 1; ; i++) {
            if (!products.idExists(i)) {
                return i;
            }
        }
    }


    /////////////////////////////////////Delete
    /*Method delete user from 'users' list and from 'marketHistory' */
    public void deleteUser(int id) {
        if (users.idExists(id)) {
            User user = users.getUserById(id);
            users.getUsersList().remove(user);
            marketHistory.remove(user);
            System.out.println(user.getFirstName() + " " + user.getLastName() + " deleted\n");
        }
    }


    /*
     * Method delete product by id from 'products' list and 'marketHistory';
     * can be that user buy more than 1 product, so to avoid skipping value in 'while loop' checked whether
     * product value is still contain in 'marketHistory'
     * */
    public void deleteProduct(int id) {
        if (products.idExists(id)) {
            Product product = products.getProductById(id);
            products.getProductsList().remove(product);
            for (Map.Entry<User, List<Product>> entry : marketHistory.entrySet()) {
                while (entry.getValue().contains(product)) {
                    deleteProductFromList(entry.getValue(), product);
                }
            }
            System.out.println(product.getName() + " deleted.\n");
        }
    }

    /*method delete product from give list*/
    private void deleteProductFromList(List<Product> productList, Product product) {
        productList.remove(product);
    }

}

