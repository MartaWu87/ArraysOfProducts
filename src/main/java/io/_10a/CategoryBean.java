package io._10a;

import io._10a.controller.CategoryController;
import io._10a.controller.ProductsController;
import io._10a.entity.Category;


import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@Named
@ViewScoped
public class CategoryBean implements Serializable {

    @Inject
    CategoryController categoryController;

    @Inject
    ProductsController productsController;

    private Long category_id;

    private String name;
    private List<Category> categoryList;


    @PostConstruct
    public void init()  {
        categoryList = categoryController.allCategory();
    }

    public List<Category> allCategory() {
        categoryList = categoryController.allCategory();
        return categoryList;
    }

    public void findByName(String name) {
        categoryController.findByName(name);
    }
}
