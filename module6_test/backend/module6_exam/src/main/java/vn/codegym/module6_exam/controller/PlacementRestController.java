package vn.codegym.module6_exam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.codegym.module6_exam.entity.Employee;
import vn.codegym.module6_exam.entity.Placement;
import vn.codegym.module6_exam.service.IPlacementService;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/placement")
public class PlacementRestController {
    @Autowired
    IPlacementService iPlacementService;

    @GetMapping("/list")
    public ResponseEntity<List<Placement>> findAll() {
        List<Placement> placementList = iPlacementService.findAll();
        if (placementList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(placementList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Placement> findById(@PathVariable Integer id) {
        Optional<Placement> placement = iPlacementService.findById(id);
        if (!placement.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(placement.orElse(null), HttpStatus.OK);
    }
}
