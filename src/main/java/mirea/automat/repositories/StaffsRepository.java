package mirea.automat.repositories;

import mirea.automat.models.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffsRepository extends JpaRepository<Staff,Integer> {
}
