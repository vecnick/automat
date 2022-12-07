package mirea.automat.services;

import mirea.automat.models.Consumer;
import mirea.automat.models.SafetyRule;
import mirea.automat.repositories.ConsumersRepository;
import mirea.automat.repositories.SafetyRulesRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public void test(){
        System.out.println("Testing here with debug. Inside Hibernate transaction");
    }
}
