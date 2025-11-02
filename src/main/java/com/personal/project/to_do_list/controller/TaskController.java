package com.personal.project.to_do_list.controller;

import com.personal.project.to_do_list.entity.CompletedTask;
import com.personal.project.to_do_list.entity.Task;
import com.personal.project.to_do_list.service.TaskService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class TaskController {

    private TaskService taskService;

    public TaskController (TaskService taskService){

        this.taskService = taskService;

    }

    @GetMapping("/home")
    public String homePage(Model model){

        List<Task> activeTask = taskService.findAll();
        int activeTasksCount = taskService.getActiveTaskCount();
        int completedTaskCount = taskService.getTotalCompletedTaskCount();

        model.addAttribute("activeTasksCount", activeTasksCount);
        model.addAttribute("task", new Task());
        model.addAttribute("activeTask", activeTask);
        model.addAttribute("totalTask", completedTaskCount);

        return "home-page";
    }

    @PostMapping("/saveTask")
    public String saveTask(@ModelAttribute("task") Task task){

        taskService.save(task);
        return "redirect:/home";
    }

    @GetMapping("/deleteActiveTask")
    public String deleteActive(@RequestParam("id") int id){
        taskService.delete(id);
        return "redirect:/home";
    }

    @GetMapping("/completeActiveTask")
    public String finishTask(@RequestParam("id") int id){

        taskService.saveCompleteTask(id);
        return "redirect:/home";
    }

    @GetMapping("/showSearchPage")
    public String searchPage(){
        return "search-page";
    }

    @GetMapping("/search")
    public String searchByDate(@RequestParam("fromDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
                                @RequestParam("toDate")  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDote, Model model){


        List<CompletedTask> completedTaskList =  taskService.searchTaskByDate(fromDate, toDote);
        /*System.out.println(completedTaskList);*/

        model.addAttribute("completedTaskList", completedTaskList);
        return "search-page";
    }

    @GetMapping("/deleteHistory")
    public String deleteHistory(){

        taskService.deleteAll();

        return "redirect:/home";
    }

}
