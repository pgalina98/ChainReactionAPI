package hr.pgalina.chain_reaction.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Setter
@Getter
@Entity
@Table(name = "discount_code", schema = "public")
public class DiscountCode extends BaseAuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_discount_code")
    private Long idDiscountCode;

    @Column(name = "code")
    private String code;

    @Column(name = "discount")
    private Double discount;

    @Column(name = "active_from")
    private LocalDate activeFrom;

    @Column(name = "active_to")
    private LocalDate activeTo;
}
