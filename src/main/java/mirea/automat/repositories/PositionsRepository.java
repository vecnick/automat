package mirea.automat.repositories;

import mirea.automat.models.Position;
import mirea.automat.models.Textile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PositionsRepository extends JpaRepository<Position,Integer> {
    List<Position> findByNameStartingWith(String query);
}
