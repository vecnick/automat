package mirea.automat.repositories;

import mirea.automat.models.Quality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QualitiesRepository extends JpaRepository<Quality,Integer> {
    List<Quality> findByConditionStartingWith(String query);
}
