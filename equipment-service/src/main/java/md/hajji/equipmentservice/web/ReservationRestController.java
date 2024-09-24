package md.hajji.equipmentservice.web;


import lombok.RequiredArgsConstructor;
import md.hajji.equipmentservice.dtos.ReservationRequestDTO;
import md.hajji.equipmentservice.dtos.ReservationResponseDTO;
import md.hajji.equipmentservice.enums.ReservationStatus;
import md.hajji.equipmentservice.services.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservations")
@CrossOrigin("*")
@RequiredArgsConstructor
public class ReservationRestController {

    private final ReservationService reservationService;

//    @PostMapping
//    public ResponseEntity<ReservationResponseDTO> createReservation(
//            @RequestBody final ReservationRequestDTO reservationRequestDTO) {
//
//        System.out.println("request received ");
//        return ResponseEntity.status(HttpStatus.CREATED)
//                .body(reservationService.createReservation(reservationRequestDTO));
//    }
//
//    @GetMapping
//    public ResponseEntity<List<ReservationResponseDTO>> getReservations() {
//        return ResponseEntity.ok(reservationService.getAllReservations());
//    }
//
//    @GetMapping("{id}")
//    public ResponseEntity<ReservationResponseDTO> getReservation(@PathVariable final String  id) {
//        return ResponseEntity.ok(reservationService.getReservation(id));
//    }
//
//
//
//    @PutMapping("{id}/accept")
//    public ResponseEntity<Integer> acceptReservation(@PathVariable final String id) {
//        return ResponseEntity.ok(
//                reservationService.updateReservationStatus(id, ReservationStatus.ACCEPTED)
//        );
//    }
//
//    @PutMapping("{id}/refuse")
//    public ResponseEntity<Integer> refuseReservation(@PathVariable final String id) {
//        return ResponseEntity.ok(
//                reservationService.updateReservationStatus(id, ReservationStatus.REFUSED)
//        );
//    }
//
//    @PutMapping("{id}/expire")
//    public ResponseEntity<Integer> expireReservation(@PathVariable final String id) {
//        return ResponseEntity.ok(
//                reservationService.updateReservationStatus(id, ReservationStatus.EXPIRED)
//        );
//    }
//
//    @PutMapping("{id}/request-for-cancel")
//    public ResponseEntity<Integer> requestForCancelReservation(@PathVariable final String id) {
//        return ResponseEntity.ok(
//                reservationService.updateReservationStatus(id, ReservationStatus.CANCEL_REQUESTED)
//        );
//    }
//
//    @PutMapping("{id}/cancel")
//    public ResponseEntity<Integer> cancelReservation(@PathVariable final String id) {
//        return ResponseEntity.ok(
//                reservationService.updateReservationStatus(id, ReservationStatus.CANCELLED)
//        );
//    }
//
//    @PutMapping("{id}/start")
//    public ResponseEntity<Integer> startReservation(@PathVariable final String id) {
//        return ResponseEntity.ok(
//                reservationService.updateReservationStatus(id, ReservationStatus.CHECKED_OUT)
//        );
//    }
//
//    @PutMapping("{id}/complete")
//    public ResponseEntity<Integer> completeReservation(@PathVariable final String id) {
//        return ResponseEntity.ok(
//                reservationService.updateReservationStatus(id, ReservationStatus.COMPLETED)
//        );
//    }





}
