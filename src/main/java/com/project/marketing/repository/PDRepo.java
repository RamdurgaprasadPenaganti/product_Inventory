package com.project.marketing.repository;

import com.project.marketing.entity.PDEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PDRepo extends JpaRepository<PDEntity,Long> {


    Page<PDEntity> findAllBySupplier(String name, Pageable page);

}
