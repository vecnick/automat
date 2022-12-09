package mirea.automat.services;

import mirea.automat.models.*;
import mirea.automat.models.SafetyRule;
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
public class SafetyRulesService {
    private final SafetyRulesRepository safetyRulesRepository;

    public SafetyRulesService(SafetyRulesRepository safetyRulesRepository) {
        this.safetyRulesRepository = safetyRulesRepository;
    }

    public List<SafetyRule> findAll(){
        return safetyRulesRepository.findAll();
    }
    public List<SafetyRule> findAll(boolean sortByRuleName) {
        if (sortByRuleName)
            return safetyRulesRepository.findAll(Sort.by("name"));
        else
            return safetyRulesRepository.findAll();
    }

    public List<SafetyRule> findWithPagination(Integer page, Integer safetyRulesPerPage, boolean sortByRuleName) {
        if (sortByRuleName)
            return safetyRulesRepository.findAll(PageRequest.of(page, safetyRulesPerPage, Sort.by("name"))).getContent();
        else
            return safetyRulesRepository.findAll(PageRequest.of(page, safetyRulesPerPage)).getContent();
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

    public List<SafetyRule> searchByName(String query) {
        return safetyRulesRepository.findByNameStartingWith(query);
    }
    public List<Staff> getStaffsBySafetyRuleId(int id) {
        Optional<SafetyRule> safetyRule = safetyRulesRepository.findById(id);

        if (safetyRule.isPresent()) {
            Hibernate.initialize(safetyRule.get().getStaffs());
            return safetyRule.get().getStaffs();
        }
        else {
            return Collections.emptyList();
        }
    }
    public void test(){
        System.out.println("Testing here with debug. Inside Hibernate transaction");
    }
}
