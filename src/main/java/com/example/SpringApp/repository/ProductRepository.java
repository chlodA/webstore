package com.example.SpringApp.repository;



import com.example.SpringApp.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
/*   List<Product> getAllProducts();

    void updateStock(String productId, long noOfUnits);*/
    //List<Product> findAllByActiveOrderByIdDesc(boolean active);

  //  Optional<Product> findByIdAndActive(Integer id, boolean active);

     List<Product> getProductsByDescription(String description);
    Product getProductById(String productID);
}