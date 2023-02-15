package com.sourcemind.seatmapserver.controller;

import com.sourcemind.seatmapserver.dto.EmployeeChangeDTO;
import com.sourcemind.seatmapserver.dto.EmployeeDetailsDTO;
import com.sourcemind.seatmapserver.dto.EmployeeRegistrationDTO;
import com.sourcemind.seatmapserver.facade.EmployeeAPIFacade;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {
    private final EmployeeAPIFacade apiFacade;

    public EmployeeController(EmployeeAPIFacade apiFacade) {
        this.apiFacade = apiFacade;
    }


    @GetMapping("/{uuid}")
    public ResponseEntity<EmployeeDetailsDTO> getEmployeeByUUID(@PathVariable("uuid") UUID uuid) {
        return ResponseEntity.ok(apiFacade.getEmployeeByUUID(uuid));
    }

    @GetMapping()
    public List<EmployeeDetailsDTO> getAllEmployees(@RequestParam(value = "search", required = false) Optional<String> search) {
        if (search.isEmpty()) {
            return apiFacade.getAllEmployees();

        } else
            return apiFacade.getAllEmployeesBySearch(search.get());
    }

    @PostMapping
    public ResponseEntity<EmployeeDetailsDTO> register(@RequestBody EmployeeRegistrationDTO dto) {
        return ResponseEntity.ok(apiFacade.registerEmployeeFromDTO(dto));
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<EmployeeDetailsDTO> update(@PathVariable("uuid") UUID uuid, @RequestBody EmployeeChangeDTO request) {
        return ResponseEntity.ok(apiFacade.updateEmployee(uuid, request));
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<EmployeeDetailsDTO> delete(@PathVariable("uuid") UUID uuid) {
        return ResponseEntity.ok(apiFacade.deleteEmployee(uuid));
    }

}
