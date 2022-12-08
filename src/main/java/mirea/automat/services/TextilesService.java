package mirea.automat.services;

import mirea.automat.models.Textile;
import mirea.automat.repositories.TextilesRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class TextilesService {
    private final TextilesRepository textilesRepository;

    public TextilesService(TextilesRepository textilesRepository) {
        this.textilesRepository = textilesRepository;
    }

    public List<Textile> findAll(){
        return textilesRepository.findAll();
    }

    public List<Textile> findAll(boolean sortByType) {
        if (sortByType)
            return textilesRepository.findAll(Sort.by("type"));
        else
            return textilesRepository.findAll();
    }

    public List<Textile> findWithPagination(Integer page, Integer statusesPerPage, boolean sortByType) {
        if (sortByType)
            return textilesRepository.findAll(PageRequest.of(page, statusesPerPage, Sort.by("type"))).getContent();
        else
            return textilesRepository.findAll(PageRequest.of(page, statusesPerPage)).getContent();
    }

    public Textile findOne(int id){
        Optional<Textile> foundTextile =  textilesRepository.findById(id);
        return foundTextile.orElse(null);
    }

    @Transactional
    public void save(Textile textile){
        textilesRepository.save(textile);
    }

    @Transactional
    public void update(int id, Textile updatedTextile){
        updatedTextile.setId(id);
        textilesRepository.save(updatedTextile);
    }

    @Transactional
    public void delete(int id){
        textilesRepository.deleteById(id);
    }

    public List<Textile> searchByName(String query) {
        return textilesRepository.findByNameStartingWith(query);
    }
    public void test(){
        System.out.println("Testing here with debug. Inside Hibernate transaction");
    }
}
