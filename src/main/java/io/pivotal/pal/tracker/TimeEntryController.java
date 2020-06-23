package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TimeEntryController {

    private TimeEntryRepository repo;

    public TimeEntryController(TimeEntryRepository repo){
        this.repo = repo;
    }

    @PostMapping("/time-entries")
    public ResponseEntity create(@RequestBody TimeEntry timeEntry) {
        TimeEntry entry = repo.create(timeEntry);
        return new ResponseEntity<>(entry, HttpStatus.CREATED);
    }

    @GetMapping("/time-entries/{id}")
    public ResponseEntity read(@PathVariable("id") long timeEntryId) {
        TimeEntry result = repo.find(timeEntryId);

        if ( result != null ){
            return new ResponseEntity<>(result, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/time-entries/{id}")
    public ResponseEntity delete(@PathVariable("id") Long timeEntryId) {
        if ( timeEntryId != null ) {
            repo.delete(timeEntryId);
        }
        return new ResponseEntity<>(timeEntryId, HttpStatus.NO_CONTENT);
    }


    @PutMapping("/time-entries/{id}")
    public ResponseEntity update(@PathVariable("id") Long timeEntryId, @RequestBody TimeEntry entry) {
        TimeEntry result = repo.update(timeEntryId, entry);
        if ( result != null ){
            return new ResponseEntity<>(result, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/time-entries")
    @PutMapping(produces = {"application/json"})
    public ResponseEntity list() {
        return new ResponseEntity<>(repo.list(), HttpStatus.OK);
    }

}
