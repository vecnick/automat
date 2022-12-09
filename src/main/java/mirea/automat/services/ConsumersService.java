package mirea.automat.services;

import mirea.automat.models.*;
import mirea.automat.models.Consumer;
import mirea.automat.repositories.ConsumersRepository;
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
public class ConsumersService {
    private final ConsumersRepository consumersRepository;

    public ConsumersService(ConsumersRepository ConsumersRepository) {
        this.consumersRepository = ConsumersRepository;
    }

    public List<Consumer> findAll(){
        return consumersRepository.findAll();
    }

    public List<Consumer> findAll(boolean sortBySecondName) {
        if (sortBySecondName)
            return consumersRepository.findAll(Sort.by("secondName"));
        else
            return consumersRepository.findAll();
    }

    public List<Consumer> findWithPagination(Integer page, Integer consumersPerPage, boolean sortBySecondName) {
        if (sortBySecondName)
            return consumersRepository.findAll(PageRequest.of(page, consumersPerPage, Sort.by("secondName"))).getContent();
        else
            return consumersRepository.findAll(PageRequest.of(page, consumersPerPage)).getContent();
    }

    public Consumer findOne(int id){
        Optional<Consumer> foundConsumer =  consumersRepository.findById(id);
        return foundConsumer.orElse(null);
    }

    @Transactional
    public void save(Consumer consumer){
        consumersRepository.save(consumer);
    }

    @Transactional
    public void update(int id, Consumer updatedConsumer){
        updatedConsumer.setId(id);
        consumersRepository.save(updatedConsumer);
    }

    @Transactional
    public void delete(int id){
        consumersRepository.deleteById(id);
    }

    public List<Consumer> searchByName(String query) {
        return consumersRepository.findByNameStartingWith(query);
    }
    public List<Order> getOrdersByConsumerId(int id) {
        Optional<Consumer> consumer = consumersRepository.findById(id);

        if (consumer.isPresent()) {
            Hibernate.initialize(consumer.get().getOrders());
            return consumer.get().getOrders();
        }
        else {
            return Collections.emptyList();
        }
    }
    public void test(){
        System.out.println("Testing here with debug. Inside Hibernate transaction");
    }
}
