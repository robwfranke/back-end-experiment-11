package nl.lotrac.bv.controller;

import nl.lotrac.bv.model.Job;
import nl.lotrac.bv.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/jobs")


public class JobController {

    @Autowired
    private JobService jobService;





    //********************************************************************************
    @PostMapping(value = "/create")
    public ResponseEntity<Job> createNewJob(@RequestBody Job job) {

        jobService.createNewJob(job);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{create}")
                .buildAndExpand(job.getJobname()).toUri();

        return ResponseEntity.created(location).body(job);

    }
    //********************************************************************************



    //********************************************************************************
    @GetMapping(value = "")
    public ResponseEntity<Object>getAllJobs(){
        return ResponseEntity.ok().body(jobService.getAllJobs());
    }
//********************************************************************************

@GetMapping(value="/{id}")
    public ResponseEntity<Job> getOneJobByID(@PathVariable("id") Long id){
        return new ResponseEntity<>(jobService.getOneJobByID(id),HttpStatus.OK);

}

    //********************************************************************************
    @GetMapping(value = "/jobsByDepartment/{departmentname}")
    public ResponseEntity<Object> getAllJobsByJob(@PathVariable("departmentname") String departmentname) {
        List<Job> jobs = jobService.getAllJobsByDepartment(departmentname);
        return ResponseEntity.ok().body(jobs);
    }
//********************************************************************************





}