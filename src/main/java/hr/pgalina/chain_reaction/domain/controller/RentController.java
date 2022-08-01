package hr.pgalina.chain_reaction.domain.controller;

import hr.pgalina.chain_reaction.domain.features.rent.dto.LocationDto;
import hr.pgalina.chain_reaction.domain.features.rent.form.RentForm;
import hr.pgalina.chain_reaction.domain.features.rent.service.RentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/rents")
@RequiredArgsConstructor
public class RentController {

    private final RentService rentService;

    @GetMapping("/{idProduct}")
    public ResponseEntity<List<LocalDateTime>> fetchAvailableTimeslots(
        @PathVariable Long idProduct,
        @RequestParam Short idLocation,
        @RequestParam @DateTimeFormat(pattern = "dd.MM.yyyy.") LocalDate date
    ) {
        log.info("Entered '/api/rents' with product ID {}, idLocation {} and date {} [GET].", idProduct, idLocation, date);

        return new ResponseEntity<>(rentService.getAvailableTimeslots(idProduct, idLocation, date), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> saveRent(@RequestBody RentForm rentForm) {
        log.info("Entered '/api/rents' with product ID {}, idLocation {} and date {} [POST].",
            rentForm.getProduct().getIdProduct(),
            rentForm.getLocation().getIdLocation(),
            rentForm.getDate()
        );

        rentService.createRent(rentForm);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/available-locations")
    public ResponseEntity<List<LocationDto>> fetchAvailableRentLocations() {
        log.info("Entered '/api/rents/available-locations' [GET].");

        return new ResponseEntity<>(rentService.getAvailableRentLocations(), HttpStatus.OK);
    }
}
