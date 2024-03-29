package com.example.demo.repository;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.ContractorRequirement;
import com.example.demo.model.Labourer;
import com.example.demo.model.WorkerApplication;

@Repository
public interface WorkerApplicationRepository extends JpaRepository<WorkerApplication, Integer>{
	ArrayList<WorkerApplication> findByContractorRequirementId(ContractorRequirement contractorRequirement);
	WorkerApplication save(WorkerApplication application);
	Optional<WorkerApplication> findByWorkerApplicationId(int workAppId);
	ArrayList<WorkerApplication> findByLabourer(Labourer labourer);
	
	//value = "select contractor_requirement_id from worker_application where labourer_id=?1;", nativeQuery = true
	@Query(value = "select contractor_requirement_id from worker_application where labourer_id=?1", nativeQuery = true)
	ArrayList<Integer> findWorkerApplicationIdsByLabourer(int labourer);
}
