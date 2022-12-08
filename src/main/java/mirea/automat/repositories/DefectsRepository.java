package mirea.automat.repositories;

import mirea.automat.models.Defect;
import mirea.automat.models.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DefectsRepository extends JpaRepository<Defect,Integer> {
    List<Defect> findByDefectCaseStartingWith(String query);
}
