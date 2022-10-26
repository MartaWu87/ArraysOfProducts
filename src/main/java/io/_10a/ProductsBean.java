package io._10a;


import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

import io._10a.controller.ProductsController;
import io._10a.entity.Products;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Setter
@Getter
@Named
@ViewScoped
//@ApplicationScoped
public class ProductsBean implements Serializable {

    Logger logger = LoggerFactory.getLogger(ProductsBean.class);
    @Inject
    ProductsController productsController;

    private Long id;
    private String name;
    private Long quantity;
    private List<Products> products;
    private String filter;


    @PostConstruct
    public void init() {
        products = productsController.allProducts();
    }

    public List<Products> getAllProducts() {
        return products;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;

    }

    public void takeAction() {
        this.products = productsController.findLike(filter);
    }

    public String addProduct() {
        Products product = new Products();
        product.setName(name);
        product.setQuantity(quantity);
        productsController.addProduct(product);
        return "index?faces-redirect=true";
    }

    public String deleteProduct(Long id) {
        productsController.deleteProduct(id);
        return "remove?faces-redirect=true";
    }

    public String insert() {
        return "insert?faces-redirect=true";
    }

    public String updateProduct(Long id) {
        Products product = productsController.findById(id);
        product.setId(id);
        product.setName(name);
        product.setQuantity(quantity);
        productsController.updateProduct(product);
        return "index?faces-redirect=true";

    }

    public String preUpdateProduct(Long id) {
        return "update?id=" + id + "&faces-redirect=true";
    }

    public String back() {
        return "index?faces-redirect=true";
    }
}
