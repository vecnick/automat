package mirea.automat.services;

import mirea.automat.models.Cloth;
import mirea.automat.models.SafetyRule;
import mirea.automat.models.Textile;
import mirea.automat.repositories.ClothesRepository;
import mirea.automat.repositories.SafetyRulesRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public void test(){
        System.out.println("Testing here with debug. Inside Hibernate transaction");
    }
}
