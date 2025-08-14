package com.rungroup.Web.service.impl;

import com.rungroup.Web.dto.ClubDto;
import com.rungroup.Web.models.Club;
import com.rungroup.Web.repository.ClubRepository;
import com.rungroup.Web.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClubServiceImpl implements ClubService
{
    private ClubRepository clubRepository;

    @Autowired
    public ClubServiceImpl(ClubRepository clubRepository)
    {
        this.clubRepository = clubRepository;
    }

    @Override
    public List<ClubDto> findallClubs()
    {
        List<Club> clubs = clubRepository.findAll();
        return clubs.stream().map((club) -> mapToClubDto(club)).collect(Collectors.toList());
    }

    @Override
    public Club saveClub(Club club)
    {
        return clubRepository.save(club);
    }

    private ClubDto mapToClubDto(Club club)
    {
        return ClubDto.builder()
                .id(club.getId())
                .title(club.getTitle())
                .photoUrl(club.getPhotoUrl())
                .content(club.getContent())
                .createdOn(club.getCreatedOn())
                .updatedOn(club.getUpdatedOn())
                .build();
    }
}
