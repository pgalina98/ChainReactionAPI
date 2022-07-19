package hr.pgalina.chain_reaction.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "product", schema = "public")
public class Product extends BaseAuditEntity {

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
    private Integer availableQuantity;

    @Column(name = "type")
    private Short type;
}
