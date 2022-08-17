package hr.pgalina.chain_reaction.domain.features.rent.form;

import hr.pgalina.chain_reaction.domain.features.product.dto.ProductDto;
import hr.pgalina.chain_reaction.domain.features.rent.dto.LocationDto;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@RequiredArgsConstructor
public class RentForm implements Serializable {

    private Long idUser;

    private ProductDto product;

    private ProductDto helmet;

    private Short helmetSize;

    private LocationDto location;

    private LocalDate date;

    private List<LocalTime> timeslots;
}
