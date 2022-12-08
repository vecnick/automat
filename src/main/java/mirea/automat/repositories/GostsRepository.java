package mirea.automat.repositories;

import mirea.automat.models.Gost;
import mirea.automat.models.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GostsRepository extends JpaRepository<Gost,Integer> {
    List<Gost> findByNameStartingWith(String query);
}
