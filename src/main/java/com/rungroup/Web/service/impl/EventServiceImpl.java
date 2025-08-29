package com.rungroup.Web.service.impl;

import com.rungroup.Web.dto.EventDto;
import com.rungroup.Web.models.Club;
import com.rungroup.Web.models.Event;
import com.rungroup.Web.repository.ClubRepository;
import com.rungroup.Web.repository.EventRepository;
import com.rungroup.Web.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.rungroup.Web.mapper.EventMapper.mapToEvent;
import static com.rungroup.Web.mapper.EventMapper.mapToEventDto;

@Service
public class EventServiceImpl implements EventService
{
    private EventRepository eventRepository;
    private ClubRepository clubRepository;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository, ClubRepository clubRepository)
    {
        this.eventRepository = eventRepository;
        this.clubRepository = clubRepository;
    }

    @Override
    public void createEvent(Long clubId, EventDto eventDto)
    {
        Club club = clubRepository.findById(clubId).get();
        Event event = mapToEvent(eventDto);
        event.setClub(club);
        eventRepository.save(event);
    }

    @Override
    public List<EventDto> findAllEvents()
    {
        List<Event> events = eventRepository.findAll();
        return events.stream().map(event -> mapToEventDto(event)).collect(Collectors.toList());
    }

    @Override
    public EventDto findByEventId(Long eventId)
    {
        Event event = eventRepository.findById(eventId).get();
        return mapToEventDto(event);
    }

    @Override
    public void updateEvent(EventDto eventDto)
    {
        Event event = mapToEvent(eventDto);
        eventRepository.save(event);
    }

    @Override
    public void deleteEvent(Long eventId)
    {
        eventRepository.deleteById(eventId);
    }


}
