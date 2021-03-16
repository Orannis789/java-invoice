package pl.edu.agh.mwo.invoice.product;

import java.math.BigDecimal;

public class ExciseProduct extends OtherProduct {

    public ExciseProduct(String name, BigDecimal price) {
        super(name, price);
    }

    public BigDecimal getPriceWithTax() {
        BigDecimal priceWithTax = super.getPriceWithTax();
        return priceWithTax.add(new BigDecimal("5.56"));
    }
}
