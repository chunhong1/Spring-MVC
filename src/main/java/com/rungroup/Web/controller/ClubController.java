package com.rungroup.Web.controller;

import com.rungroup.Web.dto.ClubDto;
import com.rungroup.Web.models.Club;
import com.rungroup.Web.models.UserEntity;
import com.rungroup.Web.security.SecurityUtil;
import com.rungroup.Web.service.ClubService;
import com.rungroup.Web.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
public class ClubController
{
    private ClubService clubService;
    private UserService userService;

    @Autowired
    public ClubController(ClubService clubService, UserService userService)
    {
        this.clubService = clubService;
        this.userService = userService;
    }

    @GetMapping("/clubs")
    public String listClubs(Model model)
    {
        UserEntity user = new UserEntity();
        List<ClubDto> clubs = clubService.findallClubs();
        String username = SecurityUtil.getSessionUser();
        if(username != null)
        {
            user = userService.findByUsername(username);
            model.addAttribute("user", user);
        }
        model.addAttribute("user", user);
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
    public String saveClub(@Valid @ModelAttribute("club") ClubDto clubDto, BindingResult result, Model model)
    {
        if(result.hasErrors())
        {
            // Pass the info back to create page
            model.addAttribute("club", clubDto);
            return "clubs-create";
        }

        clubService.saveClub(clubDto);
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
    public String updateClub(@PathVariable("clubId") long clubId,
                             @Valid @ModelAttribute("club") ClubDto club,
                             BindingResult result,
                             Model model)
    {
        if(result.hasErrors())
        {
            model.addAttribute("club", club);
            return "clubs-edit";
        }

        club.setId(clubId);
        clubService.updateClub(club);
        return "redirect:/clubs";
    }

    @GetMapping("/clubs/{clubId}")
    public String clubDetail(@PathVariable("clubId") long clubId, Model model)
    {
        UserEntity user = new UserEntity();
        ClubDto clubDto = clubService.findClubById(clubId);
        String username = SecurityUtil.getSessionUser();
        if(username != null)
        {
            user = userService.findByUsername(username);
            model.addAttribute("user", user);
        }
        model.addAttribute("user", user);
        model.addAttribute("club", clubDto);
        return "clubs-details";
    }

    @GetMapping("/clubs/{clubId}/delete")
    public String deleteClub(@PathVariable("clubId") long clubId)
    {
        clubService.delete(clubId);
        return "redirect:/clubs";
    }

    @GetMapping("/clubs/search")
    public String searchClub(@RequestParam(value = "query") String query, Model model)
    {
        UserEntity user = new UserEntity();
        List<ClubDto> clubs = clubService.searchClubs(query);
        String username = SecurityUtil.getSessionUser();
        if(username != null)
        {
            user = userService.findByUsername(username);
            model.addAttribute("user", user);
        }
        model.addAttribute("user", user);
        model.addAttribute("clubs", clubs);
        return "clubs-list";
    }
}
