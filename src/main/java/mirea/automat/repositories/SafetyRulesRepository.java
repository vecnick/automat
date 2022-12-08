package mirea.automat.repositories;

import mirea.automat.models.Producer;
import mirea.automat.models.SafetyRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SafetyRulesRepository extends JpaRepository<SafetyRule,Integer> {
    List<SafetyRule> findByNameStartingWith(String query);
}
