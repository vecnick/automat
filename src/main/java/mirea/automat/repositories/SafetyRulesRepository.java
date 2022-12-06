package mirea.automat.repositories;

import mirea.automat.models.SafetyRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SafetyRulesRepository extends JpaRepository<SafetyRule,Integer> {
}
