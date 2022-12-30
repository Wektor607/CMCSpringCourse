package ru.mikhelson.springcourse.SpringApp.repositories;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.mikhelson.springcourse.SpringApp.models.Product;

import java.util.List;

@Repository
public interface ProductsRepository extends JpaRepository<Product, Integer>
{
    List<Product> findAll();
    Product findProductByName(String productName);


}
