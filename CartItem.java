//Name: Reynold Villasenor-Doan
//Student No. 501031610

public class CartItem
{
    private Product cartItem;
    private String productOptions;

    //Creates a new cart item
    public CartItem()
    {
        this.cartItem = new Product();
    }

    //References product 
    public CartItem(Product item, String productOption)
    {
        this.cartItem = item;
        this.productOptions = productOption;
    }

    //Print cart item
    public void print()
    {
        cartItem.print();
        System.out.print(" : " + productOptions);
    }

    //Get product id 
    public String getItemId()
    {
        return cartItem.getId();
    }
}