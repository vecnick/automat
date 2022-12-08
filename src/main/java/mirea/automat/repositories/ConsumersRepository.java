package mirea.automat.repositories;

import mirea.automat.models.Consumer;
import mirea.automat.models.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsumersRepository extends JpaRepository<Consumer,Integer> {
    List<Consumer> findByNameStartingWith(String query);
}
