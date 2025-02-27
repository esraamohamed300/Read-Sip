import java.util.*;

class globalv {
    public static int x;
}

class MenuItem 
{
    private String name;
    private double price;

    public MenuItem(String name, double price) 
    {
        this.name = name;
        this.price = price;
    }

    public String getName() 
    {
        return name;
    }

    public double getPrice()
    {
        return price;
    }
}

class Book 
{
    private String title;
    private String author;
    private boolean isAvailable;
    private double price;

    public Book(String title, String author, double price) 
    {
        this.title = title;
        this.author = author;
        this.price = price;
        this.isAvailable = true;
    }

    public String getTitle()
    {
        return title;
    }

    public String getAuthor() 
    {
        return author;
    }

    public double getPrice() 
    {
        return price;
    }

    public boolean isAvailable() 
    {
        return isAvailable;
    }

    public void rentBook() 
    {
        if (isAvailable) {
            isAvailable = false;
            System.out.println("Book rented successfully.");
        } 
        else {
            System.out.println("Book is currently not available.");
        }
    }

}

class StudyArea 
{
    private int availableSeats;
    private double hourlyRate;
    Scanner input = new Scanner(System.in);

    public StudyArea(int seats, double rate) 
    {
        this.availableSeats = seats;
        this.hourlyRate = rate;
    }

    public boolean bookSeat() 
    {

        if (availableSeats > 0) {
            int hours;
            System.out.println("enter hours to rent:");
            hours = input.nextInt();
            globalv.x = hours;
            availableSeats--;
            System.out.println("Study area booked for " + hours + " hours.");
            return true;
        } else {
            System.out.println("No available study area seats.");
            return false;
        }
    }

    public void releaseSeat() 
    {
        availableSeats++;
    }

    public double calculateCharge(int x) 
    {
        return x * hourlyRate;
    }
}

class Order 
{
    private List<MenuItem> items = new ArrayList<>();
    private List<Book> purchasedBooks = new ArrayList<>();
    private double total;
    private double studyCharge = 0; // Added variable to track study area charge

    public void addItem(MenuItem item) 
    {
        items.add(item);
        total += item.getPrice();
    }

    public void buyBook(Book book) 
    {
        if (purchasedBooks.contains(book)) {
            System.out.println("You have already purchased this book.");
            return;
        }

        purchasedBooks.add(book);
        total += book.getPrice();
        System.out.println("You bought '" + book.getTitle() + "' for $" + book.getPrice());
    }

    public void addCharge(double charge) 
    {
        studyCharge += charge; // Track study area charges separately
        total += charge;
    }

    public void printReceipt(List<Book> rentedBooks) 
    {
        System.out.println("\nReceipt:");

        // Print ordered menu items
        for (MenuItem item : items) 
        {
            System.out.println(item.getName() + " - $" + item.getPrice());
        }

        // Print purchased books
        if (!purchasedBooks.isEmpty()) 
        {
            System.out.println("\nPurchased Books:");
            for (Book book : purchasedBooks) 
            {
                System.out.println(book.getTitle() + " by " + book.getAuthor() + " - $" + book.getPrice());
            }
        }

        // Print rented books
        if (!rentedBooks.isEmpty()) 
        {
            System.out.println("\nRented Books:");
            for (Book book : rentedBooks) 
            {
                System.out.println(book.getTitle() + " by " + book.getAuthor());
            }
        }

        if (studyCharge > 0) 
        {
            System.out.println("\nStudy Area Charge: $" + studyCharge);
        }

        // Print total cost
        System.out.println("\nTotal: $" + total);
    }

}

class Cafe 
{
    private List<MenuItem> menu = new ArrayList<>();
    private List<Book> books = new ArrayList<>();
    private StudyArea studyArea;
    private Scanner scanner = new Scanner(System.in);
    private List<String> feedbackList = new ArrayList<>(); // Stores customer feedback

