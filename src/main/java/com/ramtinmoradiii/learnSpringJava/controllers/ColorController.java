package com.ramtinmoradiii.learnSpringJava.controllers;

import com.ramtinmoradiii.learnSpringJava.common.ApiResponse;
import com.ramtinmoradiii.learnSpringJava.dto.ColorDTO;
import com.ramtinmoradiii.learnSpringJava.services.ColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/color")
public class ColorController {

    private final ColorService service;

    @Autowired
    public ColorController(ColorService service) {
        this.service = service;
    }

    @GetMapping("")
    public ApiResponse<List<ColorDTO>> getAll() {
        return ApiResponse.success(service.getAll());
    }

    @GetMapping("/{id}")
    public ApiResponse<ColorDTO> getById(@PathVariable Long id) {
        return ApiResponse.success(service.getById(id));
    }

    @PostMapping("")
    public ApiResponse<ColorDTO> create(@RequestBody ColorDTO color) throws Exception {
        return ApiResponse.success(service.add(color));
    }

    @PutMapping("")
    public ApiResponse<ColorDTO> update(@RequestBody ColorDTO color) throws Exception {
        return ApiResponse.success(service.update(color));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ApiResponse.success();
    }
}
