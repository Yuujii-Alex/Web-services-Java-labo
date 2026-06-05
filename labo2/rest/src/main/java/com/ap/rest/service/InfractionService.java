package com.ap.rest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ap.rest.controller.InfractionDTO;
import com.ap.rest.dao.InfractionRepository;
import com.ap.rest.entity.Infraction;

@Service
public class InfractionService {
    private final InfractionRepository repository;

    public InfractionService(InfractionRepository repository) {
        this.repository = repository;
    }

    public void save(InfractionDTO dto) {
        Infraction infraction = assemble(dto);

        if (infraction == null) {
            return;
        }

        repository.save(infraction);
    }

    public InfractionDTO findById(Long id) {
        Optional<Infraction> infraction = repository.findById(id);
        return infraction.map(this::assemble).orElse(null);
    }

    public List<InfractionDTO> findByYear(Integer year) {
        List<Infraction> infraction = repository.findByYear(year);
        return infraction.stream().map(this::assemble).toList();
    }

    private InfractionDTO assemble(Infraction infraction) {
        return new InfractionDTO(infraction.getId(), infraction.getYear(), infraction.getMonth(), infraction.getDate(),
                infraction.getStreet(), infraction.getDrivingDirection(), infraction.getSpeedLimit(),
                infraction.getPassersby(), infraction.getInfractionsSpeed(), infraction.getInfractionsRedLight());
    }

    private Infraction assemble(InfractionDTO dto) {
        return new Infraction(dto.id(), dto.year(), dto.month(), dto.date(), dto.street(), dto.drivingDirection(),
                dto.speedLimit(), dto.passersby(), dto.infractionsSpeed(), dto.infractionsRedLight());
    }
}