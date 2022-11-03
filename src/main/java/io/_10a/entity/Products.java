package io._10a.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.lang.reflect.Type;

@Entity
@Table(name = "PRODUCTS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@NamedQueries({
        @NamedQuery(name = "Products.findAll", query = "SELECT p FROM Products p JOIN FETCH p.category"),
        @NamedQuery(name = "Products.startingWith", query = "SELECT p FROM Products p WHERE p.name LIKE :likeExpression"),
        @NamedQuery(name = "Products.find", query = "SELECT p FROM Products p JOIN FETCH p.category WHERE p.product_id=:productId"),
        @NamedQuery(name = "Products.findByCategory", query = "SELECT p FROM Products p JOIN FETCH p.category WHERE p.category in :category_id"),
})
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCT_ID")
    private Long product_id;
    @Column(name = "NAME")
    private String name;

    @Column(name = "QUANTITY")
    private Long quantity;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "CATEGORY_ID", referencedColumnName = "CATEGORY_ID", nullable = false)//, insertable = false, updatable = false)
    private Category category;

    public void setCategory(Category category) {
        this.category = category;
    }

}
