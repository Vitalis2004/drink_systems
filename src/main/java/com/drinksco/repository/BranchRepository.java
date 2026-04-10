package com.drinksco.repository;

import com.drinksco.model.Branch;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BranchRepository extends JpaRepository<Branch, Long> {

	Optional<Branch> findByNameIgnoreCase(String name);
}
