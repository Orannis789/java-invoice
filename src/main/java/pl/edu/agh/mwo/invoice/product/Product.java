package pl.edu.agh.mwo.invoice.product;

import java.math.BigDecimal;

public abstract class Product {
    private final String name;

    private final BigDecimal price;

    private final BigDecimal taxPercent;

    protected Product(String name, BigDecimal price, BigDecimal tax){
        if (name == null || name.equals("")) {
        	throw new IllegalArgumentException("Name cannot be null or empty");
        }
		if (price == null || price.compareTo(new BigDecimal("0")) ==-1) {
        	throw new IllegalArgumentException("Price cannot be null");
        }
    	this.name=name;
        this.price = price;
        this.taxPercent = tax;
    }

    public String getName() {
        return this.name;
    }

    public BigDecimal getPrice() {
		return price;
	}

	public BigDecimal getTaxPercent() {
		return taxPercent;
	}

	public BigDecimal getPriceWithTax() {
        
		BigDecimal PriceWithTax = this.price.add(this.price.multiply(this.taxPercent));
		
		return PriceWithTax;
    }
}
