package hr.pgalina.chain_reaction.domain.controller;

import hr.pgalina.chain_reaction.domain.features.rent.service.RentService;
import hr.pgalina.chain_reaction.domain.features.rent.validator.RentValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/rents")
@RequiredArgsConstructor
public class RentController {

    private final RentValidator rentValidator;

    private final RentService rentService;

    @GetMapping("/{idProduct}")
    public ResponseEntity<List<LocalDateTime>> fetchAvailableTimeslots(
        @PathVariable Long idProduct,
        @RequestParam Short idLocation,
        @RequestParam @DateTimeFormat(pattern = "dd.MM.yyyy.") LocalDate date
    ) {
        log.info("Entered '/api/rents' with product ID {}, idLocation {} and date {} [GET].", idProduct, idLocation, date);

        rentValidator.validateLocation(idLocation);

        return new ResponseEntity<>(rentService.getAvailableTimeslots(idProduct, idLocation, date), HttpStatus.OK);
    }
}
