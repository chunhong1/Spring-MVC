package com.rungroup.Web.service;

import com.rungroup.Web.dto.EventDto;

public interface EventService
{
    void createEvent(Long clubId, EventDto eventDto);
}
