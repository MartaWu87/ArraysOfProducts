package io._10a;


import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

import io._10a.controller.CategoryController;
import io._10a.controller.ProductsController;
import io._10a.entity.Category;
import io._10a.entity.Products;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Setter
@Getter
@Named
@ViewScoped
//@ApplicationScoped lub @
public class ProductsBean implements Serializable {

    Logger logger = LoggerFactory.getLogger(ProductsBean.class);
    @EJB
    ProductsController productsController;

    private Long product_id;
    private String name;
    private Long quantity;
    private List<Products> productsList;

    private Category category;
    private String filter;


    @PostConstruct
    public void init() {
        productsList = productsController.allProducts();
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public void takeAction() {
        this.productsList = productsController.findLike(filter);
    }

    public String addProduct() {
        Products product = new Products();
        product.setName(name);
        product.setQuantity(quantity);
        product.setCategory(category);
        productsController.addProduct(product, category);
        return "index?faces-redirect=true";
    }

    public String deleteProduct(Long product_id) {
        productsController.deleteProduct(product_id);
        return "remove?faces-redirect=true";
    }

    public String insert() {
        return "insert?faces-redirect=true";
    }

    public String preUpdateProduct(Long product_id) {
        return "update?id=" + product_id + "faces-redirect=true";
    }

    public String back() {
        return "index?faces-redirect=true";
    }

    public void sortByCategory(Long category_id) {
        this.productsList = productsController.sortByCategory(category_id);
    }

}
