//Name: Reynold Villasenor-Doan
//Student No. 501031610
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

// Simulation of a Simple E-Commerce System (like Amazon)

public class ECommerceUserInterface
{
	public static void main(String[] args)
	{
		// Create the system
		ECommerceSystem amazon = new ECommerceSystem();

		Scanner scanner = new Scanner(System.in);
		System.out.print(">");
		
		// Process keyboard actions
		while (scanner.hasNextLine())
		{
			String action = scanner.nextLine();
			
			if (action == null || action.equals("")) 
			{
				System.out.print("\n>");
				continue;
			}
			else if (action.equalsIgnoreCase("Q") || action.equalsIgnoreCase("QUIT"))
				return;

			else if (action.equalsIgnoreCase("PRODS"))	// List all products for sale
			{
				amazon.printAllProducts(); 
			}
			else if (action.equalsIgnoreCase("BOOKS"))	// List all books for sale
			{
				amazon.printAllBooks(); 
			}
			else if (action.equalsIgnoreCase("CUSTS")) 	// List all registered customers
			{
				amazon.printCustomers();	
			}
			else if (action.equalsIgnoreCase("ORDERS")) // List all current product orders
			{
				amazon.printAllOrders();	
			}
			else if (action.equalsIgnoreCase("SHIPPED"))	// List all orders that have been shipped
			{
				amazon.printAllShippedOrders();	
			}
			else if (action.equalsIgnoreCase("NEWCUST"))	// Create a new registered customer
			{
				String name = "";
				String address = "";
				
				System.out.print("Name: ");
				if (scanner.hasNextLine())
					name = scanner.nextLine();
				
				System.out.print("\nAddress: ");
				if (scanner.hasNextLine())
					address = scanner.nextLine();
				
				try
				{
					amazon.createCustomer(name, address);
				}
				catch (InvalidAddressException exception)
				{
					System.out.println(exception);
				}
				catch (InvalidNameException exception)
				{
					System.out.println(exception);
				}
			}
			else if (action.equalsIgnoreCase("SHIP"))	// ship an order to a customer
			{
					String orderNumber = "";
        
					System.out.print("Order Number: ");
					// Get order number from scanner
					orderNumber = scanner.next();
					// Ship order to customer (see ECommerceSystem for the correct method to use
					try
					{
						amazon.shipOrder(orderNumber);
					}
					catch (InvalidOrderNumException exception)
					{
						System.out.println(exception);
					}
			}
			else if (action.equalsIgnoreCase("CUSTORDERS")) // List all the current orders and shipped orders for this customer id
			{
				String customerId = "";

				System.out.print("Customer Id: ");
				// Get customer Id from scanner
				customerId = scanner.next();
				try
				{
					amazon.printOrderHistory(customerId);
				}
				catch (UnknownCustomerException exception)
				{
					System.out.println(exception);
				}
			}
			else if (action.equalsIgnoreCase("ORDER")) // order a product for a certain customer
			{
				
				String productId = "";
				String customerId = "";

				System.out.print("Product Id: ");
			  // Get product Id from scanner
				productId = scanner.next();
				System.out.print("\nCustomer Id: ");
			  // Get customer Id from scanner
				customerId = scanner.next();
				// Order the product. Catch exceptions that are thrown
				try
				{
					System.out.println("Order #" + amazon.orderProduct(productId, customerId, ""));
				}
				catch (UnknownCustomerException exception)
				{
					System.out.println(exception);
				}
				catch (UnknownProductException exception)
				{
					System.out.println(exception);
				}
				catch (NoStockException exception)
				{
					System.out.println(exception);
				}
			}
			else if (action.equalsIgnoreCase("ORDERBOOK")) // order a book for a customer, provide a format (Paperback, Hardcover or EBook)
			{
				String productId = "";
				String customerId = "";
				String options = "";

				System.out.print("Product Id: ");
				// get product id
				productId = scanner.next();
				System.out.print("\nCustomer Id: ");
				// get customer id
				customerId = scanner.next();
				System.out.print("\nFormat [Paperback Hardcover EBook]: ");
				// get book forma and store in options string
				options = scanner.next();
				
				// Order product. Catch exceptions that are thrown
				// Print order number string if order number is not null
				try
				{
					System.out.println("Order #" + amazon.orderProduct(productId, customerId, options));
				}
				catch (UnknownCustomerException exception)
				{
					System.out.println(exception);
				}
				catch (UnknownProductException exception)
				{
					System.out.println(exception);
				}
				catch (InvalidOptionsException exception)
				{
					System.out.println(exception);
				}
				catch (NoStockException exception)
				{
					System.out.println(exception);
				}
			}
			else if (action.equalsIgnoreCase("CANCEL")) // Cancel an existing order
			{
				String orderNumber = "";

				System.out.print("Order Number: ");
				// get order number from scanner
				orderNumber = scanner.next();
				// cancel order. Catch exceptions
				try
				{
					amazon.cancelOrder(orderNumber);
				}
				catch (InvalidOrderNumException exception)
				{
					System.out.println(exception);
				}
			}
			else if (action.equalsIgnoreCase("PRINTBYPRICE")) // sort products by price
			{
				amazon.sortByPrice();
			}
			else if (action.equalsIgnoreCase("PRINTBYNAME")) // sort products by name (alphabetic)
			{
				amazon.sortByName();
			}
			else if (action.equalsIgnoreCase("SORTCUSTS")) // sort products by name (alphabetic)
			{
				amazon.sortCustomersByName();
			}
			else if (action.equalsIgnoreCase("BOOKSBYAUTHOR")) // sort books by year
			{
				String author = "";

				System.out.print("Author: ");
				//get author from scanner
				author = scanner.nextLine();
				//Prints all books by author, sorted by year. Catch exceptions
				try
				{
					amazon.booksByAuthor(author);
				}
				catch (UnknownProductException exception)
				{
					System.out.println(exception);
				}
			}
			else if (action.equalsIgnoreCase("ADDTOCART"))
			{
				String productId = "";
				String customerId = "";
				String options = "";

				System.out.print("Product Id: ");
			  // Get product Id from scanner
				productId = scanner.next();
				System.out.print("\nCustomer Id: ");
			  // Get customer Id from scanner
				customerId = scanner.next();
				System.out.print("Are you ordering a book? [Yes No]");

				if (scanner.next().equalsIgnoreCase("YES"))
				{
					System.out.print("\nFormat [Paperback Hardcover EBook]: ");
					options = scanner.next();
				}
				//Add product to cart, Catch exceptions
				try
				{
					amazon.addToCart(productId, customerId, options);
				}
				catch (UnknownCustomerException exception)
				{
					System.out.println(exception);
				}
				catch (UnknownProductException exception)
				{
					System.out.println(exception);
				}
				catch (InvalidOptionsException exception)
				{
					System.out.println(exception);
				}
				catch (NoStockException exception)
				{
					System.out.println(exception);
				}
				
			}
			else if (action.equalsIgnoreCase("REMCARTITEM"))
			{
				String productId = "";
				String customerId = "";
				
				System.out.print("Product Id: ");
				//Get productId from scanner
				productId = scanner.next();
				System.out.print("Customer Id: ");
				//Get customerId from scanner
				customerId = scanner.next();
				//Remove product from cart, catch exceptions
				try
				{
					amazon.removeFromCart(customerId, productId);
				}
				catch (UnknownCustomerException exception)
				{
					System.out.println(exception);
				}
				catch (UnknownProductException exception)
				{
					System.out.println(exception);
				}
				
			}
			else if (action.equalsIgnoreCase("PRINTCART"))
			{
				String customerId = "";
				
				System.out.print("Customer Id: ");
				//Get customerId from scanner
				customerId = scanner.next();
				//Print cart, catch exceptions
				try
				{
					amazon.printCart(customerId);
				}
				catch (UnknownCustomerException exception)
				{
					System.out.println(exception);
				}
				
			}
			else if (action.equalsIgnoreCase("ORDERITEMS"))
			{
				String customerId = "";

				System.out.print("Customer Id: ");
				//Get customerId from scanner
				customerId = scanner.next();
			}
			else if (action.equalsIgnoreCase("STATS"))
			{
				amazon.productStatistics();
			}
			else if (action.equalsIgnoreCase("RATEPROD"))
			{
				String productId = "";
				int num_stars = 0;

				System.out.print("Product Id: ");
				//Get productId from scanner
				productId = scanner.next();
				System.out.print("On a scale of 1 to 5, enter number of stars you would rate the product: ");
				//Get rating from scanner
				num_stars = scanner.nextInt();
				//Adds rating to product, catch exceptions
				try
				{
					amazon.reviewProduct(productId, num_stars);
				}
				catch (InvalidRatingEntry exception)
				{
					System.out.println(exception);
				}
				catch (UnknownProductException exception)
				{
					System.out.println(exception);
				}

			}
			else if (action.equalsIgnoreCase("PRINTPRODRATING"))
			{
				String productId = "";

				System.out.print("Product Id: ");
				//Get productId from scanner
				productId = scanner.next();
				//Print product rating, catch exceptions
				try
				{
					amazon.printProductRatings(productId);
				}
				catch (UnknownProductException exception)
				{
					System.out.println(exception);
				}
				
			}
			else if (action.equalsIgnoreCase("FILTERPRODRATING"))
			{
				String category = "";
				int threshold = 0;
				
				System.out.println("Category [GENERAL, CLOTHING, BOOKS, FURNITURE, COMPUTERS, SHOES]");
				//Get category from scanner
				category = scanner.next();
				System.out.print("Enter minimum average rating to filter by: ");
				//Get rating from scanner
				threshold = scanner.nextInt();
				//Filter product ratings, catch exceptions
				try
				{
					amazon.filterProductRatings(category, threshold);
				}
				catch (InvalidCategoryException exception)
				{
					System.out.println(exception);
				}
				catch (InvalidRatingEntry exception)
				{
					System.out.println(exception);
				}
				
			}
			System.out.print("\n>");
		}
	}
}
