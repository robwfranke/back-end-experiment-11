package nl.lotrac.bv.service;


import lombok.extern.slf4j.Slf4j;
import nl.lotrac.bv.exceptions.NameExistsException;
import nl.lotrac.bv.exceptions.NameNotFoundException;
import nl.lotrac.bv.model.Job;
//import nl.lotrac.bv.model.Order;
import nl.lotrac.bv.service.UserService;
import nl.lotrac.bv.repository.JobRepository;
import nl.lotrac.bv.utils.ExtractUserName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;




@Slf4j
@Service


public class JobServiceImpl implements JobService {


    @Autowired
    private JobRepository jobRepository;



    @Autowired
    private UserService userService;


    @Autowired
    private ExtractUserName extractUserName;





    @Override
    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }


    @Override
    public String createNewJob(Job job) {
        if (jobRepository.getJobByJobname(job.getJobname()) != null)
            throw new NameExistsException("job exists");
        job.setJobname(job.getJobname());
        job.setDepartment(job.getDepartment());
        Job newJob = jobRepository.save(job);
        return (newJob.getJobname());
    }





    @Override

    public Job getOneJobByID(Long id) {
        Optional<Job> job = jobRepository.findById(id);

        if (job.isEmpty()) {
            throw new NameNotFoundException("job does not exists");
        } else {
            return job.get();
        }
    }

    @Override
    public List<Job> getAllJobsByDepartment(String departmentname) {

        if (jobRepository.findAllByDepartment(departmentname).isEmpty())
            throw new NameNotFoundException("job not present");
        log.debug("emty");

        return jobRepository.findAllByDepartment(departmentname);
    }


}
