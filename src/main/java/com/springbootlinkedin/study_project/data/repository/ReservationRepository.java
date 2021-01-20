package com.springbootlinkedin.study_project.data.repository;

import com.springbootlinkedin.study_project.data.entity.Guest;
import com.springbootlinkedin.study_project.data.entity.Reservation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Long> {
    List<Reservation> findReservationByResDate(Date resDate);
}
