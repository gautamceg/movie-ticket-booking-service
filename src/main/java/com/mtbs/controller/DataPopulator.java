/**
 * 
 */
package com.mtbs.controller;

import com.mtbs.enums.CertificateType;
import com.mtbs.enums.MovieLanguage;
import com.mtbs.enums.SeatType;
import com.mtbs.enums.TheaterType;
import com.mtbs.dao.entity.MovieEntity;
import com.mtbs.dao.entity.ShowEntity;
import com.mtbs.dao.entity.ShowSeatsEntity;
import com.mtbs.dao.entity.TheaterEntity;
import com.mtbs.dao.entity.TheaterSeatsEntity;
import com.mtbs.dao.entity.UserEntity;
import com.mtbs.dao.repository.MovieRepository;
import com.mtbs.dao.repository.ShowRepository;
import com.mtbs.dao.repository.ShowSeatsRepository;
import com.mtbs.dao.repository.TheaterRepository;
import com.mtbs.dao.repository.TheaterSeatsRepository;
import com.mtbs.dao.repository.TicketRepository;
import com.mtbs.dao.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


@Log4j2
@RestController
@RequestMapping("initialize")
public class DataPopulator {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ShowRepository showsRepository;

	@Autowired
	private MovieRepository movieRepository;

	@Autowired
	private TicketRepository ticketRepository;

	@Autowired
	private TheaterRepository theaterRepository;

	@Autowired
	private ShowSeatsRepository showSeatsRepository;

	@Autowired
	private TheaterSeatsRepository theaterSeatsRepository;

	@GetMapping("generate")
	public ResponseEntity<String> generate() {

		log.info("Deleting Data from Shows, Movies and Theaters");

		userRepository.deleteAllInBatch();
		ticketRepository.deleteAllInBatch();

		showSeatsRepository.deleteAllInBatch();
		theaterSeatsRepository.deleteAllInBatch();

		showsRepository.deleteAllInBatch();
		movieRepository.deleteAllInBatch();
		theaterRepository.deleteAllInBatch();
		movieRepository.deleteAllInBatch();

		log.info("Adding Starting Shows");

		UserEntity userEntity = UserEntity.builder().name("Gautam").mobile("1234567890").build();

		userRepository.save(userEntity);

		TheaterEntity pvrBengaluru = getTheater("PVR", "Bengaluru", "HSR Layout");
		TheaterEntity pvrHyd = getTheater("PVR", "Hyderabad", "MG Road");
		TheaterEntity cinePolisPune = getTheater("Cinepolis", "Pune", "Hinjewadi");

		MovieEntity spidermanMovie = getMovie("Spiderman", MovieLanguage.ENGLISH, CertificateType.UA, LocalDate.now());
		MovieEntity batmanMovie = getMovie("Batman", MovieLanguage.ENGLISH, CertificateType.UA, LocalDate.now().minusDays(1));

		List<ShowEntity> showEntities = new ArrayList<>();

		showEntities.add(getShow(LocalDate.now(), LocalTime.NOON, 1.0f, pvrBengaluru, spidermanMovie));
		showEntities.add(getShow(LocalDate.now(), LocalTime.NOON, 1.1f, pvrHyd, spidermanMovie));
		showEntities.add(getShow(LocalDate.now().plusDays(1), LocalTime.NOON, 1.0f, pvrBengaluru, spidermanMovie));
		showEntities.add(getShow(LocalDate.now().plusDays(1), LocalTime.NOON, 1.4f, pvrHyd, spidermanMovie));
		showEntities.add(getShow(LocalDate.now(), LocalTime.NOON, 1.2f, cinePolisPune, spidermanMovie));
		showEntities.add(getShow(LocalDate.now(), LocalTime.NOON.plusHours(1), 1.5f, cinePolisPune, batmanMovie));

		showsRepository.saveAll(showEntities);

		log.debug("Added " + showEntities.size() + " Shows");

		return ResponseEntity.ok(showEntities.size() + " Shows Added");
	}

