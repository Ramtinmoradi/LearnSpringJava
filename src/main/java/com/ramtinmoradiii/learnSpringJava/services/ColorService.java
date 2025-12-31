package com.ramtinmoradiii.learnSpringJava.services;

import com.ramtinmoradiii.learnSpringJava.entities.Color;
import com.ramtinmoradiii.learnSpringJava.exceptions.ResourceNotFoundException;
import com.ramtinmoradiii.learnSpringJava.repositories.ColorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ColorService {
    private final ColorRepository repository;

    @Autowired
    public ColorService(ColorRepository repository) {
        this.repository = repository;
    }

    public List<Color> getAll() {
        return repository.findAll();
    }

    public Color getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("رنگ مورد نظر یافت نشد."));
    }

    public Color create(Color color) throws Exception {
        modelValidation(color);
        return repository.save(color);
    }

    public Color update(Color color) throws Exception {
        if (color.getId() == null) throw new Exception("لطفا آیدی رنگ را وارد کنید.");
        modelValidation(color);
        return repository.save(color);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public static void modelValidation(Color color) throws Exception {
        if (color.getName() == null || color.getName().isEmpty()) throw new Exception("لطفا اسم رنگ را وارد کنید.");
        if (color.getHexValue() == null || color.getHexValue().isEmpty())
            throw new Exception("لطفا hexValue را وارد کنید.");
    }
}
