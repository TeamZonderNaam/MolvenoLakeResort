// TODO: We switched back and didn't do services

package com.resort.springbootMolvenoLake.services;
//
//import com.resort.springbootMolvenoLake.models.Facility;
//import com.resort.springbootMolvenoLake.repositories.FacilityRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//public class FacilityService {
//
//    @Autowired
//    private FacilityRepository facilityRepository;
//
//    // TODO: GET ALL
//    // public Iterable<Facility> getAllFacilities() {
//    //    return facilityRepository.findAll();
//    // }
//
//        public List<Facility> getAllFacilities() {
//            List<Facility> facility = new ArrayList<>();
//            facilityRepository.findAll();
//            // facility.addAll(facilityRepository.findByFacilityId(id));
//            return facility;
//        }
//
//    public Facility getFacility(long id) {
//        return facilityRepository.findById(id).orElse(null);
//    }
//
//    public void addFacility(Facility facility) {
//        facilityRepository.save(facility);
//    }
//
//    public void updateFacility(Facility facility) {
//        facilityRepository.save(facility);
//    }
//
//    public void deleteFacility(long id) {
//        facilityRepository.deleteById(id);
//    }
//}

import com.resort.springbootMolvenoLake.models.Facility;
import com.resort.springbootMolvenoLake.repositories.FacilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FacilityService {
    @Autowired
    private FacilityRepository repository;

    public long create(Facility facility) {
        Facility created = repository.save(facility);
        return created.getId();
    }

    public List<Facility> all() {
        Iterable<Facility> source = this.repository.findAll();
        List<Facility> target = new ArrayList<>();
        source.forEach(target::add);
        return target;
    }

    public Optional<Facility> read(final long id) {
        Optional<Facility> facility = repository.findById(id);
        return facility;
    }

    public Facility update(Facility facility) {
        return this.repository.save(facility);
    }

    public void delete(final long id) {
        this.repository.deleteById(id);
    }
}
