package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.table.TableStringConverter;

import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {
    private Map<Product, Integer> products = new LinkedHashMap<>();
    private static int nextNumber = 0;
    private final int number = ++nextNumber;

    public void addProduct(Product product) {
        addProduct(product, 1);
    }

    public void addProduct(Product product, Integer quantity) {
        if (product == null || quantity <= 0) {
            throw new IllegalArgumentException();
        }
        if(this.getProducts().containsKey(product)) {
           Integer existingQuantity = products.get(product);
           products.remove(product);
           products.put(product, quantity + existingQuantity);
        }
        else{
            products.put(product, quantity);
        }
    }

    public BigDecimal getNetTotal() {
        BigDecimal totalNet = BigDecimal.ZERO;
        for (Product product : products.keySet()) {
            BigDecimal quantity = new BigDecimal(products.get(product));
            totalNet = totalNet.add(product.getPrice().multiply(quantity));
        }
        return totalNet;
    }

    public BigDecimal getTaxTotal() {
        return getGrossTotal().subtract(getNetTotal());
    }

    public BigDecimal getGrossTotal() {
        BigDecimal totalGross = BigDecimal.ZERO;
        for (Product product : products.keySet()) {
            BigDecimal quantity = new BigDecimal(products.get(product));
            totalGross = totalGross.add(product.getPriceWithTax().multiply(quantity));
        }
        return totalGross;
    }

    public int getNumber() {
        return number;
    }
    
    public Map<Product, Integer> getProducts() {
        return products;
    }

    public String printInvoiceToSting() {      
            StringBuilder invoicePrint = new StringBuilder();
            invoicePrint.append("Invoice number: " + number + "\n");
            invoicePrint.append(String.format("%-20s","Name") + String.format("%-5s", "Count")+ String.format("%-15s","Price With Tax") + "\n");
            for (Product product : products.keySet()) {
                invoicePrint.append(String.format("%-20s", product.getName()) + String.format("%-5s", products.get(product)) + String.format("%-15s", product.getPriceWithTax()) + "\n");
            }
            invoicePrint.append("Number of items: " + products.size());
            return invoicePrint.toString();
    }
}
