package hr.pgalina.chain_reaction.domain.repository;

import hr.pgalina.chain_reaction.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, QuerydslPredicateExecutor<Product> {

    List<Product> findProductsByTypeAndName(Short productType, String name);

    Optional<Boolean> existsByIdProduct(Long idProduct);

    @Query(value = "SELECT (count(product.*) > 0) FROM Product product WHERE product.idProduct =:idProduct AND product.availableQuantity >=:quantity", nativeQuery = true)
    boolean isQuantityOfProductAvailable(Long idProduct, Integer quantity);
}
