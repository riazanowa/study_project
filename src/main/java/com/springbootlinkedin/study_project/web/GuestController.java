package com.springbootlinkedin.study_project.web;

import com.springbootlinkedin.study_project.business.domain.GuestDTO;
import com.springbootlinkedin.study_project.business.service.GuestService;
import com.springbootlinkedin.study_project.data.entity.Guest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import java.util.List;

@Controller
@RequestMapping("/guests")
public class GuestController {

    private final GuestService guestService;

    @Autowired
    public GuestController(GuestService guestService) {
        this.guestService = guestService;
    }

    @GetMapping
    public String getAllGuests(Model model) {
        List<GuestDTO> guests = guestService.getAllGuests();
        model.addAttribute("guests", guests);
        return "guests";
    }
}