    public Cafe() 
    {
        menu.add(new MenuItem("Tea", 1.5));
        menu.add(new MenuItem("Coffee", 2.5));
        menu.add(new MenuItem("Espresso", 3.0));
        menu.add(new MenuItem("Cappuccino", 4.0));
        menu.add(new MenuItem("Latte", 4.5));
        menu.add(new MenuItem("Hot Chocolate", 3.5));
        menu.add(new MenuItem("Iced Coffee", 3.8));
        menu.add(new MenuItem("Green Tea", 2.0));
        menu.add(new MenuItem("Herbal Tea", 2.5));
        menu.add(new MenuItem("Fresh Juice", 4.0));
        menu.add(new MenuItem("Smoothie", 5.5));
        menu.add(new MenuItem("Croissant", 3.0));
        menu.add(new MenuItem("Donut", 5.0));
        menu.add(new MenuItem("Muffin", 2.8));
        menu.add(new MenuItem("Pancakes", 6.0));
        menu.add(new MenuItem("Waffles", 6.5));
        menu.add(new MenuItem("Cheesecake", 4.5));
        menu.add(new MenuItem("Brownie", 3.5));
        menu.add(new MenuItem("Club Sandwich", 6.5));
        menu.add(new MenuItem("Veggie Wrap", 5.5));
        menu.add(new MenuItem("Chicken Salad", 7.0));
        menu.add(new MenuItem("Pasta Alfredo", 8.5));
        menu.add(new MenuItem("Burger", 7.5));

        books.add(new Book("The Great Gatsby", "F. Scott Fitzgerald", 15));
        books.add(new Book("shatter me", "Tahereh Mafi", 16));
        books.add(new Book("The way i used to be", "Amber Smith", 12));
        books.add(new Book("The silent patient", "Alex Michaelides", 14.5));
        books.add(new Book("It ends with us", "Collen Hoover", 15.5));
        books.add(new Book("The house made", "Freida Mcfadden", 17.5));
        books.add(new Book("Good girls guide to murder", "Holly Jackson", 20));
        books.add(new Book("The summer I turned pretty", "Jenny Han", 19.5));
        books.add(new Book("Girl in pieces", "kathleen Glasgow", 11.5));

        studyArea = new StudyArea(8, 3.0);
    }

    public void displayMenu() 
    {
        System.out.println("\nMenu:");
        for (int i = 0; i < menu.size(); i++) 
        {
            System.out.println((i + 1) + ". " + menu.get(i).getName() + " - $" + menu.get(i).getPrice());
        }
    }

    public void takeOrder(Customer customer) 
    {
        displayMenu();
        while (true) 
        {
            System.out.println("Enter item number to add to order (0 to finish):");
            int choice = scanner.nextInt();
            if (choice == 0) {
                break;
            }
            if (choice > 0 && choice <= menu.size()) 
            {
                customer.placeOrder(menu.get(choice - 1));
                System.out.println("Item added to order.");
            } 
            else 
            {
                System.out.println("Invalid choice.");
            }
        }
    }

    public void rentStudyArea(Customer customer) 
    {
        customer.bookStudyArea(studyArea);
    }

    public void displayBooks() 
    {
        System.out.println("\nAvailable Books:");
        for (int i = 0; i < books.size(); i++) 
        {
            String status = books.get(i).isAvailable() ? "Available" : "Not Available";
            System.out.println((i + 1) + ". " + books.get(i).getTitle() + " by " + books.get(i).getAuthor() + " - "
                    + status + " - $" + books.get(i).getPrice());
        }
    }

    public void rentBook(Customer customer) 
    {
        displayBooks();
        System.out.println("Enter book number to rent:");
        int choice = scanner.nextInt();
        if (choice > 0 && choice <= books.size()) {
            customer.rentBook(books.get(choice - 1));
        } 
        else {
            System.out.println("Invalid choice.");
        }
    }

    public void buyBook(Customer customer) 
    {
        displayBooks();
        System.out.println("Enter book number to buy:");
        int choice = scanner.nextInt();
        if (choice > 0 && choice <= books.size()) {
            customer.buyBook(books.get(choice - 1));
        } 
        else {
            System.out.println("Invalid choice.");
        }
    }

    public void submitFeedback(String customerName, int rating, String feedback) 
    {
        if (rating < 1 || rating > 5) {
            System.out.println("Invalid rating! Please enter a rating between 1 and 5.");
            return;
        }
        feedbackList.add(customerName + " ( " + rating + " Stars ) : " + feedback);
        System.out.println("Thank you for your feedback!");
    }

    public void displayFeedback() 
    {
        if (feedbackList.isEmpty()) 
        {
            System.out.println("\nNo feedback available yet.");
        } 
        else {
            System.out.println("\nCustomer Feedback:");
            for (String feedback : feedbackList) 
            {
                System.out.println("- " + feedback);
            }
        }
    }

    public void addMenuItem() 
    {
        System.out.println("Enter item name:");
        String name = scanner.next();
        System.out.println("Enter item price:");
        double price = scanner.nextDouble();
        menu.add(new MenuItem(name, price));
        System.out.println("Item added successfully!");
    }

