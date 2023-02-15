package com.sourcemind.seatmapserver.facade;

import com.sourcemind.seatmapserver.dto.EmployeeChangeDTO;
import com.sourcemind.seatmapserver.dto.EmployeeDetailsDTO;
import com.sourcemind.seatmapserver.dto.EmployeeRegistrationDTO;
import com.sourcemind.seatmapserver.dto.EmployeeSeatAllocationDTO;
import com.sourcemind.seatmapserver.exception.EmployeeAlreadyExistsException;
import com.sourcemind.seatmapserver.exception.EmployeeEmailNotFoundException;
import com.sourcemind.seatmapserver.exception.InvalidEmailException;
import com.sourcemind.seatmapserver.mapper.EmployeeMapper;
import com.sourcemind.seatmapserver.model.Employee;
import com.sourcemind.seatmapserver.service.EmailUtilService;
import com.sourcemind.seatmapserver.service.EmployeeService;
import com.sourcemind.seatmapserver.service.PositionService;
import com.sourcemind.seatmapserver.service.SeatService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EmployeeAPIFacade {

    private final EmployeeService employeeService;
    private final EmployeeMapper employeeMapper;
    private final SeatService seatService;
    private final PositionService positionService;

    public EmployeeAPIFacade(EmployeeService employeeService, EmployeeMapper employeeMapper, SeatService seatService, PositionService positionService) {
        this.employeeService = employeeService;
        this.employeeMapper = employeeMapper;
        this.seatService = seatService;
        this.positionService = positionService;
    }

    @Transactional
    public EmployeeDetailsDTO registerEmployeeFromDTO(EmployeeRegistrationDTO dto) {
        Employee employee = employeeMapper.mapEmployeeRegistrationDTOToEmployee(dto);
        try {
            employeeService.findEmployeeByEmail(dto.getEmail());
            throw new EmployeeAlreadyExistsException(dto.getEmail());
        } catch (EmployeeEmailNotFoundException e) {
            if (!EmailUtilService.isValidEmail(dto.getEmail()))
                throw new InvalidEmailException(dto.getEmail());
            employee = employeeService.create(employee);
            EmployeeSeatAllocationDTO seatAllocationDTO = new EmployeeSeatAllocationDTO();
            seatAllocationDTO.setEmployeeUUID(employee.getEmployeeUuid());
            seatAllocationDTO.setFloorUUID(dto.getFloorUUID());
            seatAllocationDTO.setSeatNumber(dto.getSeatNumber());
            seatService.saveSeatFromAllocationDTO(seatAllocationDTO);
            return employeeMapper.mapEmployeeToDetailsDTO(employee);
        }
    }

    public EmployeeDetailsDTO updateEmployee(UUID uuid, EmployeeChangeDTO dto) {
        Employee employeeByUUId = employeeService.findEmployeeByUUId(uuid);
        employeeByUUId.setFirstname(dto.getFirstname());
        employeeByUUId.setLastname(dto.getLastname());
        employeeByUUId.setEmail(dto.getEmail());
        if (dto.getManagerUUId() == null) {
            employeeByUUId.setManager(null);
        } else employeeByUUId.setManager(employeeService.findEmployeeByUUId(dto.getManagerUUId()));
        employeeByUUId.setPosition(positionService.findByPositionByUUID(dto.getPositionUUId()));
        return employeeMapper.mapEmployeeToDetailsDTO(employeeByUUId);
    }

    public EmployeeDetailsDTO deleteEmployee(UUID uuid) {
        return employeeService.deleteEmployeeByUUID(uuid);
    }

    public EmployeeDetailsDTO getEmployeeByUUID(UUID uuid) {
        return employeeMapper.mapEmployeeToDetailsDTO(employeeService.findEmployeeByUUId(uuid));
    }

    public List<EmployeeDetailsDTO> getAllEmployees() {
        return employeeService.findAllEmployees().stream().map(employeeMapper::mapEmployeeToDetailsDTO).collect(Collectors.toList());
    }

    public List<EmployeeDetailsDTO> getAllEmployeesBySearch(String search) {
        if (search.length() < 3) {
            return new ArrayList<>();
        } else {
            return employeeService.findEmployeeBySearch(search.toLowerCase()).stream().map(employeeMapper::mapEmployeeToDetailsDTO).collect(Collectors.toList());
        }
    }
}
