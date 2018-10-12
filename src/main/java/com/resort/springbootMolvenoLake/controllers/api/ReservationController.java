package com.resort.springbootMolvenoLake.controllers.api;

import com.resort.springbootMolvenoLake.controllers.Controller;
import com.resort.springbootMolvenoLake.models.Guest;
import com.resort.springbootMolvenoLake.models.HotelReservation;
import com.resort.springbootMolvenoLake.models.Reservation;
import com.resort.springbootMolvenoLake.models.Room;
import com.resort.springbootMolvenoLake.repositories.HotelReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController("calendar_api_controller")
@RequestMapping("/api/calendar")
public class ReservationController extends Controller {

        @Autowired
        private HotelReservationRepository hotelReservationRepository;

        // region getAll
        @RequestMapping(method = RequestMethod.GET)
        public Iterable<HotelReservation> getAll() {
            return hotelReservationRepository.findAll();
        }
        // endregion getAll

        //region post
        // POST: that saves Entries in DB. REQUIRES A DATE DD/MM/YYYY format
        @RequestMapping(value = "{startDate}/{endDate}/{guestNr}_{roomNr}", method = RequestMethod.POST)
        public HotelReservation create(@PathVariable String startDate, @PathVariable String endDate,
                                       @PathVariable long guestNr, @PathVariable long roomNr)
        {
            Optional<Room> room = getHotel().getRoomController().findRoomById( roomNr );
            Optional<Guest> guest = getHotel().getGuestController().read( guestNr );
            if(!room.isPresent() || !guest.isPresent() )
                throw new RuntimeException();
            LocalDateTime start = LocalDateTime.parse(startDate + HotelReservation.START_TIME, Reservation.DF);
            LocalDateTime end = LocalDateTime.parse(endDate + HotelReservation.END_TIME, Reservation.DF);
            if(start.isAfter(end))
                throw new IllegalArgumentException();
            HotelReservation reservation = new HotelReservation(start, end, room.get(), guest.get());
            return hotelReservationRepository.save(reservation);
        }
        // endregion post

        //region RD_ID
        // GET: request that returns one Entries
        @RequestMapping(value = "{id}", method = RequestMethod.GET)
        public Optional<HotelReservation> find(@PathVariable long id)
        {
            return hotelReservationRepository.findById(id);
        }
        // DELETE: deletes Entries in DB
        @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
        public void deleteById(@PathVariable long id)
        {
            this.hotelReservationRepository.deleteById(id);
        }
        //endregion RD_ID

        //region getOnDate
        @RequestMapping(value = "y/{year}", method = RequestMethod.GET)
        public Iterable<HotelReservation> findAllByStartYear(@PathVariable Integer year)
        {
            System.out.println(hotelReservationRepository);
            Iterable<HotelReservation> reservations = hotelReservationRepository.findAllByStartYear(year);
            return reservations;
        }

        @RequestMapping(value = "ym/{year}-{month}", method = RequestMethod.GET)
        public Iterable<HotelReservation> findAllByStartYearAndStartMonth(@PathVariable Integer year,
                                                                          @PathVariable Integer month)
        {
            System.out.println(hotelReservationRepository);
            Iterable<HotelReservation> reservations = hotelReservationRepository.findAllByStartYearAndStartMonth(year, month);
            return reservations;
        }

        @RequestMapping(value = "ymd/{year}-{month}-{day}", method = RequestMethod.GET)
        public Iterable<HotelReservation> findAllByStartYearAndStartMonthAndDay(@PathVariable Integer year,
                                                                                @PathVariable Integer month,
                                                                                @PathVariable Integer day)
        {
            Iterable<HotelReservation> reservations = hotelReservationRepository.findAllByStartYearAndStartMonthAndStartDay(year, month, day);
            return reservations;
        }

        @RequestMapping(value = "ymdb/{date}", method = RequestMethod.GET)
        public Iterable<HotelReservation> findAllValid( @PathVariable String date )
        {
            LocalDateTime dateTime =  LocalDateTime.parse(date + HotelReservation.START_TIME, Reservation.DF );
            Iterable<HotelReservation> reservations =
                    hotelReservationRepository.findAllByStartLessThanEqualAndEndGreaterThanEqual(dateTime, dateTime);
            return reservations;
        }

        //endregion getOnDate
    }
