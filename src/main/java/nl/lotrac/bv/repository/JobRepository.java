package nl.lotrac.bv.repository;

import nl.lotrac.bv.model.Item;
import nl.lotrac.bv.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobRepository extends JpaRepository <Job, Long >{

Job getJobByJobname (String jobname);

List<Job> findAllByDepartment(String departmentname);

Job getDepartmentByDepartment (String departmentname);




}
