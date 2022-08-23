package hr.pgalina.chain_reaction.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "order", schema = "public")
public class Order extends BaseAuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order")
    private Long idOrder;

    @Column(name = "buyer")
    private String buyer;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "delivery_type")
    private Integer deliveryType;

    @Column(name = "city")
    private String city;

    @Column(name = "address")
    private String address;

    @Column(name = "zip_code")
    private String zipCode;

    @Column(name = "payment_method")
    private Integer paymentMethod;

    @Column(name = "card_number")
    private String cardNumber;

    @Column(name = "expiration_date")
    private String expirationDate;

    @Column(name = "cardholder")
    private String cardholder;

    @Column(name = "cvv")
    private String cvv;

    @Column(name = "total")
    private Double total;

    @ManyToOne
    @JoinColumn(name = "id_discount_code", referencedColumnName = "id_discount_code")
    private DiscountCode discountCode;
}
