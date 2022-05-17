//Name: Reynold Villasenor-Doan
//Student No. 501031610
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.TreeMap;

/*
 * Models a simple ECommerce system. Keeps track of products for sale, registered customers, product orders and
 * orders that have been shipped to a customer
 */
public class ECommerceSystem
{
    private Map<String, Product> productss = new TreeMap<String, Product>();
    
    private ArrayList<Customer> customers = new ArrayList<Customer>();	
    
    private ArrayList<ProductOrder> orders   = new ArrayList<ProductOrder>();
    private ArrayList<ProductOrder> shippedOrders   = new ArrayList<ProductOrder>();
    
    // These variables are used to generate order numbers, customer id's, product id's 
    private int orderNumber = 500;
    private int customerId = 900;
    private int productId = 700;
    
    // Random number generator
    Random random = new Random();
    
    public ECommerceSystem()
    {
    	// NOTE: do not modify or add to these objects!! - the TAs will use for testing
    	// If you do the class Shoes bonus, you may add shoe products

    	//calls the function to load all the products from the text file into a map
      try
      {
        ProductList();
      }
      catch (IOException exception)
      {
        System.out.println(exception);
        System.exit(1);
      }
    	
    	// Create some customers. Notice how generateCustomerId() method is used
    	customers.add(new Customer(generateCustomerId(),"Inigo Montoya", "1 SwordMaker Lane, Florin"));
    	customers.add(new Customer(generateCustomerId(),"Prince Humperdinck", "The Castle, Florin"));
    	customers.add(new Customer(generateCustomerId(),"Andy Dufresne", "Shawshank Prison, Maine"));
    	customers.add(new Customer(generateCustomerId(),"Ferris Bueller", "4160 Country Club Drive, Long Beach"));
    }
    
    private String generateOrderNumber()
    {
    	return "" + orderNumber++;
    }

    private String generateCustomerId()
    {
    	return "" + customerId++;
    }
    
    private String generateProductId()
    {
    	return "" + productId++;
    }
    
    public String getErrorMessage()
    {
    	return errMsg;
    }
    
    public void printAllProducts()
    {
      for (String key : productss.keySet())
      {
        productss.get(key).print();
      }
    }
    
    // Print all products that are books. See getCategory() method in class Product
    public void printAllBooks()
    {
      for (String key : productss.keySet())
      {
        Product item = productss.get(key);
        if (item.getCategory().equals(Product.Category.BOOKS))
        {
          item.print();
        }
      }
    }
    // Print all current orders
    public void printAllOrders()
    {
    	for (ProductOrder po : orders)
      {
        po.print();
      }
    }
    // Print all shipped orders
    public void printAllShippedOrders()
    {
    	for (ProductOrder po : shippedOrders)
      {
        po.print();
      }
    }
    
    // Print all customers
    public void printCustomers()
    {
    	for (Customer c : customers)
        c.print();
    }
    /*
     * Given a customer id, print all the current orders and shipped orders for them (if any)
     */
    public void printOrderHistory(String customerId)
    {
      // Make sure customer exists - check using customerId
    	// ... code here
    	boolean valid_customerID = false;
      for (Customer c : customers)
      {
        if (c.getId().equals(customerId))
        {
          valid_customerID = true;
        }
      }
      //Throws exception if customer does not exist
      if (!valid_customerID)
      {
        throw new UnknownCustomerException("Customer not found");
      }
    	// Print current orders of this customer 
    	System.out.println("Current Orders of Customer " + customerId);
    	// enter code here
    	for (ProductOrder po : orders)
      {
        if (po.getCustomer().getId().equals(customerId))
        {
          po.print();
        }
      }
    	// Print shipped orders of this customer 
    	System.out.println("\nShipped Orders of Customer " + customerId);
    	//enter code here
    	for (ProductOrder so : shippedOrders)
      {
        if (so.getCustomer().getId().equals(customerId))
        {
          so.print();
        }
      }
    }
    