    public void removeMenuItem() 
    {
        displayMenu();
        System.out.println("Enter item number to remove:");
        int choice = scanner.nextInt();
        if (choice > 0 && choice <= menu.size()) {
            menu.remove(choice - 1);
            System.out.println("Item removed successfully!");
        } 
        else {
            System.out.println("Invalid choice.");
        }
    }

    public void manageMenu() 
    {
        while (true) 
        {
            System.out.println("\nMenu Management:");
            System.out.println("1. Add Item");
            System.out.println("2. Remove Item");
            System.out.println("3. Show Menu");
            System.out.println("4. Exit");
            System.out.println("Enter choice:");
            int choice = scanner.nextInt();
            switch (choice) 
            {
                case 1:
                    addMenuItem();
                    break;
                case 2:
                    removeMenuItem();
                    break;
                case 3:
                    displayMenu();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}

class Customer 
{
    private String name;
    private List<Book> rentedBooks = new ArrayList<>();
    private Order order = new Order();

    public Customer(String name) {
        this.name = name;
    }

    public void rentBook(Book book) 
    {
        if (!book.isAvailable()) {
            System.out.println("Sorry, this book is already rented.");
            return;
        }

        rentedBooks.add(book);
        book.rentBook();

        double rentalFee = 5.0;
        order.addCharge(rentalFee);
        System.out.println("You have rented '" + book.getTitle() + "' for $" + rentalFee);
    }

    public void buyBook(Book book) 
    {
        if (!book.isAvailable()) {
            System.out.println("Sorry, this book is not available.");
            return;
        } 
        else {
            order.buyBook(book);
        }

    }

    public void placeOrder(MenuItem item) {
        order.addItem(item);
    }

    public void bookStudyArea(StudyArea studyArea) 
    {
        if (studyArea.bookSeat()) 
        {
            double charge = studyArea.calculateCharge(globalv.x);
            order.addCharge(charge);
            System.out.println("Study Area Charge: $" + charge);
        }
    }

    public void printReceipt() 
    {
        System.out.println("\nCustomer: " + name);
        order.printReceipt(rentedBooks);
    }

    public void giveFeedback(Cafe cafe, int rating, String feedback) 
    {
        cafe.submitFeedback(name, rating, feedback);
    }
}

public class ReadandSip 
{
    public static void main(String[] args) 
    {

        Scanner scanner = new Scanner(System.in);
        boolean AE = true;
        Cafe cafe = new Cafe();
        while (AE) 
        {
            System.out.println("\n *******Welcome to Read&Sip System*******");
            System.out.println("Enter (1) for customer or (2) for Employee or (0) to exit:");
            int num = scanner.nextInt();
            scanner.nextLine();
            if (num == 1) 
            {
                System.out.println("Enter customer name:");
                String name = scanner.nextLine();
                Customer customer = new Customer(name);

                while (true) 
                {
                    System.out.println("1. Take Order");
                    System.out.println("2. Rent Study Area");
                    System.out.println("3. Rent a Book");
                    System.out.println("4. Buy a Book");
                    System.out.println("5. Print Receipt");
                    System.out.println("6. Leave Feedback");
                    System.out.println("7. View Feedback");
                    System.out.println("8. Exit");
                    System.out.println("Enter choice:");

                    int choice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    if (choice == 8) {
                        System.out.println("Exiting...");
                        break;
                    }

                    switch (choice) 
                    {
                        case 1:
                            cafe.takeOrder(customer);
                            break;
                        case 2:
                            cafe.rentStudyArea(customer);
                            break;
                        case 3:
                            cafe.rentBook(customer);
                            break;
                        case 4:
                            cafe.buyBook(customer);
                            break;
                        case 5:
                            customer.printReceipt();
                            break;
                        case 6: 
                        {
                            System.out.println("Rate your experience (1-5 stars):");
                            int rating = scanner.nextInt();
                            scanner.nextLine(); // Consume newline
                            System.out.println("Enter your feedback:");
                            String feedback = scanner.nextLine();
                            customer.giveFeedback(cafe, rating, feedback);
                            break;
                        }
                        case 7:
                            cafe.displayFeedback();
                            break;
                        default:
                            System.out.println("Invalid choice.");
                    }
                }
            } else if (num == 2) {
                cafe.manageMenu();
            } else if (num == 0) {
                AE = false;
            } 
            else {
                System.out.println("Invalid choice.");
            }

        }
        scanner.close();
    }
}