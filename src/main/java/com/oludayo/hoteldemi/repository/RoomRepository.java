package com.oludayo.hoteldemi.repository;

import java.util.List;
import com.oludayo.hoteldemi.entity.Booking;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends CrudRepository<Booking, Long> {

    List<Booking> findByFirstNameAndLastNameEquals(String firstName, String lastName);
}
