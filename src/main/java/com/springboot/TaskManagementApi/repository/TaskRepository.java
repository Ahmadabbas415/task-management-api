package com.springboot.TaskManagementApi.repository;

import com.springboot.TaskManagementApi.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
