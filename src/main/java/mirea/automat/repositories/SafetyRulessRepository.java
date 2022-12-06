package mirea.automat.repositories;

import mirea.automat.models.SafetyRules;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SafetyRulessRepository extends JpaRepository<SafetyRules,Integer> {
}
