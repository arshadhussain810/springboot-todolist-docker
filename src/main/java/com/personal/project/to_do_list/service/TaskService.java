package com.personal.project.to_do_list.service;

import com.personal.project.to_do_list.entity.CompletedTask;
import com.personal.project.to_do_list.entity.Task;

import java.time.LocalDate;
import java.util.List;

public interface TaskService {

    void save(Task task);
    List<Task> findAll();
    void delete(int id);
    Task findById(int id);

    void saveCompleteTask(int id);
    int getActiveTaskCount();
    int getTotalCompletedTaskCount();

    List<CompletedTask> searchTaskByDate(LocalDate fromDate, LocalDate toDate);

    void deleteAll();

}
