package com.ramtinmoradiii.learnSpringJava.services;

import com.ramtinmoradiii.learnSpringJava.dto.ColorDTO;
import com.ramtinmoradiii.learnSpringJava.entities.Color;
import com.ramtinmoradiii.learnSpringJava.exceptions.ResourceNotFoundException;
import com.ramtinmoradiii.learnSpringJava.repositories.ColorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ColorService {
    private final ColorRepository repository;
    private final ModelMapper mapper;

    @Autowired
    public ColorService(ColorRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public ColorDTO add(ColorDTO dto) throws Exception {
        modelValidation(dto);
        Color color = mapper.map(dto, Color.class);
        color.setId(null);
        return mapper.map(repository.save(color), ColorDTO.class);
    }

    public ColorDTO update(ColorDTO dto) throws Exception {
        if (dto.getId() == null) throw new Exception("لطفا آیدی رنگ را وارد کنید.");
        modelValidation(dto);
        return mapper.map(repository.save(mapper.map(dto, Color.class)), ColorDTO.class);
    }

    public List<ColorDTO> getAll() {
        return repository.findAll().stream()
                .map(entity -> mapper.map(entity, ColorDTO.class))
                .collect(Collectors.toList());
    }

    public ColorDTO getById(Long id) {
        return repository.findById(id)
                .map(entity -> mapper.map(entity, ColorDTO.class))
                .orElseThrow(() -> new ResourceNotFoundException("رنگ مورد نظر یافت نشد."));
    }

    public void deleteById(Long id) {
        if (!repository.existsById(id))
            throw new ResourceNotFoundException("رنگ مورد نظر یافت نشد.");
        repository.deleteById(id);
    }

    public static void modelValidation(ColorDTO color) throws Exception {
        if (color.getName() == null || color.getName().isEmpty())
            throw new Exception("لطفا اسم رنگ را وارد کنید.");
        if (color.getHexValue() == null || color.getHexValue().isEmpty())
            throw new Exception("لطفا hexValue را وارد کنید.");
    }
}
