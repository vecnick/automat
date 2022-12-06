package mirea.automat.services;

import mirea.automat.models.SafetyRules;
import mirea.automat.repositories.SafetyRulessRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class StaffsService {
    private final SafetyRulessRepository staffsRepository;

    public StaffsService(SafetyRulessRepository staffsRepository) {
        this.staffsRepository = staffsRepository;
    }

    public List<SafetyRules> findAll(){
        return staffsRepository.findAll();
    }

    public SafetyRules findOne(int id){
        Optional<SafetyRules> foundPerson =  staffsRepository.findById(id);
        return foundPerson.orElse(null);
    }

    @Transactional
    public void save(SafetyRules staff){
        staffsRepository.save(staff);
    }

    @Transactional
    public void update(int id, SafetyRules updatedStaff){
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
