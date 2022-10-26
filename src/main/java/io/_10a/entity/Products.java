package io._10a.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Table(name = "PRODUCTS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@NamedQueries({
        @NamedQuery(name = "Products.findAll", query = "SELECT p FROM Products p"),
        @NamedQuery(name = "Products.startingWith", query = "SELECT p FROM Products p WHERE p.name LIKE :likeExpression"),
        @NamedQuery(name = "Products.find", query = "SELECT p FROM Products p WHERE p.id=:productId"),
        @NamedQuery(name = "Products.update", query = "UPDATE Products p SET p.name=:productName, p.quantity=:productQuantity WHERE p.id=:productId")
})
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCT_ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "QUANTITY")
    private Long quantity;

}
