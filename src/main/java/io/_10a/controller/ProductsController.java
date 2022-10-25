package io._10a.controller;


import io._10a.entity.Products;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Stateless
public class ProductsController {
    Products product;
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
    public void updateProduct(Products product) {
        entityManager.merge(product);
    }

}

