package pl.dfurman.sales.reservation;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.dfurman.sales.reservation.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, String> {
}
