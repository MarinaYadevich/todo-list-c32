package com.appToDoList.repository;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * This repository that stores user tasks
 */
public class TaskRepository {
    private final Map<String, Set<String>> taskList;

    public TaskRepository() {
        taskList = new HashMap<>();
    }

    public Map<String, Set<String>> getTaskList() {
        return taskList;
    }

    public Set<String> getTasksByUsername(String username) {
        Set<String> tasks = taskList.get(username);
        if (tasks == null) {
            tasks = new HashSet<>();
        }
        return tasks;
    }
}

/*
 * Временно используется структура Map<String, Set<String>>,
 * где ключ — имя пользователя, а значение — множество его задач.
 * TODO: нужно реализовать кнопку "Редактировать".
*/
