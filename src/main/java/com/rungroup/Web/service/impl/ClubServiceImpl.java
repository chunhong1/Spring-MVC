package com.rungroup.Web.service.impl;

import com.rungroup.Web.dto.ClubDto;
import com.rungroup.Web.models.Club;
import com.rungroup.Web.models.UserEntity;
import com.rungroup.Web.repository.ClubRepository;
import com.rungroup.Web.repository.UserRepository;
import com.rungroup.Web.security.SecurityUtil;
import com.rungroup.Web.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.rungroup.Web.mapper.ClubMapper.mapToClub;
import static com.rungroup.Web.mapper.ClubMapper.mapToClubDto;

@Service
public class ClubServiceImpl implements ClubService
{
    private ClubRepository clubRepository;
    private UserRepository userRepository;

    @Autowired
    public ClubServiceImpl(ClubRepository clubRepository, UserRepository userRepository)
    {
        this.clubRepository = clubRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<ClubDto> findallClubs()
    {
        List<Club> clubs = clubRepository.findAll();
        return clubs.stream().map((club) -> mapToClubDto(club)).collect(Collectors.toList());
    }

    @Override
    public Club saveClub(ClubDto clubDto)
    {
        String username = SecurityUtil.getSessionUser();
        UserEntity user = userRepository.findByUsername(username);
        Club club = mapToClub(clubDto);
        club.setCreatedBy(user);
        return clubRepository.save(club);
    }

    @Override
    public ClubDto findClubById(long clubId)
    {
        Club club = clubRepository.findById(clubId).get();
        return mapToClubDto(club);
    }

    @Override
    public void updateClub(ClubDto clubDto)
    {
        String username = SecurityUtil.getSessionUser();
        UserEntity user = userRepository.findByUsername(username);
        Club club = mapToClub(clubDto);
        club.setCreatedBy(user);
        clubRepository.save(club);
    }

    @Override
    public void delete(long clubId)
    {
        clubRepository.deleteById(clubId);
    }

    @Override
    public List<ClubDto> searchClubs(String query)
    {
        List<Club> clubs = clubRepository.searchClubs(query);
        return clubs.stream().map(club -> mapToClubDto(club)).collect(Collectors.toList());
    }
}
