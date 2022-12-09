package mirea.automat.services;

import mirea.automat.models.*;
import mirea.automat.repositories.GostsRepository;
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
public class GostsService {
    private final GostsRepository gostsRepository;

    public GostsService(GostsRepository gostsRepository) {
        this.gostsRepository = gostsRepository;
    }

    public List<Gost> findAll(){
        return gostsRepository.findAll();
    }
    public List<Gost> findAll(boolean sortByName) {
        if (sortByName)
            return gostsRepository.findAll(Sort.by("name"));
        else
            return gostsRepository.findAll();
    }

    public List<Gost> findWithPagination(Integer page, Integer gostsPerPage, boolean sortByName) {
        if (sortByName)
            return gostsRepository.findAll(PageRequest.of(page, gostsPerPage, Sort.by("name"))).getContent();
        else
            return gostsRepository.findAll(PageRequest.of(page, gostsPerPage)).getContent();
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

    public List<Gost> searchByName(String query) {
        return gostsRepository.findByNameStartingWith(query);
    }
    public List<Quality> getQualitiesByGostId(int id) {
        Optional<Gost> gost = gostsRepository.findById(id);

        if (gost.isPresent()) {
            Hibernate.initialize(gost.get().getQualities());
            return gost.get().getQualities();
        }
        else {
            return Collections.emptyList();
        }
    }
    public List<Cloth> getClothesByGostId(int id) {
        Optional<Gost> gost = gostsRepository.findById(id);

        if (gost.isPresent()) {
            Hibernate.initialize(gost.get().getClothes());
            return gost.get().getClothes();
        }
        else {
            return Collections.emptyList();
        }
    }

    public void test(){
        System.out.println("Testing here with debug. Inside Hibernate transaction");
    }
}
