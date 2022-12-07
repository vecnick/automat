package mirea.automat.services;

import mirea.automat.models.Quality;
import mirea.automat.models.SafetyRule;
import mirea.automat.repositories.QualitiesRepository;
import mirea.automat.repositories.SafetyRulesRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class QualitiesService {
    private final QualitiesRepository qualitiesRepository;

    public QualitiesService(QualitiesRepository qualitiesRepository) {
        this.qualitiesRepository = qualitiesRepository;
    }

    public List<Quality> findAll(){
        return qualitiesRepository.findAll();
    }

    public Quality findOne(int id){
        Optional<Quality> foundQuality =  qualitiesRepository.findById(id);
        return foundQuality.orElse(null);
    }

    @Transactional
    public void save(Quality quality){
        qualitiesRepository.save(quality);
    }

    @Transactional
    public void update(int id, Quality updatedQuality){
        updatedQuality.setId(id);
        qualitiesRepository.save(updatedQuality);
    }

    @Transactional
    public void delete(int id){
        qualitiesRepository.deleteById(id);
    }

    public void test(){
        System.out.println("Testing here with debug. Inside Hibernate transaction");
    }
}
