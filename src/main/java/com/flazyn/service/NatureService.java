package com.flazyn.service;

import com.flazyn.dto.NatureDTO;
import com.flazyn.entities.Nature;
import com.flazyn.map.NatureMapper;
import com.flazyn.repository.NatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
public class NatureService {

    private final NatureRepository natureRepository;

    @Autowired
    public NatureService(NatureRepository natureRepository) {
        this.natureRepository = natureRepository;
    }

    public Set<NatureDTO> getNatures() {
        return NatureMapper.entityToDTO(natureRepository.findAll());
    }

    public Set<Nature> findByIds(Collection<NatureDTO> natures) {
        if (isNull(natures)) {
            return Collections.EMPTY_SET;
        }

        return natures.stream()
                .map(NatureDTO::getId)
                .map(natureRepository::getOne)
                .collect(Collectors.toSet());

    }

}
