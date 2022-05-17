//Name: Reynold Villasenor-Doan
//Student No. 501031610
import java.util.Comparator;

/*
 *  class Customer defines a registered customer. It keeps track of the customer's name and address. 
 *  A unique id is generated when when a new customer is created. 
 *  
 *  Implement the Comparable interface and compare two customers based on name
 */
public class Customer implements Comparable<Customer>
{
	private String id;  
	private String name;
	private String shippingAddress;
	private Cart custCart;
	
	public Customer(String id)
	{
		this.id = id;
		this.name = "";
		this.shippingAddress = "";
		this.custCart = new Cart();
	}
	
	public Customer(String id, String name, String address)
	{
		this.id = id;
		this.name = name;
		this.shippingAddress = address;
		this.custCart = new Cart();
	}
	
	public String getId()
	{
		return id;
	}
	public void setId(String id)
	{
		this.id = id;
	}
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getShippingAddress()
	{
		return shippingAddress;
	}
	
	public void setShippingAddress(String shippingAddress)
	{
		this.shippingAddress = shippingAddress;
	}

	public void addToCart(Product productItem, String productOptions)
	{
		CartItem item = new CartItem(productItem, productOptions);
		custCart.addToCart(item);
	}

	public void removeFromCart(Product productItem)
	{
		custCart.removeFromCart(productItem);
	}

	public Cart retrieveCustCart()
	{
		return custCart;
	}

	public void clearCustCart()
	{
		custCart = new Cart();
	}
	
	public void print()
	{
		System.out.printf("\nName: %-20s ID: %3s Address: %-35s", name, id, shippingAddress);
	}
	
	public void printCart()
	{
		custCart.print();
	}

	public boolean equals(Object other)
	{
		Customer otherC = (Customer) other;
		return this.id.equals(otherC.id);
	}
	
	public int compareTo(Customer other)
	{
		return this.getName().compareTo(other.getName());
	}
}
