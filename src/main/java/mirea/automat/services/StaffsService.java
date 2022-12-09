package mirea.automat.services;

import mirea.automat.models.Cloth;
import mirea.automat.models.Order;
import mirea.automat.models.Staff;
import mirea.automat.models.Textile;
import mirea.automat.repositories.StaffsRepository;
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
public class StaffsService {
    private final StaffsRepository staffsRepository;

    public StaffsService(StaffsRepository staffsRepository) {
        this.staffsRepository = staffsRepository;
    }

    public List<Staff> findAll(){
        return staffsRepository.findAll();
    }

    public List<Staff> findAll(boolean sortBySecondname) {
        if (sortBySecondname)
            return staffsRepository.findAll(Sort.by("secondname"));
        else
            return staffsRepository.findAll();
    }

    public List<Staff> findWithPagination(Integer page, Integer staffsPerPage, boolean sortBySecondname) {
        if (sortBySecondname)
            return staffsRepository.findAll(PageRequest.of(page, staffsPerPage, Sort.by("secondname"))).getContent();
        else
            return staffsRepository.findAll(PageRequest.of(page, staffsPerPage)).getContent();
    }

    public Staff findOne(int id){
        Optional<Staff> foundPerson =  staffsRepository.findById(id);
        return foundPerson.orElse(null);
    }

    @Transactional
    public void save(Staff staff){
        staffsRepository.save(staff);
    }

    @Transactional
    public void update(int id, Staff updatedStaff){
        updatedStaff.setId(id);
        staffsRepository.save(updatedStaff);
    }

    @Transactional
    public void delete(int id){
        staffsRepository.deleteById(id);
    }

    public List<Staff> searchByName(String query) {
        return staffsRepository.findByNameStartingWith(query);
    }
    public List<Order> getOrdersByStaffId(int id) {
        Optional<Staff> staff = staffsRepository.findById(id);

        if (staff.isPresent()) {
            Hibernate.initialize(staff.get().getOrders());
            return staff.get().getOrders();
        }
        else {
            return Collections.emptyList();
        }
    }

    public void test(){
        System.out.println("Testing here with debug. Inside Hibernate transaction");
    }
}