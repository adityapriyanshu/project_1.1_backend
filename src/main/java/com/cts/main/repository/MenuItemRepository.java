package com.cts.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.main.entities.MenuItem;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
	
}
