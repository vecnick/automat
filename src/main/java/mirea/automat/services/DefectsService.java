package mirea.automat.services;

import mirea.automat.models.Defect;
import mirea.automat.models.SafetyRule;
import mirea.automat.models.Textile;
import mirea.automat.repositories.DefectsRepository;
import mirea.automat.repositories.SafetyRulesRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class DefectsService {
    private final DefectsRepository defectsRepository;

    public DefectsService(DefectsRepository DefectsRepository) {
        this.defectsRepository = DefectsRepository;
    }

    public List<Defect> findAll(){
        return defectsRepository.findAll();
    }

    public Defect findOne(int id){
        Optional<Defect> foundDefect =  defectsRepository.findById(id);
        return foundDefect.orElse(null);
    }

    @Transactional
    public void save(Defect defect){
        defectsRepository.save(defect);
    }

    @Transactional
    public void update(int id, Defect updatedDefect){
        updatedDefect.setId(id);
        defectsRepository.save(updatedDefect);
    }

    @Transactional
    public void delete(int id){
        defectsRepository.deleteById(id);
    }

    public List<Defect> searchByName(String query) {
        return defectsRepository.findByDefectCaseStartingWith(query);
    }
    public void test(){
        System.out.println("Testing here with debug. Inside Hibernate transaction");
    }
}
