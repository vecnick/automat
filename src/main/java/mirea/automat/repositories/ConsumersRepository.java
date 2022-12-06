package mirea.automat.repositories;

import mirea.automat.models.Consumer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsumersRepository extends JpaRepository<Consumer,Integer> {
}
