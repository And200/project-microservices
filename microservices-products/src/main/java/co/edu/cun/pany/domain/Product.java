package co.edu.cun.pany.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "products")
@Data
@AllArgsConstructor @NoArgsConstructor @Builder
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @NotNull
    @NotEmpty(message = "The name must not be Null")
    @Column(name = "name",length = 50,nullable = false)
    private String name;

    @Column(name = "description",length = 150,nullable = false)
    private String description;




    @Column(name = "stock",length = 10,nullable = false)
    @Positive(message = "The Stock must be higher than 0")
    private Double stock;

    @Column(name = "price",length = 10,nullable = false)
    private Double price;

    @Column(name = "status",length = 50,nullable = false)
    private String status;


    @Column(name = "create_date")
        @Temporal(TemporalType.DATE)
    private Date createDate;

    /* You can add FetchType.LAZY or more if you want load the resources of the way anxious loading or not*/

   @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id",referencedColumnName = "id",
    foreignKey = @ForeignKey(name = "fk_category_product"))
   @Valid
   @NotNull(message = "The productCategory must not Be Null")
    private ProductCategory productCategory;

}
