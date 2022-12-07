package mirea.automat.services;

import mirea.automat.models.Gost;
import mirea.automat.models.SafetyRule;
import mirea.automat.repositories.GostsRepository;
import mirea.automat.repositories.SafetyRulesRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class GostsService {
    private final GostsRepository gostsRepository;

    public GostsService(GostsRepository gostsRepository) {
        this.gostsRepository = gostsRepository;
    }

    public List<Gost> findAll(){
        return gostsRepository.findAll();
    }

    public Gost findOne(int id){
        Optional<Gost> foundGost =  gostsRepository.findById(id);
        return foundGost.orElse(null);
    }

    @Transactional
    public void save(Gost gost){
        gostsRepository.save(gost);
    }

    @Transactional
    public void update(int id, Gost updatedGost){
        updatedGost.setId(id);
        gostsRepository.save(updatedGost);
    }

    @Transactional
    public void delete(int id){
        gostsRepository.deleteById(id);
    }

    public void test(){
        System.out.println("Testing here with debug. Inside Hibernate transaction");
    }
}
