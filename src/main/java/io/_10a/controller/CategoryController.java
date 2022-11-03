package io._10a.controller;

import io._10a.entity.Category;
import io._10a.entity.Products;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Stateless
public class CategoryController {

    @PersistenceContext
    EntityManager entityManager;

    public List<Category> allCategory() {
        return entityManager.createNamedQuery("Category.findAll", Category.class).getResultList();
    }
    public Category findByName(String name) {
        return entityManager.createNamedQuery("Category.findByName", Category.class).setParameter("name", name).getSingleResult();
    }

    public Category findById(Long categoryId) {
        return entityManager.createNamedQuery("Category.findById", Category.class).setParameter("categoryId", categoryId).getSingleResult();
    }
}
