package mirea.automat.repositories;


import mirea.automat.models.Producer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProducersRepository extends JpaRepository<Producer,Integer> {
}
