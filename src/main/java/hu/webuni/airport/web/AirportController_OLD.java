//package hu.webuni.airport.web;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Optional;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.server.ResponseStatusException;
//
//import hu.webuni.airport.dto.AirportDto;
//import hu.webuni.airport.service.NonUniqueIataException;
//import jakarta.validation.Valid;
//
//@RestController
//@RequestMapping("/api/airports")
//public class AirportController_OLD {
//
//	private Map<Long, AirportDto> airports = new HashMap<>();
//	
//	{
//		airports.put(1L, new AirportDto(1, "Ferihegy", "BUD"));
//		airports.put(2L, new AirportDto(2, "Fiumicino", "ROM"));
//		airports.put(3L, new AirportDto(3, "Heathrow", "LON"));
//	}
//	
//	@GetMapping
//	public List<AirportDto> getAll() {
//		return new ArrayList<>(airports.values());
//	}
//	
//	@GetMapping("/{id}")
//	public AirportDto getById(@PathVariable long id) {
//		AirportDto airportDto = airports.get(id);
//		if (airportDto != null)
//			return airportDto;
//		else
//			throw new ResponseStatusException(HttpStatus.NOT_FOUND); 
//	}
//
//	/* korábbi megoldás - week 2-3
//	@GetMapping("/{id}")
//	public ResponseEntity<AirportDto> getById(@PathVariable long id) {
//		AirportDto airportDto = airports.get(id);
//		if (airportDto != null)
//			return ResponseEntity.ok(airportDto);
//		else
//			return ResponseEntity.notFound().build(); 
//	}
//	*/
//	
//	@PostMapping
////	public AirportDto createAirport(@RequestBody AirportDto airportDto) {
//	public AirportDto createAirport(@RequestBody @Valid AirportDto airportDto) {
//		checkUniqueIata(airportDto.getIata());
//		airports.put(airportDto.getId(), airportDto);
//		return airportDto;
//	}
//
//	@PutMapping("/{id}")
//	public ResponseEntity<AirportDto> modifyAirport(@PathVariable long id, @RequestBody AirportDto airportDto) {
//		checkUniqueIata(airportDto.getIata());
//		if (!airports.containsKey(id)) {
//			return ResponseEntity.notFound().build();
//		}
//		
//		airportDto.setId(id);
//		airports.put(id, airportDto);		
//		return ResponseEntity.ok(airportDto);
//	}
//	
//	private void checkUniqueIata(String iata) {
//		Optional<AirportDto> airportWithSameIata = airports.values()
//				.stream()
//				.filter(a -> a.getIata().equals(iata))
//				.findAny();
//		if (airportWithSameIata.isPresent())
//			throw new NonUniqueIataException(iata);
//	}
//
//	@DeleteMapping("/{id}")
//	public void deleteAirport(@PathVariable long id) {
//		airports.remove(id);
//	}
//	
//}
