package com.resort.springbootMolvenoLake.controllers.api;

import com.resort.springbootMolvenoLake.models.Facility;
import com.resort.springbootMolvenoLake.repositories.FacilityRepository;
import com.resort.springbootMolvenoLake.services.FacilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController("facility_api_controller")
@RequestMapping("api/facility")
public class FacilityController {

    @Autowired
    private FacilityService facilityService;

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
        public Iterable<Facility> get() {
            return this.facilityService.all();
        }
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
        public Optional<Facility> getSingle(@PathVariable long id) {
            return this.facilityService.read(id);
        }

        @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
  public Facility add(@RequestBody Facility facility) {
            this.facilityService.create(facility);
            return facility;
        }

        @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
        public Facility update(@RequestBody Facility facility) {
            return this.facilityService.update(facility);
        }

        @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
        public boolean delete(@PathVariable long id) {
            if (id >= 0) {
                this.facilityService.delete(id);
                return true;
            } else {
                return false;
            }
        }
}
