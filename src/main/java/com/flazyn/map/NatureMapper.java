package com.flazyn.map;

import com.flazyn.dto.NatureDTO;
import com.flazyn.entities.Nature;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class NatureMapper {

    public static NatureDTO entityToDTO(Nature nature) {
        return new NatureDTO(nature.getId(), nature.getDescription());
    }

    public static Set<NatureDTO> entityToDTO(Collection<Nature> nature) {
        return nature.stream().map(NatureMapper::entityToDTO).collect(Collectors.toSet());
    }
}
