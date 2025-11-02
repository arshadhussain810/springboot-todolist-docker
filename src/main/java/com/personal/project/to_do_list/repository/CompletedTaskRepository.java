package com.personal.project.to_do_list.repository;

import com.personal.project.to_do_list.entity.CompletedTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface CompletedTaskRepository extends JpaRepository<CompletedTask, Integer> {

    @Query("SELECT count(c) From CompletedTask c")
    int getTotalCompletedTask();

    @Query("SELECT t from CompletedTask t where t.startDate BETWEEN :fromDate and :toDate")
    List<CompletedTask> searchByDate(@Param("fromDate") LocalDateTime fromDate, @Param("toDate") LocalDateTime toDate);
}
