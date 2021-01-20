package com.springbootlinkedin.study_project.business.service;

import com.springbootlinkedin.study_project.business.domain.GuestDTO;
import com.springbootlinkedin.study_project.data.entity.Guest;
import com.springbootlinkedin.study_project.data.repository.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class GuestService {
    private final GuestRepository guestRepository;

    @Autowired
    public GuestService(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    public List<GuestDTO> getAllGuests() {
        Iterable<Guest> guestList = guestRepository.findAll();

        List<GuestDTO> guests = new ArrayList<>();
        guestList.forEach(guest -> {
            GuestDTO guestDTO = new GuestDTO();
            guestDTO.setGuestId(guest.getGuestId());
            guestDTO.setFirstName(guest.getFirstName());
            guestDTO.setLastName(guest.getLastName());
            guestDTO.setEmail(guest.getEmailAddress());
            guestDTO.setPhoneNumber(guest.getPhoneNumber());
            guests.add(guestDTO);
        });
        guests.sort((o1, o2) -> {
            if (o1.getLastName() == o2.getLastName()) {
                return o1.getFirstName().compareTo(o2.getFirstName());
            }
            return o1.getLastName().compareTo(o2.getLastName());
        });
        return guests;
    }
}
