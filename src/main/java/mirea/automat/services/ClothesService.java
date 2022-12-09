package mirea.automat.services;

import mirea.automat.models.*;
import mirea.automat.models.Cloth;
import mirea.automat.repositories.ClothesRepository;
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
public class ClothesService {
    private final ClothesRepository clothesRepository;

    public ClothesService(ClothesRepository ClothesRepository) {
        this.clothesRepository = ClothesRepository;
    }

    public List<Cloth> findAll(){
        return clothesRepository.findAll();
    }

    public List<Cloth> findAll(boolean sortBySize) {
        if (sortBySize)
            return clothesRepository.findAll(Sort.by("size"));
        else
            return clothesRepository.findAll();
    }

    public List<Cloth> findWithPagination(Integer page, Integer clothesPerPage, boolean sortBySize) {
        if (sortBySize)
            return clothesRepository.findAll(PageRequest.of(page, clothesPerPage, Sort.by("size"))).getContent();
        else
            return clothesRepository.findAll(PageRequest.of(page, clothesPerPage)).getContent();
    }

    public Cloth findOne(int id){
        Optional<Cloth> foundCloth =  clothesRepository.findById(id);
        return foundCloth.orElse(null);
    }

    @Transactional
    public void save(Cloth cloth){
        clothesRepository.save(cloth);
    }

    @Transactional
    public void update(int id, Cloth updatedCloth){
        updatedCloth.setId(id);
        clothesRepository.save(updatedCloth);
    }

    @Transactional
    public void delete(int id){
        clothesRepository.deleteById(id);
    }

    public List<Cloth> searchByName(String query) {
        return clothesRepository.findByNameStartingWith(query);
    }
    public List<Order> getOrdersByClothId(int id) {
        Optional<Cloth> cloth = clothesRepository.findById(id);

        if (cloth.isPresent()) {
            Hibernate.initialize(cloth.get().getOrders());
            return cloth.get().getOrders();
        }
        else {
            return Collections.emptyList();
        }
    }

    @Transactional
    public void releaseTextile(int id){
        clothesRepository.findById(id).ifPresent(
                cloth -> {
                    cloth.setTextile(null);
                });
    }
    @Transactional
    public void assignTextile(int id, Textile selectedTextile) {
        clothesRepository.findById(id).ifPresent(
                cloth -> {
                    cloth.setTextile(selectedTextile);
                }
        );
    }

    public void test(){
        System.out.println("Testing here with debug. Inside Hibernate transaction");
    }
}