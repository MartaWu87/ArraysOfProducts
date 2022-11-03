package io._10a;

import io._10a.controller.CategoryController;
import io._10a.controller.ProductsController;
import io._10a.entity.Category;
import io._10a.entity.Products;
import lombok.Getter;
import lombok.Setter;
import org.omnifaces.cdi.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Setter
@Getter
@Named
@ViewScoped
public class UpdateBean implements Serializable {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @EJB
    ProductsController productsController; //lub @Inject

    @Inject
    @Param(name = "id")
    private Long editedId;

//    @Inject
//    @Param(name = "category_id")
//    private Long categoryId;
//
//    private Category category;
    private Products product;

    @PostConstruct
    public void init() {
//        logger.info("Passed product id: {}", editedId);
        product = productsController.findById(editedId);
    }

    public String save() {
        productsController.updateProduct(product);
        return "/index?faces-redirect=true";
    }

}
