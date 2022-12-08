package mirea.automat.services;

import mirea.automat.models.Position;
import mirea.automat.models.SafetyRule;
import mirea.automat.models.Textile;
import mirea.automat.repositories.PositionsRepository;
import mirea.automat.repositories.SafetyRulesRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PositionsService {
    private final PositionsRepository positionsRepository;

    public PositionsService(PositionsRepository positionsRepository) {
        this.positionsRepository = positionsRepository;
    }

    public List<Position> findAll(){
        return positionsRepository.findAll();
    }

    public Position findOne(int id){
        Optional<Position> foundPosition =  positionsRepository.findById(id);
        return foundPosition.orElse(null);
    }

    @Transactional
    public void save(Position position){
        positionsRepository.save(position);
    }

    @Transactional
    public void update(int id, Position updatedPosition){
        updatedPosition.setId(id);
        positionsRepository.save(updatedPosition);
    }

    @Transactional
    public void delete(int id){
        positionsRepository.deleteById(id);
    }

    public List<Position> searchByName(String query) {
        return positionsRepository.findByNameStartingWith(query);
    }
    public void test(){
        System.out.println("Testing here with debug. Inside Hibernate transaction");
    }
}
