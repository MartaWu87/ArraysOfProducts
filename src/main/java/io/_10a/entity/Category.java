package io._10a.entity;

import io._10a.controller.CategoryController;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CATEGORY")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@NamedQueries({
        @NamedQuery(name = "Category.findAll", query = "SELECT c FROM Category c"),
        @NamedQuery(name = "Category.findById", query = "SELECT c FROM Category c WHERE c.category_id=:categoryId"),
        @NamedQuery(name = "Category.findByName", query = "SELECT c FROM Category c WHERE c.name=:name")
})
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CATEGORY_ID")
    private Long category_id;

    @Column(name = "NAME")
    private String name;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)  //, cascade = CascadeType.ALL, fetch = FetchType.EAGER, fetch = FetchType.LAZY, )
    private List<Products> products = new ArrayList<>();

    @Override
    public String toString() {
        return name;
    }

}
