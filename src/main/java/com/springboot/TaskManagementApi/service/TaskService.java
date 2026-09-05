package com.springboot.TaskManagementApi.service;

import com.springboot.TaskManagementApi.entity.Task;
import com.springboot.TaskManagementApi.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public Task saveTask(Task task) {
        if (task.getTitle() == null || task.getTitle().isEmpty()) {
            throw new IllegalArgumentException("العنوان لا يمكن ان بكون فارغ");
        }
        return taskRepository.save(task);
    }

    public void deleteTask(Long id){
        if (!taskRepository.existsById(id)){
            throw new IllegalArgumentException("المهمة غير موجودة...لا يمكننا حذفها");
        }
        taskRepository.deleteById(id);

    }
    public Task updateTask(Long id, Task updatTask){
        Task existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("لم يتم العثور على المهمة لتعديلها"));
        existingTask.setTitle(updatTask.getTitle());
        existingTask.setCompleted(updatTask.isCompleted());
        return taskRepository.save(existingTask);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }


}
