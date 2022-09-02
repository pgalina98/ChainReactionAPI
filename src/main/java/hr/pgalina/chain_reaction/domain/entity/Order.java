package hr.pgalina.chain_reaction.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "order", schema = "public")
public class Order extends BaseAuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order")
    private Long idOrder;

    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id_user")
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_discount_code", referencedColumnName = "id_discount_code")
    private DiscountCode discountCode;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderProduct> products;

    @Column(name = "buyer")
    private String buyer;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "delivery_type")
    private Short deliveryType;

    @Column(name = "city")
    private String city;

    @Column(name = "address")
    private String address;

    @Column(name = "zip_code")
    private String zipCode;

    @Column(name = "payment_method")
    private Short paymentMethod;

    @Column(name = "card_number")
    private String cardNumber;

    @Column(name = "expiration_date")
    private String expirationDate;

    @Column(name = "cardholder")
    private String cardholder;

    @Column(name = "cvv")
    private String cvv;

    @Column(name = "status")
    private Short status;

    @Column(name = "total")
    private Double total;
}
