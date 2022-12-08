package mirea.automat.repositories;


import mirea.automat.models.Position;
import mirea.automat.models.Producer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProducersRepository extends JpaRepository<Producer,Integer> {
    List<Producer> findByNameStartingWith(String query);
}
