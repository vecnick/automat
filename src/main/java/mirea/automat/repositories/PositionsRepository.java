package mirea.automat.repositories;

import mirea.automat.models.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionsRepository extends JpaRepository<Position,Integer> {
}
