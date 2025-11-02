package com.personal.project.to_do_list.repository;

import com.personal.project.to_do_list.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TaskRepository extends JpaRepository<Task, Integer> {

    @Query("SELECT count(t) From Task t")
    int getTotalActiveTask();

}
