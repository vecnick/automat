package mirea.automat.repositories;

import mirea.automat.models.Producer;
import mirea.automat.models.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StaffsRepository extends JpaRepository<Staff,Integer> {
    List<Staff> findByNameStartingWith(String query);
}
