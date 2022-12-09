package mirea.automat.services;

import mirea.automat.models.*;
import mirea.automat.models.Quality;
import mirea.automat.repositories.QualitiesRepository;
import mirea.automat.repositories.SafetyRulesRepository;
import org.hibernate.Hibernate;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
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
    public List<Quality> findAll(boolean sortByCondition) {
        if (sortByCondition)
            return qualitiesRepository.findAll(Sort.by("condition"));
        else
            return qualitiesRepository.findAll();
    }

    public List<Quality> findWithPagination(Integer page, Integer qualitiesPerPage, boolean sortByCondition) {
        if (sortByCondition)
            return qualitiesRepository.findAll(PageRequest.of(page, qualitiesPerPage, Sort.by("condition"))).getContent();
        else
            return qualitiesRepository.findAll(PageRequest.of(page, qualitiesPerPage)).getContent();
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

    public List<Quality> searchByCondition(String query) {
        return qualitiesRepository.findByConditionStartingWith(query);
    }
    public List<Cloth> getClothesByQualityId(int id) {
        Optional<Quality> quality = qualitiesRepository.findById(id);

        if (quality.isPresent()) {
            Hibernate.initialize(quality.get().getClothes());
            return quality.get().getClothes();
        }
        else {
            return Collections.emptyList();
        }
    }
    public void test(){
        System.out.println("Testing here with debug. Inside Hibernate transaction");
    }
}
