package com.springbootlinkedin.study_project.data.repository;

import com.springbootlinkedin.study_project.data.entity.Guest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GuestRepository extends CrudRepository<Guest, Long> {
}
