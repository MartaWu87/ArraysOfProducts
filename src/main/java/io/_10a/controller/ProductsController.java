package io._10a.controller;


import io._10a.entity.Category;
import io._10a.entity.Category_;
import io._10a.entity.Products;
import io._10a.entity.Products_;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

import static io._10a.entity.Category_.category_id;


@Stateless
public class ProductsController {
    Products products;
    Category category;
    @PersistenceContext
    EntityManager entityManager;

    public List<Products> allProducts() {
        return entityManager.createNamedQuery("Products.findAll", Products.class).getResultList();
    }

    public List<Products> findLike(String prefix) {
        return entityManager.createNamedQuery("Products.startingWith", Products.class).setParameter("likeExpression", prefix + "%").getResultList();
    }

    public void addProduct(Products products, Category category) {
        entityManager.merge(products);
    }

    public void deleteProduct(Long product_id) {
        entityManager.remove(findById(product_id));
    }

    public Products findById(Long product_id) {
        return entityManager.createNamedQuery("Products.find", Products.class).setParameter("productId", product_id).getSingleResult();
    }

    public void updateProduct(Products product) {
        entityManager.merge(product);
    }


    public List<Products> sortByCategory(Long category_id) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Products> criteriaQuery = criteriaBuilder.createQuery(Products.class);
        Root<Products> products = criteriaQuery.from(Products.class);
        products.fetch(Products_.category);
        criteriaQuery.select(products);
        Predicate predicate = criteriaBuilder.equal(products.get(Products_.category), category_id);
        criteriaQuery.where(predicate);
        TypedQuery<Products> query = entityManager.createQuery(criteriaQuery);
        List<Products> productsList = query.getResultList();
        return productsList;
//        products.fetch(Products_.category, JoinType.LEFT);
//        Join<Products, Category> joinQuery = products.join(Products_.category);
//        return entityManager.createQuery(criteriaQuery.select(products).where(criteriaBuilder.in(joinQuery.get(Category_.category_id), category_id )).getResultList();
//    }
    }
}

