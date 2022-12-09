package mirea.automat.services;

import mirea.automat.models.*;
import mirea.automat.repositories.PositionsRepository;
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
public class PositionsService {
    private final PositionsRepository positionsRepository;

    public PositionsService(PositionsRepository positionsRepository) {
        this.positionsRepository = positionsRepository;
    }

    public List<Position> findAll(){
        return positionsRepository.findAll();
    }

    public List<Position> findAll(boolean sortByPositionName) {
        if (sortByPositionName)
            return positionsRepository.findAll(Sort.by("name"));
        else
            return positionsRepository.findAll();
    }

    public List<Position> findWithPagination(Integer page, Integer statusesPerPage, boolean sortByPositionName) {
        if (sortByPositionName)
            return positionsRepository.findAll(PageRequest.of(page, statusesPerPage, Sort.by("name"))).getContent();
        else
            return positionsRepository.findAll(PageRequest.of(page, statusesPerPage)).getContent();
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
    public List<Staff> getStaffsByPositionId(int id) {
        Optional<Position> position = positionsRepository.findById(id);

        if (position.isPresent()) {
            Hibernate.initialize(position.get().getStaffs());
            return position.get().getStaffs();
        }
        else {
            return Collections.emptyList();
        }
    }
    public void test(){
        System.out.println("Testing here with debug. Inside Hibernate transaction");
    }
}