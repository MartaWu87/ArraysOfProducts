package io._10a.controller;

import io._10a.entity.Category;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class CategoryController {

    @PersistenceContext
    EntityManager entityManager;

    public List<Category> allCategory() {
        return entityManager.createNamedQuery("Category.findAll", Category.class).getResultList();
    }
}
