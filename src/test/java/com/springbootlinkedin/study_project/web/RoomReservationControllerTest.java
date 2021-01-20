package com.springbootlinkedin.study_project.web;

import com.springbootlinkedin.study_project.business.domain.RoomReservation;
import com.springbootlinkedin.study_project.business.service.ReservationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(RoomReservationController.class)
public class RoomReservationControllerTest {
    @MockBean
    ReservationService reservationService;

    @Autowired
    MockMvc mockMvc;


    @Test
    public void getReservations() throws Exception {
        String dateString = "2020-01-01";
        Date date = DateUtils.createDatefromDateString(dateString);
        List<RoomReservation> roomReservationsList = new ArrayList<>();
        RoomReservation roomReservation = new RoomReservation();
        roomReservation.setFirstName("TestFirstName");
        roomReservation.setLastName("TestLastName");
        roomReservation.setDate(date);
        roomReservation.setGuestId(1);
        roomReservation.setRoomId(100);
        roomReservation.setRoomNumber("1a");
        roomReservation.setRoomName("WOOP");
        roomReservationsList.add(roomReservation);
        given(reservationService.getRoomReservationForDate(date)).willReturn(roomReservationsList);

        this.mockMvc.perform(get("/reservations?date=2020-01-01"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("TestLastName, TestFirstName")));
    }


}
