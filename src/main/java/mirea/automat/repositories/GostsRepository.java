package mirea.automat.repositories;

import mirea.automat.models.Gost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GostsRepository extends JpaRepository<Gost,Integer> {
}
