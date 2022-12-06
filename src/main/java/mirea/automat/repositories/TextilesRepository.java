package mirea.automat.repositories;

import mirea.automat.models.Textile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TextilesRepository extends JpaRepository<Textile,Integer> {
}
