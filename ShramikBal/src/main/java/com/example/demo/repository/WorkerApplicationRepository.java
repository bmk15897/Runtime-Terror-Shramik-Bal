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
	
	@Query("select u.contractorRequirementId from WorkerApplication u where u.labourer=?1")
	ArrayList<Integer> findWorkerApplicationIdsByLabourer(Labourer labourer);
}
