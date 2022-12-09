package mirea.automat.services;

import mirea.automat.models.Cloth;
import mirea.automat.models.Producer;
import mirea.automat.models.SafetyRule;
import mirea.automat.models.Textile;
import mirea.automat.repositories.ProducersRepository;
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
public class ProducersService {
    private final ProducersRepository producersRepository;

    public ProducersService(ProducersRepository producersRepository) {
        this.producersRepository = producersRepository;
    }

    public List<Producer> findAll(){
        return producersRepository.findAll();
    }

    public List<Producer> findAll(boolean sortByName) {
        if (sortByName)
            return producersRepository.findAll(Sort.by("name"));
        else
            return producersRepository.findAll();
    }

    public List<Producer> findWithPagination(Integer page, Integer producersPerPage, boolean sortByName) {
        if (sortByName)
            return producersRepository.findAll(PageRequest.of(page, producersPerPage, Sort.by("name"))).getContent();
        else
            return producersRepository.findAll(PageRequest.of(page, producersPerPage)).getContent();
    }

    public Producer findOne(int id){
        Optional<Producer> foundProducer =  producersRepository.findById(id);
        return foundProducer.orElse(null);
    }

    @Transactional
    public void save(Producer producer){
        producersRepository.save(producer);
    }

    @Transactional
    public void update(int id, Producer updatedProducer){
        updatedProducer.setId(id);
        producersRepository.save(updatedProducer);
    }

    @Transactional
    public void delete(int id){
        producersRepository.deleteById(id);
    }

    public List<Producer> searchByName(String query) {
        return producersRepository.findByNameStartingWith(query);
    }

    public List<Cloth> getClothesByProducerId(int id) {
        Optional<Producer> producer = producersRepository.findById(id);

        if (producer.isPresent()) {
            Hibernate.initialize(producer.get().getClothes());
            return producer.get().getClothes();
        }
        else {
            return Collections.emptyList();
        }
    }
    public void test(){
        System.out.println("Testing here with debug. Inside Hibernate transaction");
    }
}