	private ShowEntity getShow(LocalDate showDate, LocalTime showTime, float multiplier, TheaterEntity theaterEntity, MovieEntity movieEntity) {

		ShowEntity showEntity =
				ShowEntity.builder()
						.showDate(showDate)
						.showTime(showTime)
						.rateMultiplier(multiplier)
						.theater(theaterEntity)
						.movie(movieEntity)
						.build();

		theaterEntity.getShows().add(showEntity);
		movieEntity.getShows().add(showEntity);
		showEntity.setSeats(generateShowSeats(theaterEntity.getSeats(), showEntity));

		for (ShowSeatsEntity seatsEntity : showEntity.getSeats()) {
			seatsEntity.setShow(showEntity);
		}

		return showEntity;

	}

	private List<ShowSeatsEntity> generateShowSeats(List<TheaterSeatsEntity> theaterSeatsEntities, ShowEntity showEntity) {

		List<ShowSeatsEntity> showSeatsEntities = new ArrayList<>();

		for (TheaterSeatsEntity theaterSeatsEntity : theaterSeatsEntities) {

			ShowSeatsEntity showSeatsEntity =
					ShowSeatsEntity.builder()
							.seatNumber(theaterSeatsEntity.getSeatNumber())
							.seatType(theaterSeatsEntity.getSeatType())
							.rate((int) (theaterSeatsEntity.getRate() * showEntity.getRateMultiplier()))
							.build();

			showSeatsEntities.add(showSeatsEntity);
		}

		return showSeatsRepository.saveAll(showSeatsEntities);
	}

	private TheaterEntity getTheater(String name, String city, String address) {

		TheaterEntity theaterEntity =
				TheaterEntity.builder()
						.name(name)
						.type(TheaterType.SINGLE_SCREEN)
						.city(city)
						.address(address)
						.build();

		theaterEntity.getSeats().addAll(getTheaterSeats());

		for (TheaterSeatsEntity seatsEntity : theaterEntity.getSeats()) {
			seatsEntity.setTheater(theaterEntity);
		}

		theaterEntity = theaterRepository.save(theaterEntity);

		return theaterEntity;
	}

	private List<TheaterSeatsEntity> getTheaterSeats() {

		List<TheaterSeatsEntity> seats = new ArrayList<>();

		seats.add(getTheaterSeat("1A", SeatType.CLASSIC, 100));
		seats.add(getTheaterSeat("1B", SeatType.CLASSIC, 100));
		seats.add(getTheaterSeat("1C", SeatType.CLASSIC, 100));
		seats.add(getTheaterSeat("1D", SeatType.CLASSIC, 100));
		seats.add(getTheaterSeat("1E", SeatType.CLASSIC, 100));

		seats.add(getTheaterSeat("2A", SeatType.PREMIUM, 120));
		seats.add(getTheaterSeat("2B", SeatType.PREMIUM, 120));
		seats.add(getTheaterSeat("2C", SeatType.PREMIUM, 120));
		seats.add(getTheaterSeat("2D", SeatType.PREMIUM, 120));
		seats.add(getTheaterSeat("2E", SeatType.PREMIUM, 120));

		seats = theaterSeatsRepository.saveAll(seats);

		return seats;
	}

	private TheaterSeatsEntity getTheaterSeat(String seatNumber, SeatType seatType, int rate) {
		return TheaterSeatsEntity.builder().seatNumber(seatNumber).seatType(seatType).rate(rate).build();
	}

	private MovieEntity getMovie(String name, MovieLanguage language, CertificateType certificateType, LocalDate releaseDate) {
		MovieEntity movieEntity =
				MovieEntity.builder()
						.name(name)
						.language(language)
						.certificateType(certificateType)
						.releaseDate(releaseDate)
						.build();

		movieEntity = movieRepository.save(movieEntity);

		return movieEntity;
	}
}