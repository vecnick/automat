package mirea.automat.services;

import mirea.automat.models.Staff;
import mirea.automat.repositories.StaffsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class StaffsService {
    private final StaffsRepository staffsRepository;

    public StaffsService(StaffsRepository staffsRepository) {
        this.staffsRepository = staffsRepository;
    }

    public List<Staff> findAll(){
        return staffsRepository.findAll();
    }

    public Staff findOne(int id){
        Optional<Staff> foundPerson =  staffsRepository.findById(id);
        return foundPerson.orElse(null);
    }

    @Transactional
    public void save(Staff staff){
        staffsRepository.save(staff);
    }

    @Transactional
    public void update(int id, Staff updatedStaff){
        updatedStaff.setId(id);
        staffsRepository.save(updatedStaff);
    }

    @Transactional
    public void delete(int id){
        staffsRepository.deleteById(id);
    }

    public void test(){
        System.out.println("Testing here with debug. Inside Hibernate transaction");
    }
}
