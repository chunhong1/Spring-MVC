package com.rungroup.Web.controller;

import com.rungroup.Web.dto.ClubDto;
import com.rungroup.Web.models.Club;
import com.rungroup.Web.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ClubController
{
    private ClubService clubService;

    @Autowired
    public ClubController(ClubService clubService)
    {
        this.clubService = clubService;
    }

    @GetMapping("/clubs")
    public String listClubs(Model model)
    {
        List<ClubDto> clubs = clubService.findallClubs();
        model.addAttribute("clubs", clubs);
        return "clubs-list";
    }

    @GetMapping("/clubs/create")
    public String createClubForm(Model model)
    {
        Club club = new Club();
        model.addAttribute("club", club);
        return "clubs-create";
    }

    @PostMapping("/clubs/create")
    public String saveClub(@ModelAttribute("club") Club club)
    {
        clubService.saveClub(club);
        return "redirect:/clubs";
    }

    @GetMapping("/clubs/{clubId}/edit")
    public String editClubForm(@PathVariable("clubId") long clubId, Model model)
    {
        ClubDto club = clubService.findClubById(clubId);
        model.addAttribute("club", club);
        return "clubs-edit";
    }

    @PostMapping("/clubs/{clubId}/edit")
    public String updateClub(@PathVariable("clubId") long clubId, @ModelAttribute("club") ClubDto club)
    {
        club.setId(clubId);
        clubService.updateClub(club);
        return "redirect:/clubs";
    }
}
