package com.summertraining.iysproject.repo;

import com.summertraining.iysproject.model.Pages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagesRepository extends JpaRepository<Pages, Long> {
}
