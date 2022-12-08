package mirea.automat.repositories;

import mirea.automat.models.Order;
import mirea.automat.models.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Order,Integer> {
    List<Order> findByNameStartingWith(String query);
}
