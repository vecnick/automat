package mirea.automat.services;

import mirea.automat.models.*;
import mirea.automat.repositories.DefectsRepository;
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
public class DefectsService {
    private final DefectsRepository defectsRepository;

    public DefectsService(DefectsRepository DefectsRepository) {
        this.defectsRepository = DefectsRepository;
    }

    public List<Defect> findAll(){
        return defectsRepository.findAll();
    }
    public List<Defect> findAll(boolean sortByName) {
        if (sortByName)
            return defectsRepository.findAll(Sort.by("name"));
        else
            return defectsRepository.findAll();
    }

    public List<Defect> findWithPagination(Integer page, Integer gostsPerPage, boolean sortByDefectCase) {
        if (sortByDefectCase)
            return defectsRepository.findAll(PageRequest.of(page, gostsPerPage, Sort.by("defectCase"))).getContent();
        else
            return defectsRepository.findAll(PageRequest.of(page, gostsPerPage)).getContent();
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
    public List<Quality> getQualitiesByDefectId(int id) {
        Optional<Defect> defect = defectsRepository.findById(id);

        if (defect.isPresent()) {
            Hibernate.initialize(defect.get().getQualities());
            return defect.get().getQualities();
        }
        else {
            return Collections.emptyList();
        }
    }
    public void test(){
        System.out.println("Testing here with debug. Inside Hibernate transaction");
    }
}
