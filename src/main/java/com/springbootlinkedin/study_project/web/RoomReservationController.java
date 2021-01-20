package com.springbootlinkedin.study_project.web;

import com.springbootlinkedin.study_project.business.domain.RoomReservation;
import com.springbootlinkedin.study_project.business.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/reservations")
public class RoomReservationController {

    private ReservationService reservationService;

    @Autowired
    public RoomReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }


    @GetMapping
    public String getReserrvations(@RequestParam(value = "date", required = false) String dateString, Model model) {
        Date date = DateUtils.createDatefromDateString(dateString);
        List<RoomReservation> roomReservations = reservationService.getRoomReservationForDate(date);
        model.addAttribute("roomReservations",roomReservations);;
        return "reservations";
    }
}
