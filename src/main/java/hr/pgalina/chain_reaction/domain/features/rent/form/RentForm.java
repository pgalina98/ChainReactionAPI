package hr.pgalina.chain_reaction.domain.features.rent.form;

import hr.pgalina.chain_reaction.domain.features.order.dto.ProductDto;
import hr.pgalina.chain_reaction.domain.features.rent.enumeration.Location;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@RequiredArgsConstructor
public class RentForm implements Serializable {

    private Long idUser;

    private ProductDto product;

    private ProductDto helmet;

    private Short helmetSize;

    private Location location;

    private LocalDate date;

    private List<LocalDateTime> timeslots;
}
