package mirea.automat.services;

import mirea.automat.models.Producer;
import mirea.automat.models.SafetyRule;
import mirea.automat.models.Textile;
import mirea.automat.repositories.ProducersRepository;
import mirea.automat.repositories.SafetyRulesRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public void test(){
        System.out.println("Testing here with debug. Inside Hibernate transaction");
    }
}
