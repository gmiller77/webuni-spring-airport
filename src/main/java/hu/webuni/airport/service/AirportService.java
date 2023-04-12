package hu.webuni.airport.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import hu.webuni.airport.model.Airport;
import hu.webuni.airport.model.Flight;
import hu.webuni.airport.repository.AirportRepository;
import hu.webuni.airport.repository.FlightRepository;
import jakarta.transaction.Transactional;

@Service
public class AirportService {
	
	AirportRepository airportRepository;
	FlightRepository flightRepository;

	public AirportService(AirportRepository airportRepository, FlightRepository flightRepository) {
		super();
		this.airportRepository = airportRepository;
		this.flightRepository = flightRepository;
	}

//	@PersistenceContext
//	EntityManager em;
	
	@Transactional
	public Airport save(Airport airport) {
		checkUniqueIata(airport.getIata(), null);
//		em.persist(airport);
		return airportRepository.save(airport);
	}
	
	@Transactional
	public Airport update(Airport airport) {
		checkUniqueIata(airport.getIata(), airport.getId());
		if(airportRepository.existsById(airport.getId()))
			return airportRepository.save(airport);
		else
			throw new NoSuchElementException();
	}
	
	private void checkUniqueIata(String iata, Long id) {
		
		boolean forUpdate = id != null;
//		TypedQuery<Long> query = em.createNamedQuery(forUpdate ? "Airport.countByIataAndIdNotIn" : "Airport.countByIata", Long.class)
//			.setParameter("iata", iata);
//		if(forUpdate)
//			query.setParameter("id", id);
		
//		Long count = query
//			.getSingleResult();
		
		Long count = forUpdate ?
				airportRepository.countByIataAndIdNot(iata, id)
				:airportRepository.countByIata(iata);
		
		if(count > 0)
			throw new NonUniqueIataException(iata);
	}
	
	public List<Airport> findAll(){
//		return em.createQuery("SELECT a FROM Airport a", Airport.class).getResultList();
		return airportRepository.findAll();
	}
	
	public Optional<Airport> findById(long id){
//		return em.find(Airport.class, id);
		return airportRepository.findById(id);
	}
	
	@Transactional
	public void delete(long id) {
//		em.remove(findById(id));
		airportRepository.deleteById(id);
	}
	
	@Transactional
	public void createFlight() {
		Flight flight = new Flight();
		flight.setFlightNumber("asdgasdf");
		flight.setTakeoff(airportRepository.findById(4L).get());
		flight.setLanding(airportRepository.findById(4L).get());
		flight.setTakeoffTime(LocalDateTime.of(2021, 4,10, 10, 0, 0));
		flightRepository.save(flight);
	}
	
}
	
/*
	private Map<Long, Airport> airports = new HashMap<>();

	{
		airports.put(1L, new Airport(1, "Ferihegy", "BUD"));
		airports.put(2L, new Airport(2, "Fiumicino", "ROM"));
		airports.put(3L, new Airport(3, "Heathrow", "LON"));
	}

	public Airport save(Airport airport) {
		checkUniqueIata(airport.getIata());
		airports.put(airport.getId(), airport);
		return airport;
	}

	private void checkUniqueIata(String iata) {
		Optional<Airport> airportWithSameIata = airports.values().stream().filter(a -> a.getIata().equals(iata))
				.findAny();
		if (airportWithSameIata.isPresent())
			throw new NonUniqueIataException(iata);
	}

	public List<Airport> findAll() {
		return new ArrayList<>(airports.values());
	}

	public Airport findById(long id) {
		return airports.get(id);
	}

	public void delete(long id) {
		airports.remove(id);
	}

}
*/
