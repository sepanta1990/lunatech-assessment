package com.lunatech.dao;

import com.lunatech.model.Name;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NameRepository extends JpaRepository<Name, String> {
}