    public String orderProduct(String productId, String customerId, String productOptions)
    {
    	// First check to see if customer object with customerId exists in array list customers
    	// else get the Customer object
      Product the_product = new Product("", "", 0.00, 0, Product.Category.GENERAL);
      Customer the_customer = new Customer("", "", "");
      Boolean valid_customerID = false;
      Boolean valid_productID = false;
    	for (Customer c : customers) 
      {
        if (c.getId().equals(customerId))
        {
          the_customer = c;
          valid_customerID = true;
          break;
        }
      }
      //Throws exception if customer does not exist
      if (!valid_customerID)
      {
        throw new UnknownCustomerException("Customer not found");
      }
    	// Check to see if product object with productId exists in map of products
    	//get the Product object 
      for (String key : productss.keySet())
      {
        if (productss.get(key).getId().equals(productId))
        {
          the_product = productss.get(key);
          valid_productID = true;
          break;
        }
      }
      //Throws exception if product does not exist
      if (!valid_productID)
      {
        throw new UnknownProductException("Product not found");
      }
    	// Check if the options are valid for this product (e.g. Paperback or Hardcover or EBook for Book product)
    	// If options are not valid, throw exception
    	if (!the_product.validOptions(productOptions))
      {
        throw new InvalidOptionsException("Invalid Option");
      }
    	// Check if the product has stock available (i.e. not 0)
    	// If no stock available, throw exception
    	if (the_product.getStockCount(productOptions) <= 0)
      {
        throw new NoStockException("Product out of stock");
      }
      else 
      {
        the_product.reduceStockCount(productOptions);
        the_product.increasePurchaseCount();
      }
      // Create a ProductOrder, (make use of generateOrderNumber() method above)
    	// reduce stock count of product by 1 (see class Product and class Book)
    	// Add to orders list and return order number string
    	orders.add(new ProductOrder(generateOrderNumber(), the_product, the_customer , productOptions));    	
    	//return null; // replace this line
      return orders.get(orders.size() - 1).getOrderNumber();
    }
    
    /*
     * Create a new Customer object and add it to the list of customers
     */
    
    public void createCustomer(String name, String address)
    {
    	//Throws exception if name is invalid
    	if (name.equals(""))
      {
        throw new InvalidNameException("Invalid customer name");
      }
      
      //Throws exception if address is invalid
      if (address.equals(""))
      {
        throw new InvalidAddressException("Invalid customer address");
      }
    	// Create a Customer object and add to array list
      customers.add(new Customer(generateCustomerId(), name, address));
    }
    
    public ProductOrder shipOrder(String orderNumber)
    {
      //If order number is valid, ship order
      int array_counter = -1;
      for (ProductOrder po: orders)
      {
        array_counter += 1;
        if (po.getOrderNumber().equals(orderNumber))
        {
          orders.remove(array_counter);
          shippedOrders.add(po);
          return po;
        }
      }
      //Throws exception if order number does not exist
      throw new InvalidOrderNumException("Order not found");
    }
    
    /*
     * Cancel a specific order based on order number
     */
    public void cancelOrder(String orderNumber)
    {
      boolean order_removed = false;
    	int array_counter = -1;
      //If order number exists, cancel order
      for (ProductOrder po : orders)
      {
        array_counter += 1;
        if (po.getOrderNumber().equals(orderNumber))
        {
          orders.remove(array_counter);
          order_removed = true;
          break;
        }
      }
      //throws exception if order number does not exist
      if (!order_removed)
      {
        throw new InvalidOrderNumException("Order not found");
      }
    }
    //Print all books by a certain author and display them by year
    public void booksByAuthor(String author)
    {
      //Check if author exists first. If it doesn't, don't change valid_author
      boolean valid_author = false;
      for (String key : productss.keySet())
      {
        if (productss.get(key).getAuthor().equals(author))
        {
          valid_author = true;
        }
      }
      //If author is valid, sort products by year and only print books by the author entered
      if (valid_author)
      {
        ArrayList<Product> book_list = new ArrayList<Product>();
        for (String key : productss.keySet())
        {
          Product book_item = productss.get(key);
          if (book_item.getCategory().equals(Product.Category.BOOKS) && book_item.getAuthor().equals(author))
          {
            book_list.add(book_item);
          }
        }
        Collections.sort(book_list, new compareByYear());
        for (Product p : book_list)
        {
          p.print();
        }
      }
      //If author is not valid, throw exception
      if (!valid_author)
      {
        throw new UnknownProductException("Author not found");
      }
    }

    //Prints out an ordered list of how many times a product has been ordered from greatest to least
    public void productStatistics()
    {
      //Creates temp arraylist
      ArrayList<Product> Stat_List = new ArrayList<Product>();
      //Adds all products in map to temp list
      for (String key : productss.keySet())
      {
        Product item = productss.get(key);
        Stat_List.add(item);
      }
      //Sorts list from most ordered to least ordered and prints the list out with the num of times ordered next to it
      Collections.sort(Stat_List, new compareByOrders());
      for (Product p : Stat_List)
      {
        p.printStats();
      }
    }

