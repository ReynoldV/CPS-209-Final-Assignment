//Name: Reynold Villasenor-Doan
//Student No. 501031610
import java.util.Comparator;

/*
 * class Product defines a product for sale by the system. 
 * 
 * A product belongs to one of the 5 categories below. 
 * 
 * Some products also have various options (e.g. size, color, format, style, ...). The options can affect
 * the stock count(s). In this generic class Product, product options are not used in get/set/reduce stockCount() methods  
 * 
 * Some products
 */
public class Product
{
	public static enum Category {GENERAL, CLOTHING, BOOKS, FURNITURE, COMPUTERS, SHOES};
	
	private String name;
	private String id;
	private Category category;
	private double price;
	private int stockCount;
	private int purchaseCount;
	private int[] ratings;
	private double average_ratings; 
	
	public Product()
	{
		this.name = "Product";
		this.id = "001";
		this.category = Category.GENERAL;
		this.stockCount = 0;
		this.purchaseCount = 0;
		
	}
	
	public Product(String id)
	{
		this("Product", id, 0, 0, Category.GENERAL);
	}

	public Product(String name, String id, double price, int stock, Category category)
	{
		this.name = name;
		this.id = id;
		this.price = price;
		this.stockCount = stock;
		this.category = category;
		this.ratings = new int[5];
		this.average_ratings = 0.00;
	}
	/*
	 * This method always returns true in class Product. In subclasses, this method will be overridden
	 * and will check to see if the options specified are valid for this product.
	 */
	public boolean validOptions(String productOptions)
	{
		return true;
	}
	
	public Category getCategory()
	{
		return category;
	}
	
	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public double getPrice()
	{
		return price;
	}

	public void setPrice(double price)
	{
		this.price = price;
	}

	public int getYear()
	{
		return 0;
	}

	public String getAuthor()
	{
		return "";
	}

	/*
	 * Return the number of items currently in stock for this product
	 * Note: in this general class, the productOptions parameter is not used. It may be used
	 * in subclasses.
	 */
	public int getStockCount(String productOptions)
	{
		return stockCount;
	}

	public int getPurchaseCount()
	{
		return purchaseCount;
	}
	/*
	 * Set (or replenish) the number of items currently in stock for this product
	 * Note: in this general class, the productOptions parameter is not used. It may be used
	 * in subclasses.
	 */
	public void setStockCount(int stockCount, String productOptions)
	{
		this.stockCount = stockCount;
	}
	/*
	 * Reduce the number of items currently in stock for this product by 1 (called when a product has
	 * been ordered by a customer)
	 * Note: in this general class, the productOptions parameter is not used. It may be used
	 * in subclasses.
	 */
	public void reduceStockCount(String productOptions)
	{
		stockCount--;
	}
	
	//Increase number of times purchased
	public void increasePurchaseCount()
	{
		purchaseCount++;
	}

	//Adds rating count to array
	public void increaseRatings(int num_stars)
	{
		switch (num_stars)
		{
			case 1:
				ratings[0] += 1;
				break;
			case 2:
				ratings[1] += 1;
				break;
			case 3:
				ratings[2] += 1;
				break;
			case 4:
				ratings[3] += 1;
				break;
			case 5:
				ratings[4] += 1;
				break;
		}
	}

	//Determines average rating of product
	public double AverageRatings()
	{
		int num_ratings = ratings[0] + ratings[1] + ratings[2] + ratings[3] + ratings[4];

		if (num_ratings == 0)
		{
			average_ratings = 0.0;
			return average_ratings;
		}
		else
		{
			average_ratings = (ratings[0] + ratings[1]*2 + ratings[2]*3 + ratings[3]*4 + ratings[4]*5) / (double)(num_ratings);
			return average_ratings;
		}
	}

	public void print()
	{
		System.out.printf("\nId: %-5s Category: %-9s Name: %-20s Price: %7.1f", id, category, name, price);
	}

	public void printStats()
	{
		System.out.printf("\nId: %-5s Name: %-20s # Of times ordered: %-20s", id, name, purchaseCount);
	}
	
	public void printRatings()
	{
		AverageRatings();
		
		System.out.printf("\nId: %-5s Name: %-20s\n\nRatings:\nFive Stars: %-5s\nFour Stars: %-5s\nThree Stars: %-5s\nTwo Stars: %-5s\nOne Star: %-5s\nAverage Ratings: %7.1f", id, name, ratings[4], ratings[3], ratings[2], ratings[1], ratings[0], average_ratings);
	}

	/*
	 * Two products are equal if they have the same product Id.
	 * This method is inherited from superclass Object and overridden here
	 */
	public boolean equals(Object other)
	{
		Product otherP = (Product) other;
		return this.id.equals(otherP.id);
	}

}
//sorts products by name	
class compareByName implements Comparator<Product>
{
	public int compare(Product p1, Product p2)
	{
		return p1.getName().compareTo(p2.getName());
	}
}
//sorts products by price
class compareByPrice implements Comparator<Product>
{
	public int compare(Product p1, Product p2)
	{
		if (p1.getPrice() > p2.getPrice())
		{
			return 1;
		}
		else if (p1.getPrice() < p2.getPrice())
		{
			return -1;
		}
		else return 0;
	}
}
//sorts products by year
class compareByYear implements Comparator<Product>
{
	public int compare(Product p1, Product p2)
	{
		if (p1.getYear() > p2.getYear())
		{
			return 1;
		}
		else if (p1.getYear() < p2.getYear())
		{
			return -1;
		}
		else return 0;
	}
}

//Sorts based of number of orders
class compareByOrders implements Comparator<Product>
{
	public int compare(Product p1, Product p2)
	{
		if (p1.getPurchaseCount() < p2.getPurchaseCount())
		{
			return 1;
		}
		else if (p1.getPurchaseCount() > p2.getPurchaseCount())
		{
			return -1;
		}
		else return 0;
	}
}


