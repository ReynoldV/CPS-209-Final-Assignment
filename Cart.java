//Name: Reynold Villasenor-Doan
//Student No. 501031610
import java.util.ArrayList;

public class Cart
{
    private ArrayList<CartItem> custCart;

    //Creates empty cart
    public Cart()
    {
        this.custCart = new ArrayList<CartItem>();
    }

    //Add cart item to cart
    public void addToCart(CartItem item)
    {
        custCart.add(item);
    }

    //Remove cart item from cart
    public void removeFromCart(Product item)
    {
        for (CartItem cartItems : custCart)
        {
            if (cartItems.getItemId().equals(item.getId()))
            {
                custCart.remove(cartItems);
            }
        }
    }
    //Print all items in cart
    public void print()
    {
        for (CartItem item : custCart)
        {
            item.print();
        }
    }
}