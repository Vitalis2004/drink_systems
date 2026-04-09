package com.drinksco.repository;

import com.drinksco.model.StockLevel;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockLevelRepository extends JpaRepository<StockLevel, Long> {

	List<StockLevel> findAllByOrderByBranchNameAscDrinkNameAsc();

	List<StockLevel> findAllByBranchIdOrderByDrinkNameAsc(Long branchId);

	Optional<StockLevel> findByBranchIdAndDrinkId(Long branchId, Long drinkId);
}
