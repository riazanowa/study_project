package com.springbootlinkedin.study_project.data.repository;

import com.springbootlinkedin.study_project.data.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

}