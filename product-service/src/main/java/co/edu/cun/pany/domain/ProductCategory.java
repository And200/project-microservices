package co.edu.cun.pany.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "product_categories")
@Data
@AllArgsConstructor @NoArgsConstructor @Builder
public class ProductCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "category",nullable = false)
    private String category;

  @JsonIgnore
    @OneToMany(mappedBy = "productCategory")
    private Set<Product> productList;


}
