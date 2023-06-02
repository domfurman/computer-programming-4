package pl.dfurman.sales;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.dfurman.sales.reservation.Reservation;
import pl.dfurman.sales.reservation.ReservationRepository;

import java.math.BigDecimal;

@SpringBootTest
public class ReservationStorageTest {

    @Autowired
    ReservationRepository repository;

    @Test
    void insert() {
        Reservation reservation = new Reservation(
                "res-1234abcd",
                BigDecimal.valueOf(10.10),
                "payu/12345");
        repository.save(reservation);

        Reservation loaded = repository.findById("res-1234abcd").get();

        assert loaded.getId().equals(reservation.getId());
    }
}
