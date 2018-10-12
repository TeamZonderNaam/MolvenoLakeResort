package com.resort.springbootMolvenoLake.repositories;

import com.resort.springbootMolvenoLake.models.HotelReservation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface HotelReservationRepository extends CrudRepository<HotelReservation, Long>
{
    public Iterable<HotelReservation> findAllByStartYear(int startYear);
    public Iterable<HotelReservation> findAllByStartYearAndStartMonth(int startYear, int startMonth);
    public Iterable<HotelReservation> findAllByStartYearAndStartMonthAndStartDay(int startYear, int startMonth, int startDay);

    /*
    @Query(value = "SELECT * FROM RESERVATION R WHERE :year BETWEEN R.START_YEAR AND R.END_YEAR AND :month BETWEEN R.START_MONTH AND R.END_MONTH AND :day BETWEEN R.START_DAY AND R.END_DAY ", nativeQuery = true)
    public Iterable<HotelReservation> findAllReservationsBetweenStartAndEnd(@Param("year") Integer year,
                                                                            @Param("month") Integer month,
                                                                            @Param("day") Integer day);


    public Iterable<HotelReservation> findAllByDateBetweenStartAndEnd(LocalDateTime date);
    */

    public Iterable<HotelReservation> findAllByStartLessThanEqualAndEndGreaterThanEqual(LocalDateTime start, LocalDateTime end);
}
