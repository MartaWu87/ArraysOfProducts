package io._10a.controller;


import io._10a.ProductsBean;
import io._10a.entity.Products;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class ProductsController {

    @PersistenceContext
    EntityManager entityManager;

    public List<Products> allProducts() {
        return entityManager.createNamedQuery("Products.findAll", Products.class).getResultList();
    }

    public List<Products> findLike(String prefix) {
        return entityManager.createNamedQuery("Products.startingWith", Products.class).setParameter("likeExpression", prefix + "%").getResultList();
    }

    public void addProduct(Products product) {
        entityManager.persist(product);

    }

    public void deleteProduct(Long id) {
        entityManager.remove(findById(id));
    }

    public Products findById(Long id) {
            return entityManager.createNamedQuery("Products.find", Products.class).setParameter("productId", id).getSingleResult();
    }

    public Products updateProduct(Products product) {
        return entityManager.merge(product);
    }
}

