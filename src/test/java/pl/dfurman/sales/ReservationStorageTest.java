package pl.dfurman.sales;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.dfurman.sales.reservation.Reservation;
import pl.dfurman.sales.reservation.ReservationRepository;

import java.math.BigDecimal;
import java.util.UUID;

@SpringBootTest
public class ReservationStorageTest {

    @Autowired
    ReservationRepository repository;

    @Test
    void insert() {
        Reservation reservation = new Reservation(
                UUID.randomUUID().toString(),
                BigDecimal.valueOf(10.10),
                "payu/12345");
        repository.save(reservation);

        //Reservation loaded = repository.findById("res-1234abcd").get();
//
        //assert loaded.getId().equals(reservation.getId());
    }

    @Test
    void select() {
        String id = UUID.randomUUID().toString();
        Reservation reservation = new Reservation(
                id,
                BigDecimal.valueOf(10.10),
                "payu/12345"
        );
        repository.save(reservation);

        //Reservation loaded = reservation.findById(id).get();
    }
}
