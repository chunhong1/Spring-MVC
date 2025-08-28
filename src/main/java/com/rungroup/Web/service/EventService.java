package com.rungroup.Web.service;

import com.rungroup.Web.dto.EventDto;

import java.util.List;

public interface EventService
{
    void createEvent(Long clubId, EventDto eventDto);

    List<EventDto> findAllEvents();
}
