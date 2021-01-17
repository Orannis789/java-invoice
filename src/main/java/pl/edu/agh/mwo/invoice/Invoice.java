package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {
    private Map<Product, Integer> products = new LinkedHashMap<>();
	
    public void addProduct(Product product) {
    	this.addProduct(product, 1);
    }

    public void addProduct(Product product, Integer quantity) {
    	 if (quantity <=0 ) {
         	throw new IllegalArgumentException("Name cannot be null or empty");
         }
    	this.products.put(product, quantity);
    }

    public BigDecimal getNetPrice() {
        BigDecimal netPrice = BigDecimal.ZERO;
        
        for(Product product: this.products.keySet()) {
        	 Integer quantity = this.products.get(product);
        	 netPrice = netPrice.add(new BigDecimal(quantity).multiply(product.getPrice())) ;
        }
        return netPrice;
    }

    public BigDecimal getTax() {
    	 BigDecimal tax = BigDecimal.ZERO; 
         tax = this.getGrossPrice().subtract(getNetPrice());
         return tax;
    }
    
    public BigDecimal getGrossPrice() {
    	BigDecimal grossPrice = BigDecimal.ZERO;
        
        for(Product product: this.products.keySet()) {
        	 Integer quantity = this.products.get(product);
        	 grossPrice= grossPrice.add(new BigDecimal(quantity).multiply(product.getPriceWithTax())) ;
        }
        return grossPrice;
    }
}
