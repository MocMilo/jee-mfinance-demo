package com.infoshare.web.adminpanel.agentservice;

import java.util.List;

public interface ITaskService {

    List<Task> getAllTasks();

    void AddTask(Task task);
    List<String> getAllAvaliableTaskTypeNames();

    Task getTaskbyId(long id);

    void updateTask(Task task);
}
