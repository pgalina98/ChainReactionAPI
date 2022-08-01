package hr.pgalina.chain_reaction.domain.mapper;

import hr.pgalina.chain_reaction.domain.entity.Rent;
import hr.pgalina.chain_reaction.domain.exception.BadRequestException;
import hr.pgalina.chain_reaction.domain.exception.contants.ErrorTypeConstants;
import hr.pgalina.chain_reaction.domain.features.rent.form.RentForm;
import hr.pgalina.chain_reaction.domain.repository.ProductRepository;
import hr.pgalina.chain_reaction.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static hr.pgalina.chain_reaction.domain.exception.contants.ExceptionMessages.PRODUCT_DOES_NOT_EXIST;
import static hr.pgalina.chain_reaction.domain.exception.contants.ExceptionMessages.USER_DOES_NOT_EXIST;

@Component
@RequiredArgsConstructor
public class RentMapper {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public List<Rent> mapToEntity(RentForm rentForm) {
        List<Rent> productRentals = new ArrayList<>();

        rentForm
            .getTimeslots()
            .forEach(
                timeslot -> {
                    Rent productRental = new Rent();

                    productRental.setUser(
                        userRepository
                            .findById(rentForm.getIdUser())
                            .orElseThrow(() -> new BadRequestException(ErrorTypeConstants.ERROR, HttpStatus.NOT_FOUND, USER_DOES_NOT_EXIST))
                    );
                    productRental.setProduct(
                        productRepository
                            .findById(rentForm.getProduct().getIdProduct())
                            .orElseThrow(() -> new BadRequestException(ErrorTypeConstants.ERROR, HttpStatus.NOT_FOUND, PRODUCT_DOES_NOT_EXIST))
                    );
                    productRental.setHelmet(
                        productRepository
                            .findById(rentForm.getHelmet().getIdProduct())
                            .orElseThrow(() -> new BadRequestException(ErrorTypeConstants.ERROR, HttpStatus.NOT_FOUND, PRODUCT_DOES_NOT_EXIST))
                    );
                    productRental.setHelmetSize(rentForm.getHelmetSize());
                    productRental.setLocation(rentForm.getLocation().getIdLocation());
                    productRental.setDate(rentForm.getDate());
                    productRental.setActiveFrom(timeslot);
                    productRental.setActiveTo(timeslot.plusHours(1));

                    productRentals.add(productRental);
                }
            );

        return productRentals;
    }
}
