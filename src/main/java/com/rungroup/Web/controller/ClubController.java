package com.rungroup.Web.controller;

import com.rungroup.Web.dto.ClubDto;
import com.rungroup.Web.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
}
