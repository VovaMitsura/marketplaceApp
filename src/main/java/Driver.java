import exceptions.NotEnoughMoneyToBuy;
import market.Market;

import java.util.Scanner;

/*
* Class Driver contains method for load market, here can be added, displayed, deleted users and products,
* users can purchase products.
* */
public class Driver {

    /*starting method, variable 'way' need to run program, 'userId' and 'productId' to make purchase,
    and 'repeat' to flag when to close program*/
    public void start() {

        int way, userId, productId;
        boolean repeat = true;
        Market market = new Market();

        System.out.println("------------Hello in market------------");
        Scanner scanner = new Scanner(System.in);

        while (repeat) {
            System.out.println("---------------Main menu---------------");
            System.out.println("Enter: '1' - open display menu;\n\t" +
                    "   '2' - open add menu;\n\t" +
                    "   '3' - make purchase;\n\t" +
                    "   '4' - delete menu;\n\t" +
                    "   '0' - exit.");

            System.out.print("-> ");
            way = scanner.nextInt();

            if (way == 1) {
                displayMenu(market, scanner);
            } else if (way == 2) {
                addMenu(market, scanner);
            } else if (way == 3) {
                System.out.print("Please, enter user`s id: ");
                userId = scanner.nextInt();
                System.out.print("Please, enter product`s id: ");
                productId = scanner.nextInt();
                try {
                    market.makePurchase(userId, productId);
                } catch (NotEnoughMoneyToBuy ex) {
                    System.err.println(ex.getMessage());
                }
            } else if (way == 4) {
                deleteMenu(market, scanner);
            } else if (way == 0) {
                System.out.println("Thank! Good luck.");
                repeat = false;
            } else {
                System.out.println("Maybe you`ve misclicked. Try again.\n");
            }
        }
        scanner.close();
    }

    /*method to display users, products, products, which users buy, and users, who buy certain products;
    * displayWay need to run certain behaviour in if, 'repeatDisplay' to flag, when to leave from menu, '*/
    private void displayMenu(Market market, Scanner scanner) {

        int displayWay, userId, productId;
        boolean repeatDisplay = true;

        System.out.println("------------Display Menu------------");

        while (repeatDisplay) {
            System.out.println("Enter: '1' - display list of users;\n\t" +
                    "   '2' - display list of products;\n\t" +
                    "   '3' - display products by userId;\n\t   '4' - display users by productId;\n\t" +
                    "   '0' - exit.");
            System.out.print("-> ");
            displayWay = scanner.nextInt();

            if (displayWay == 1) {
                market.displayListOfUsers();
            } else if (displayWay == 2) {
                market.displayListOfProducts();
            } else if (displayWay == 3) {
                System.out.print("Please, enter user`s id: ");
                userId = scanner.nextInt();
                market.displayProductsByUserId(userId);
            } else if (displayWay == 4) {
                System.out.print("Please, enter product`s id: ");
                productId = scanner.nextInt();
                market.displayUsersByProductId(productId);
            } else if (displayWay == 0) {
                repeatDisplay = false;
            } else {
                System.out.println("Maybe you`ve misclicked. Try again.\n");
            }
        }
    }

    /*method to add users, products, products,'addWay' needs for certain behaviour in if;
     'name', 'lastName', 'amountOfMoney' needs for init User and Product, 'repeatAdding' to flag, when to leave from menu*/
    private void addMenu(Market market, Scanner scanner) {

        boolean repeatAdding = true;
        String name, lastName;
        double amountOfMoney;
        int addWay;

        System.out.println("------------Add Menu------------");

        while (repeatAdding) {
            System.out.println("Enter: '1' - add User;\n\t" +
                    "   '2' - add Product;\n\t" +
                    "   '0' - exit.");
            System.out.print("-> ");
            addWay = scanner.nextInt();

            if (addWay == 1) {
                System.out.print("Please, print name: ");
                name = scanner.next();
                System.out.print("Please, print lastName: ");
                lastName = scanner.next();
                System.out.print("Please, print amount of money user has: ");
                amountOfMoney = scanner.nextDouble();
                market.addUsers(name, lastName, amountOfMoney);
            } else if (addWay == 2) {
                System.out.print("Please, print name of product: ");
                name = scanner.next();
                System.out.print("Please, print price: ");
                amountOfMoney = scanner.nextDouble();
                market.addProduct(name, amountOfMoney);
            } else if (addWay == 0) {
                repeatAdding = false;
            } else {
                System.out.println("Maybe you`ve misclicked. Try again.\n");
            }
        }
    }

    /*method that delete prouct and user, 'deleteWay' for 'if' to know, which field need to be deleted;
     'userId' and 'productId' needs to now which object to delete, 'repeatDelete' to flag, when to leave from menu*/
    private void deleteMenu(Market market, Scanner scanner){

        boolean repeatDeleting = true;
        int deleteWay, userId, productId;

        System.out.println("------------Delete Menu------------");

        while (repeatDeleting) {
            System.out.println("Enter: '1' - delete User;\n\t" +
                    "   '2' - delete Product;\n\t" +
                    "   '0' - exit.");
            System.out.print("-> ");
            deleteWay = scanner.nextInt();

            if (deleteWay == 1) {
                System.out.print("Please, print id of user: ");
                userId = scanner.nextInt();
                market.deleteUser(userId);
            } else if (deleteWay == 2) {
                System.out.print("Please, print id of product: ");
                productId = scanner.nextInt();
                market.deleteProduct(productId);
            } else if (deleteWay == 0) {
                repeatDeleting = false;
            } else {
                System.out.println("Maybe you`ve misclicked. Try again.\n");
            }
        }
    }
}
