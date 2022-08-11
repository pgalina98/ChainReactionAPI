package hr.pgalina.chain_reaction.domain.repository;

import hr.pgalina.chain_reaction.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, QuerydslPredicateExecutor<Product> {

    List<Product> findProductsByType(Short productType);

    Optional<Boolean> existsByIdProduct(Long idProduct);
}
