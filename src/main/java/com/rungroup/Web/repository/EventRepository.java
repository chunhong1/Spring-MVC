package com.rungroup.Web.repository;

import com.rungroup.Web.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long>
{
}
