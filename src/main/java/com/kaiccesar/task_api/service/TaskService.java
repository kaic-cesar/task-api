package com.kaiccesar.task_api.service;

import com.kaiccesar.task_api.dto.TaskRequestDTO;
import com.kaiccesar.task_api.dto.TaskResponseDTO;
import com.kaiccesar.task_api.exception.TaskNotFoundException;
import com.kaiccesar.task_api.model.Task;
import com.kaiccesar.task_api.model.TaskStatus;
import com.kaiccesar.task_api.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    TaskRepository repository;

    public List<Task> allTasks(){
        return repository.findAll();
    }

//    public Task taskById(Long id){
//        return tasks.stream()
//                .filter(task -> task.getId().equals(id))
//                .findFirst()
//                .orElseThrow(() -> new TaskNotFoundException("Get Error: Task not found"));
//    }
//
//    public List<Task> getTasks(@RequestParam Boolean completed){
//
//        return tasks.stream()
//                .filter(task -> task.getCompleted() == TaskStatus.COMPLETED)
//                .toList();
//    }

    public TaskResponseDTO create(TaskRequestDTO taskDto){
        Task task = new Task();

        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setCompleted(TaskStatus.PENDING);
        task.setCreateAt(LocalDate.now());

        Task savedTask = repository.save(task);

        return new TaskResponseDTO(
                savedTask.getId(),
                savedTask.getTitle(),
                savedTask.getDescription(),
                savedTask.getCompleted(),
                savedTask.getCreateAt()
        );
    }

//    public Task update(Long id, TaskRequestDTO newTask){
//        for(Task task : tasks){
//            if(task.getId().equals(id)){
//                task.setTitle(newTask.getTitle());
//                task.setDescription(newTask.getDescription());
//            }
//            return task;
//        }
//
//        return null;
//    }
//
//    public void delete(Long id){
//        boolean response = tasks.removeIf(task -> task.getId().equals(id));
//
//        if(!response){
//            throw new TaskNotFoundException("Delete Error: Task not found");
//        }
//
//    }
//
    public void completedTask(Long id){
        Task task = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada!"));

        task.setCompleted(TaskStatus.COMPLETED);
        repository.save(task);
    }

}
