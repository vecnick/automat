package mirea.automat.repositories;

import mirea.automat.models.Cloth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClothesRepository extends JpaRepository<Cloth,Integer> {
}
