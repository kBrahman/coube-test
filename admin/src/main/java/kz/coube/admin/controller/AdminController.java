package kz.coube.admin.controller;

import kz.coube.admin.domain.model.Tariff;
import kz.coube.admin.domain.repository.TariffRepository;
import kz.coube.admin.domain.usecase.UpdateTariffUseCase;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/tariff")
public class AdminController {

    private final TariffRepository tariffRepository;
    private final UpdateTariffUseCase updateTariffUseCase;

    public AdminController(TariffRepository tariffRepository, UpdateTariffUseCase updateTariffUseCase) {
        this.tariffRepository = tariffRepository;
        this.updateTariffUseCase = updateTariffUseCase;
    }

    @GetMapping
    public ResponseEntity<Tariff> getTariff() {
        return ResponseEntity.ok(tariffRepository.getLatestTariff());
    }

    @PutMapping
    public ResponseEntity<Void> updateTariff(@RequestBody Tariff tariff) {
        try {
            updateTariffUseCase.updateTariff(tariff);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
