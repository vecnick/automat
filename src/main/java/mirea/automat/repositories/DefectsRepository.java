package mirea.automat.repositories;

import mirea.automat.models.Defect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DefectsRepository extends JpaRepository<Defect,Integer> {
}
