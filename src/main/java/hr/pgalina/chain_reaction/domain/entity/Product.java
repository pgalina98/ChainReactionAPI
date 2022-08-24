package hr.pgalina.chain_reaction.domain.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@Immutable
@Table(name = "product", schema = "public")
public class Product extends BaseAuditEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_product")
    private Long idProduct;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "model")
    private String model;

    @Column(name = "assist_speed")
    private Integer assistSpeed;

    @Column(name = "battery_range")
    private Integer batteryRange;

    @Column(name = "charging_time")
    private Double chargingTime;

    @Column(name = "weight")
    private Double weight;

    @Column(name = "price")
    private Double price;

    @Column(name = "color")
    private Short color;

    @Column(name = "available_quantity")
    private Short availableQuantity;

    @Column(name = "for_rent")
    private Boolean forRent;

    @Column(name = "rent_price_per_hour")
    private Double rentPricePerHour;

    @Column(name = "image_path")
    private String imagePath;

    @Column(name = "type")
    private Short type;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Rent> rents = new ArrayList<>();
}
