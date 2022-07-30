package hr.pgalina.chain_reaction.domain.repository;

import hr.pgalina.chain_reaction.domain.entity.Rent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RentRepository extends JpaRepository<Rent, Long> {

    @Query(value = "SELECT rent.* FROM Rent rent WHERE rent.date =:date", nativeQuery = true)
    List<Rent> findAllByDate(LocalDate date);
}
