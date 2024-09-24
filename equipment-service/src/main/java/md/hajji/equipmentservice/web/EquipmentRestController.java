package md.hajji.equipmentservice.web;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import md.hajji.equipmentservice.dtos.*;
import md.hajji.equipmentservice.services.CategoryService;
import md.hajji.equipmentservice.services.EquipmentService;
import md.hajji.equipmentservice.services.ReservationService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/equipments")
@CrossOrigin("*")
@RequiredArgsConstructor
public class EquipmentRestController {

    private final EquipmentService equipmentService;
    private final CategoryService categoryService;
    private final ReservationService reservationService;


    /**---------------------- EQUIPMENT ENDPOINTS ----------------------------------**/

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<EquipmentResponseDTO> create(
            @ModelAttribute final EquipmentRequestDTO equipmentRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(equipmentService.addEquipment(equipmentRequestDTO));
    }


    @GetMapping("{id}")
    public ResponseEntity<EquipmentResponseDTO> getEquipment(@PathVariable final String id) {
        return ResponseEntity.ok(equipmentService.getEquipment(id));
    }

    @GetMapping
    public ResponseEntity<List<EquipmentResponseDTO>> getAllEquipments() {
        return ResponseEntity.ok(equipmentService.getAllEquipments());
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<EquipmentResponseDTO> putEquipment(
            @PathVariable final String id,
            @RequestBody final EquipmentUpdateDTO equipmentUpdateDTO){

        return ResponseEntity.ok(equipmentService.updateEquipment(id, equipmentUpdateDTO));
    }

    @PutMapping(value = "{id}/set-image", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<EquipmentResponseDTO> setEquipmentImage(
            @PathVariable final String id,
            @ModelAttribute final EquipmentImageDTO equipmentImageDTO){
        return ResponseEntity.ok(equipmentService.setEquipmentImage(id, equipmentImageDTO));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteEquipment(@PathVariable final String id) {
        equipmentService.deleteEquipment(id);
        return ResponseEntity.noContent().build();
    }

//    @GetMapping(value = "{id}/qrcode", produces = MediaType.IMAGE_PNG_VALUE)
//    public ResponseEntity<byte[]> getQRCode(@PathVariable final String id) {
//        return ResponseEntity.status(HttpStatus.OK)
//                .contentType(MediaType.IMAGE_PNG)
//                .body(equipmentService.getQrCodeOfEquipment(id));
//    }

    @GetMapping("{name}/exists")
    public ResponseEntity<Boolean> existsEquipment(@PathVariable final String name) {
        return ResponseEntity.ok(equipmentService.existsByName(name));
    }

    @GetMapping("{name}/exists/{exclude}")
    public ResponseEntity<Boolean> existsByNameExcluding(@PathVariable final String name, @PathVariable final String exclude) {
        return ResponseEntity.ok(equipmentService.existsByNameExclude(name, exclude));
    }

    @GetMapping("{id}/reservation-calendar")
    public ResponseEntity<Map<LocalDate, List<ReservationResponseDTO>>> getEquipmentReservationCalendar(
            @PathVariable final String id) {
        return ResponseEntity.ok(reservationService.getReservationWeekCalendarOfEquipment(id));
    }


    /**--------------------------- CATEGORY ENDPOINTS ---------------------------------**/

    @PostMapping("/categories")
    public ResponseEntity<CategoryResponseDTO> createCategory(
            @RequestBody final String categoryName) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(categoryService.addCategory(categoryName));
    }

    @GetMapping("/categories")
    public ResponseEntity<List<CategoryResponseDTO>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<CategoryResponseDTO> getCategory(@PathVariable final Long id) {
        return ResponseEntity.ok(categoryService.getCategory(id));
    }

    @PutMapping("/categories/{id}")
    public ResponseEntity<CategoryResponseDTO> renameCategory(
            @PathVariable final Long id,
            @RequestBody final String categoryName) {

        return ResponseEntity.ok(categoryService.updateCategory(id, categoryName));
    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity<CategoryResponseDTO> deleteCategory(@PathVariable final Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/categories/{name}/exists")
    public ResponseEntity<Boolean> existsCategory(@PathVariable final String name) {
        return ResponseEntity.ok(categoryService.existsCategoryByName(name));
    }

    @GetMapping("/categories/{name}/exists/{exclude}")
    public ResponseEntity<Boolean> existsCategory(@PathVariable final String name, @PathVariable final Long exclude) {
        return ResponseEntity.ok(categoryService.existsCategoryByNameExcluding(name, exclude));
    }

    /*---------------------- RESERVATIONS-----------------------------------**/

    @PostMapping("/reservations")
    public ResponseEntity<ReservationResponseDTO> createReservation(
            @RequestBody final ReservationRequestDTO reservationRequestDTO) {

        System.out.println("request received ");
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(reservationService.createReservation(reservationRequestDTO));
    }

    @GetMapping("/reservations")
    public ResponseEntity<List<ReservationResponseDTO>> getReservations() {
        return ResponseEntity.ok(reservationService.getAllReservations());
    }

    @GetMapping("/reservations/{id}")
    public ResponseEntity<ReservationResponseDTO> getReservation(@PathVariable final String  id) {
        return ResponseEntity.ok(reservationService.getReservation(id));
    }
}
