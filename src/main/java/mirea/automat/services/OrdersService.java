package mirea.automat.services;

import mirea.automat.models.Order;
import mirea.automat.models.Producer;
import mirea.automat.models.SafetyRule;
import mirea.automat.models.Textile;
import mirea.automat.repositories.OrdersRepository;
import mirea.automat.repositories.SafetyRulesRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class OrdersService {
    private final OrdersRepository ordersRepository;

    public OrdersService(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    public List<Order> findAll(){
        return ordersRepository.findAll();
    }

    public List<Order> findAll(boolean sortByName) {
        if (sortByName)
            return ordersRepository.findAll(Sort.by("name"));
        else
            return ordersRepository.findAll();
    }

    public List<Order> findWithPagination(Integer page, Integer ordersPerPage, boolean sortByName) {
        if (sortByName)
            return ordersRepository.findAll(PageRequest.of(page, ordersPerPage, Sort.by("name"))).getContent();
        else
            return ordersRepository.findAll(PageRequest.of(page, ordersPerPage)).getContent();
    }

    public Order findOne(int id){
        Optional<Order> foundOrder =  ordersRepository.findById(id);
        return foundOrder.orElse(null);
    }

    @Transactional
    public void save(Order order){
        ordersRepository.save(order);
    }

    @Transactional
    public void update(int id, Order updatedOrder){
        updatedOrder.setId(id);
        ordersRepository.save(updatedOrder);
    }

    @Transactional
    public void delete(int id){
        ordersRepository.deleteById(id);
    }

    public List<Order> searchByName(String query) {
        return ordersRepository.findByNameStartingWith(query);
    }

    public void test(){
        System.out.println("Testing here with debug. Inside Hibernate transaction");
    }
}
