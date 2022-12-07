package mirea.automat.services;

import mirea.automat.models.SafetyRule;
import mirea.automat.repositories.SafetyRulesRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SafetyRulesService {
    private final SafetyRulesRepository safetyRulesRepository;

    public SafetyRulesService(SafetyRulesRepository safetyRulesRepository) {
        this.safetyRulesRepository = safetyRulesRepository;
    }

    public List<SafetyRule> findAll(){
        return safetyRulesRepository.findAll();
    }

    public SafetyRule findOne(int id){
        Optional<SafetyRule> foundRule =  safetyRulesRepository.findById(id);
        return foundRule.orElse(null);
    }

    @Transactional
    public void save(SafetyRule safetyRule){
        safetyRulesRepository.save(safetyRule);
    }

    @Transactional
    public void update(int id, SafetyRule updatedSafetyRule){
        updatedSafetyRule.setId(id);
        safetyRulesRepository.save(updatedSafetyRule);
    }

    @Transactional
    public void delete(int id){
        safetyRulesRepository.deleteById(id);
    }

    public void test(){
        System.out.println("Testing here with debug. Inside Hibernate transaction");
    }
}
