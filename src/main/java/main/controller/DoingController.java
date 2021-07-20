package main.controller;

import main.model.DoingEntity;
import main.model.DoingRepository;
import main.service.DoingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class DoingController {

    private DoingService doingService;

    @Autowired
    public DoingController(DoingService doingService) {
        this.doingService = doingService;
    }

    @Autowired
    private DoingRepository doingRepository;

    @GetMapping("/doings")
    public Collection<DoingEntity> list() {
        return doingService.list();
    }

    @GetMapping("/doings/{id}")
    public ResponseEntity<DoingEntity> getDoing(@PathVariable int id) {
        DoingEntity doing = doingService.getDoing(id);
        if(doing != null) {
            return ResponseEntity.status(HttpStatus.OK).body(doing);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @PostMapping("/doings")
    public int addDoing(DoingEntity doingEntity) {
        return doingService.addDoing(doingEntity);
    }

    @PutMapping("/doings/{id}")
    public ResponseEntity putDoing(@PathVariable int id, DoingEntity doingEntity) {
        if(doingService.putDoing(id, doingEntity)) {
            return ResponseEntity.status(HttpStatus.OK).body(null);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @DeleteMapping("/doings")
    public void clear() {
        doingService.clear();
    }

    @DeleteMapping("/doings/{id}")
    public ResponseEntity deleteDoing(@PathVariable int id) {
        if(doingService.deleteDoing(id)) {
            return ResponseEntity.status(HttpStatus.OK).body(null);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
}
