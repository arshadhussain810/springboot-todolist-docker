package com.personal.project.to_do_list.service;

import com.personal.project.to_do_list.entity.CompletedTask;
import com.personal.project.to_do_list.entity.Task;
import com.personal.project.to_do_list.repository.CompletedTaskRepository;
import com.personal.project.to_do_list.repository.TaskRepository;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService{

    private TaskRepository taskRepository;
    private CompletedTaskRepository completedTaskRepository;

    public TaskServiceImpl(TaskRepository taskRepository, CompletedTaskRepository completedTaskRepository){

        this.taskRepository = taskRepository;
        this.completedTaskRepository = completedTaskRepository;
    }

    @Override
    public void save(Task task) {
        task.setStartDateTime(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES));
        taskRepository.save(task);
    }

    @Override
    public List<Task> findAll() {

        return taskRepository.findAll();

    }

    @Override
    public void delete(int id) {
        taskRepository.deleteById(id);
    }

    @Override
    public Task findById(int id) {
        Optional<Task> result = taskRepository.findById(id);

        Task task = null;
        if(result.isPresent()){
            task = result.get();
        }else{
            throw new RuntimeException("not found");
        }
        return task;

    }

    @Override
    public void saveCompleteTask(int id) {

        Task tempTask = findById(id);
        CompletedTask completedTask = new CompletedTask();

        completedTask.setTask(tempTask.getToDo());
        completedTask.setStartDate(tempTask.getStartDateTime());
        completedTask.setEndDate(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES));

        completedTaskRepository.save(completedTask);
        taskRepository.deleteById(id);

    }

    @Override
    public int getActiveTaskCount() {

        return taskRepository.getTotalActiveTask();
    }

    @Override
    public int getTotalCompletedTaskCount() {
        return completedTaskRepository.getTotalCompletedTask();
    }

    @Override
    public List<CompletedTask> searchTaskByDate(LocalDate fromDate, LocalDate toDate) {

        LocalDateTime fromDateTime = fromDate.atStartOfDay();
        LocalDateTime toDateTime = toDate.atTime(23,59, 59);
        return completedTaskRepository.searchByDate(fromDateTime, toDateTime);
    }

    @Override
    public void deleteAll() {
        completedTaskRepository.deleteAll();
    }

}