    //Allows customer to rate a product out of 5 stars
    public void reviewProduct(String productId, int num_stars)
    {
      boolean valid_product = false;
      //throws exception if rating is less than 1 or greater than 5
      if (num_stars < 1 || num_stars > 5)
      {
        throw new InvalidRatingEntry("Rating must be between 1 and 5");
      }
      //If product exists, the rating is added to the product
      for (String key : productss.keySet())
      {
        Product item = productss.get(key);
        if (item.getId().equals(productId))
        {
          valid_product = true;
          item.increaseRatings(num_stars);
          break;
        }
      }
      //Throws exception if product dos not exist
      if (!valid_product)
      {
        throw new UnknownProductException("Product not found");
      }
    }

    //Prints all rating stats of a specified product
    public void printProductRatings(String productId)
    {
      boolean valid_product = false;
      //if product exists, ratings stats are printed for that item
      for (String key : productss.keySet())
      {
        Product item = productss.get(key);
        if (item.getId().equals(productId))
        {
          valid_product = true;
          item.printRatings();
          break;
        }
      }
      //throws exception if product does not exist
      if (!valid_product)
      {
        throw new UnknownProductException("Product not found");
      }
    }

    //Filters products by category and ratings
    public void filterProductRatings(String category, int threshold)
    {
      //Throws exception if category does not exist
      if (!(category.equals("GENERAL") || category.equals("CLOTHING") || category.equals("BOOKS") || category.equals("FURNITURE") || category.equals("COMPUTERS")))
      {
        throw new InvalidCategoryException("Category not found");
      }
      //Throws exception if rating is outside of range
      if (threshold < 1 || threshold > 5)
      {
        throw new InvalidRatingEntry("Rating must be between 1 and 5");
      }
      //Prints every item that is in the specified category and rating is above specified threshold
      for (String key : productss.keySet())
      {
        Product item = productss.get(key);
        if (item.getCategory().equals(Product.Category.valueOf(category)) && item.AverageRatings() > threshold)
        {
          item.print();
          System.out.print(" Average ratings: " + item.AverageRatings());
        }
      }
    }

    //Adds product to cart
    public void addToCart(String productId, String customerId, String productOptions)
    {
      Product the_product = new Product("", "", 0.00, 0, Product.Category.GENERAL);
      Customer the_customer = new Customer("", "", "");
      Boolean valid_customerID = false;
      Boolean valid_productID = false;
      //Checks if customer exists
    	for (Customer c : customers) 
      {
        if (c.getId().equals(customerId))
        {
          the_customer = c;
          valid_customerID = true;
          break;
        }
      }
      //Throws exceptio if customer doesn't exist
      if (!valid_customerID)
      {
        throw new UnknownCustomerException("Customer not found");
      }
    	// Checks to see if product object with productId exists in map of products
      for (String key : productss.keySet())
      {
        if (productss.get(key).getId().equals(productId))
        {
          the_product = productss.get(key);
          valid_productID = true;
          break;
        }
      }
      //Throws exception if product does not exist
      if (!valid_productID)
      {
        throw new UnknownProductException("Product not found");
      }
    	//Throws exception if product option is invalid
    	if (!the_product.validOptions(productOptions))
      {
        throw new InvalidOptionsException("Invalid Option");
      }
    	//Throws exception if product is out of stock
    	if (the_product.getStockCount(productOptions) <= 0)
      {
        throw new NoStockException("Product out of stock");
      }
      //Reduce stock count and increase num of times product is ordered if product exists
      else 
      {
        the_product.reduceStockCount(productOptions);
        the_product.increasePurchaseCount();
      }
      //Adds product to customer's cart
      the_customer.addToCart(the_product, productOptions);
    }

    //Removes product from customer's cart
    public void removeFromCart(String customerId, String productId)
    {
      Product the_product = new Product("", "", 0.00, 0, Product.Category.GENERAL);
      Customer the_customer = new Customer("", "", "");
      Boolean valid_customerID = false;
      Boolean valid_productID = false;
      //Checks if customer exists
    	for (Customer c : customers) 
      {
        if (c.getId().equals(customerId))
        {
          the_customer = c;
          valid_customerID = true;
          break;
        }
      }
      //Throws exception if customer does not exist
      if (!valid_customerID)
      {
        throw new UnknownCustomerException("Customer not found");
      }
    	// Check to see if product object with productId exists in map of products
      for (String key : productss.keySet())
      {
        if (productss.get(key).getId().equals(productId))
        {
          the_product = productss.get(key);
          valid_productID = true;
          break;
        }
      }
      //Throws exception if product does not exist
      if (!valid_productID)
      {
        throw new UnknownProductException("Product not found");
      }
      //Removes product from customer's cart
      the_customer.removeFromCart(the_product);
    }

