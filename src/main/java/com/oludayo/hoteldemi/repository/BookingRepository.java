package com.oludayo.hoteldemi.repository;

import java.util.List;
import com.oludayo.hoteldemi.entity.Booking;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Sort;

@Repository
public interface BookingRepository  extends PagingAndSortingRepository<Booking, Long>,CrudRepository<Booking, Long>  {
    @Query("select b from Booking b where b.person.firstName = :firstName and b.person.lastName = :lastName")
    List<Booking> findByPersonFirstNameAndLastName(@Param("firstName") String firstName, @Param("lastName") String lastName);
    List<Booking> findByPersonFirstName(String firstName, Sort sort);

    List<Booking> findByPersonLastName(String lastName, Sort sort);
    List<Booking> findAllByOrderByCheckInDateAsc();
    List<Booking> findAllByOrderByCheckInDateDesc();
    List<Booking> findAllByOrderByCheckOutDateAsc();
    List<Booking> findAllByOrderByCheckOutDateDesc();
}