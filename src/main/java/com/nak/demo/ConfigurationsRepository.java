package com.nak.demo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;


public interface ConfigurationsRepository extends Repository<Configurations, String> {

    Page<Configurations> findAll(Pageable pageable);

}