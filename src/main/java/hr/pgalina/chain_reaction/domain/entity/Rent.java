package hr.pgalina.chain_reaction.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Setter
@Getter
@Entity
@Table(name = "rent", schema = "public")
public class Rent extends BaseAuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rent")
    private Long idRent;

    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id_user")
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_product", referencedColumnName = "id_product")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "id_helmet", referencedColumnName = "id_product")
    private Product helmet;

    @Column(name = "helmet_size")
    private Short helmetSize;

    @Column(name = "location")
    private Short location;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "active_from")
    private LocalTime activeFrom;

    @Column(name = "active_to")
    private LocalTime activeTo;
}

