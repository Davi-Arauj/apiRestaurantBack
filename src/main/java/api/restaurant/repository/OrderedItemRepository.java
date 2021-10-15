package api.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import api.restaurant.entity.OrderedItem;

@Repository
public interface OrderedItemRepository extends JpaRepository<OrderedItem, Long>{

}
