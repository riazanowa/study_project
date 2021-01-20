package com.springbootlinkedin.study_project.business.service;

import com.springbootlinkedin.study_project.business.domain.RoomReservation;
import com.springbootlinkedin.study_project.data.entity.Guest;
import com.springbootlinkedin.study_project.data.entity.Reservation;
import com.springbootlinkedin.study_project.data.entity.Room;
import com.springbootlinkedin.study_project.data.repository.GuestRepository;
import com.springbootlinkedin.study_project.data.repository.ReservationRepository;
import com.springbootlinkedin.study_project.data.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReservationService {
    private final RoomRepository roomRepository;
    private final GuestRepository guestRepository;
    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationService(RoomRepository roomRepository, GuestRepository guestRepository, ReservationRepository reservationRepository) {
        this.roomRepository = roomRepository;
        this.guestRepository = guestRepository;
        this.reservationRepository = reservationRepository;
    }

    public List<RoomReservation> getRoomReservationForDate(Date date) {
        Iterable<Room> rooms = roomRepository.findAll();
        Map<Long, RoomReservation> roomReservationMap = new HashMap<>();
        rooms.forEach((room)-> {
            RoomReservation roomReservation = new RoomReservation();
            roomReservation.setRoomId(room.getRoomId());
            roomReservation.setRoomName(room.getRoomName());
            roomReservation.setRoomNumber(room.getRoomNumber());
            roomReservationMap.put(room.getRoomId(), roomReservation);
        });
        List<Reservation> reservations = reservationRepository.findReservationByResDate(new java.sql.Date(date.getTime()));
        reservations.forEach((reservation -> {
           RoomReservation roomReservation = roomReservationMap.get(reservation.getRoomId());
           roomReservation.setDate(date);
            Guest guest = guestRepository.findById(reservation.getGuestId()).get();
            roomReservation.setGuestId(guest.getGuestId());
            roomReservation.setFirstName(guest.getFirstName());
            roomReservation.setLastName(guest.getLastName());
        }));
        List<RoomReservation> roomReservation = new ArrayList<>();
        for (Long roomId: roomReservationMap.keySet()) {
            roomReservation.add(roomReservationMap.get(roomId));
        }
        roomReservation.sort((o1, o2) -> {
            if (o1.getRoomName() == o2.getRoomName()){
                return o1.getRoomNumber().compareTo(o2.getRoomNumber());
            }
            return o1.getRoomName().compareTo(o2.getRoomName());
        });
        return roomReservation;
    }
}
