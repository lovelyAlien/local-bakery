package com.localbakery.dataprovider.repository;

import com.localbakery.dataprovider.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Long> {
}
