package com.rungroup.Web.repository;

import com.rungroup.Web.models.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ClubRepository extends JpaRepository<Club, Long>
{
    Optional<Club> findByTitle(String title);
    @Query("SELECT c FROM Club c WHERE LOWER(c.title) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<Club> searchClubs(String query);
}
