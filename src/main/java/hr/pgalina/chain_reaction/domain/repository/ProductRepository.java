package hr.pgalina.chain_reaction.domain.repository;

import hr.pgalina.chain_reaction.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, QuerydslPredicateExecutor<Product> {

    List<Product> findProductsByTypeAndName(Short productType, String name);

    Optional<Boolean> existsByIdProduct(Long idProduct);

    @Query(value = "SELECT (count(product.*) > 0) FROM Product product WHERE product.id_product =:idProduct AND product.available_quantity >=:quantity", nativeQuery = true)
    boolean isProductQuantityAvailable(Long idProduct, Short quantity);

    @Modifying
    @Query(value = "UPDATE Product SET available_quantity =:availableQuantity WHERE id_product =:idProduct", nativeQuery = true)
    void updateQuantityOfProduct(Long idProduct, Integer availableQuantity);
}
