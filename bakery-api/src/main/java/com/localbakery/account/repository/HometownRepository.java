package com.localbakery.account.repository;

import com.localbakery.account.domain.Hometown;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HometownRepository extends JpaRepository<Hometown, Long> {
}
