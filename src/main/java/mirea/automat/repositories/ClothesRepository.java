package mirea.automat.repositories;

import mirea.automat.models.Cloth;
import mirea.automat.models.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClothesRepository extends JpaRepository<Cloth,Integer> {
    List<Cloth> findByNameStartingWith(String query);
}
