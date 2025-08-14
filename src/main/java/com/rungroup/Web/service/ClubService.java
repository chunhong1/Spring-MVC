package com.rungroup.Web.service;

import com.rungroup.Web.dto.ClubDto;
import com.rungroup.Web.models.Club;

import java.util.List;

public interface ClubService
{
    List<ClubDto> findallClubs();
    Club saveClub (Club club);
}