    //Prints all products in customer's cart
    public void printCart(String customerId)
    {
      boolean valid_customerID = false;
      //Prints all products in customer's cart if customer exists
      for (Customer c : customers) 
      {
        if (c.getId().equals(customerId))
        {
          c.printCart();
          valid_customerID = true;
          break;
        }
      }
      //Throws exception if customer does not exist
      if (!valid_customerID)
      {
        throw new UnknownCustomerException("Customer not found");
      }
    }

    //Orders all products in customer's cart
    public void orderItems(String customerId)
    {
      boolean valid_customerID = false;
      for (Customer c : customers) 
      {
        if (c.getId().equals(customerId))
        {
          
          valid_customerID = true;
          break;
        }
      }

      if (!valid_customerID)
      {
        throw new UnknownCustomerException("Customer not found");
      }
      else
      {
        
      }
      
    }
    
    // Sort products by increasing price
    public void sortByPrice()
    {
      ArrayList<Product> product_list = new ArrayList<Product>();
      for (String key : productss.keySet())
      {
        product_list.add(productss.get(key));
      }
  	  Collections.sort(product_list, new compareByPrice());
      for (Product p: product_list)
      {
        p.print();
      }
    }
    
    
    // Sort products alphabetically by product name
    public void sortByName()
    {
      ArrayList<Product> product_list = new ArrayList<Product>();
      for (String key : productss.keySet())
      {
        product_list.add(productss.get(key));
      }
  	  Collections.sort(product_list, new compareByName());
      for (Product p: product_list)
      {
        p.print();
      }
    }
    
        
    // Sort products alphabetically by product name
    public void sortCustomersByName()
    {
  	  Collections.sort(customers);
    }

    //Imports all products in text file into map of products
    private void ProductList() throws IOException
    {
      Scanner in = new Scanner(new File("products.txt"));

      while (in.hasNextLine())
      {
        String category = in.nextLine();
        String productName = in.nextLine();
        Double price = Double.parseDouble(in.nextLine());
        String stockLine = in.nextLine();
        Scanner stock = new Scanner(stockLine);

        if (category.equals("BOOKS"))
        {
          int stockCount = stock.nextInt();
          int stockCount2 = stock.nextInt();
          String title = "";
          String author = "";
          int year = 0;

          Scanner additionalInfo = new Scanner(in.nextLine());
          additionalInfo.useDelimiter(":");

          while (additionalInfo.hasNext())
          {
            title = additionalInfo.next();
            author = additionalInfo.next();
            year = additionalInfo.nextInt();
          }
          String prodID = generateProductId();
          productss.put(prodID, new Book(productName, prodID, price, stockCount, stockCount2, title, author, year));
        }
        else
        {
          
          int stockCount = Integer.parseInt(stockLine);
          String additionalInfo = in.nextLine();

          String prodID = generateProductId();
          productss.put(prodID, new Product(productName, prodID, price, stockCount, Product.Category.valueOf(category)));
          
        }
        
      }
    }
}
//--------------------------------------------------------------------------------------------------------------
//Exception Classes Section Below

//Exception for where customer does not exist
class UnknownCustomerException extends RuntimeException
{
  public UnknownCustomerException(String message)
  {
    super(message);
  }
}

//exception for where product does not exist
class UnknownProductException extends RuntimeException
{
  public UnknownProductException(String message)
  {
    super(message);
  }
}

//exception for where product option is invalid
class InvalidOptionsException extends RuntimeException
{
  public InvalidOptionsException(String message)
  {
    super(message);
  }
}

//exception for where product is out of stock
class NoStockException extends RuntimeException
{
  public NoStockException(String message)
  {
    super(message);
  }
}

//exception for where name is invalid
class InvalidNameException extends RuntimeException
{
  public InvalidNameException(String message)
  {
    super(message);
  }
}

//exception for where address is invalid
class InvalidAddressException extends RuntimeException
{
  public InvalidAddressException(String message)
  {
    super(message);
  }
}

//exception for where order number is invalid
class InvalidOrderNumException extends RuntimeException
{
  public InvalidOrderNumException(String message)
  {
    super(message);
  }
}

//exception for where product rating is invalid
class InvalidRatingEntry extends RuntimeException
{
  public InvalidRatingEntry(String message)
  {
    super(message);
  }
}

//exception for where product category is invalid
class InvalidCategoryException extends RuntimeException
{
  public InvalidCategoryException(String message)
  {
    super(message);
  }
}
