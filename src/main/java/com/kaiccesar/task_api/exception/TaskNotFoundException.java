package com.kaiccesar.task_api.exception;

public class TaskNotFoundException extends RuntimeException{
    public TaskNotFoundException(String msg){
        super(msg);
    }
}
