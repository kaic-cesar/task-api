package com.kaiccesar.task_api.service;

import com.kaiccesar.task_api.dto.TaskRequestDTO;
import com.kaiccesar.task_api.dto.TaskResponseDTO;

import com.kaiccesar.task_api.model.Task;
import com.kaiccesar.task_api.model.TaskStatus;
import com.kaiccesar.task_api.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

import org.springframework.stereotype.Service;

import java.time.LocalDate;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    TaskRepository repository;

    public List<Task> allTasks(){
        return repository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    public Task taskById(Long id){
        Task task = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task não encontrada"));

        return task;
    }


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

    public Task update(Long id, TaskRequestDTO newTask){
        Task task = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task não encontrada"));

        task.setTitle(newTask.getTitle());
        task.setDescription(newTask.getDescription());

        Task updatedTask = repository.save(task);

        return updatedTask;
    }

    public void delete(Long id){
        Task task = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task não encontrada"));

        repository.delete(task);
    }

    public TaskResponseDTO completedTask(Long id){
        Task task = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada!"));


        if(task.getCompleted().equals(TaskStatus.COMPLETED)){
            task.setCompleted(TaskStatus.PENDING);
            repository.save(task);
        } else {
            task.setCompleted(TaskStatus.COMPLETED);
            repository.save(task);
        }

        return new TaskResponseDTO(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getCompleted(),
                task.getCreateAt()
        );
    }

}
