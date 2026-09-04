package com.springboot.TaskManagementApi;

import com.springboot.TaskManagementApi.entity.Task;
import com.springboot.TaskManagementApi.repository.TaskRepository;
import com.springboot.TaskManagementApi.service.TaskService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    @Test
    void should_Save_Task_Successfully(){
        // Arrange (تجهيز البيانات)
        Task task = new Task();
        task.setTitle(("تعلم سبرينع"));
        Mockito.when(taskRepository.save(task)).thenReturn(task);
        // Act  (التنفيذ)
        Task savedTask = taskService.saveTask(task);
        // Assert (التأكيد)
        Assertions.assertNotNull(savedTask);
        Assertions.assertEquals("تعلم سبرينع",savedTask.getTitle());

    }
    @Test
    void should_Throw_Exception_When_Title_Is_Empty(){
        Task invalidTask = new Task();
        invalidTask.setTitle("");

        // Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class,()->{
            taskService.saveTask(invalidTask);
        });
    }

    @Test
    void should_Delete_Task_Successfully(){
        Long taskId = 1L;
        Mockito.when(taskRepository.existsById(taskId)).thenReturn(true);

        taskService.deleteTask(taskId);

        Mockito.verify(taskRepository,Mockito.times(1)).deleteById(taskId);
    }
    @Test
    void should_Throw_Exception_When_Deleting_Non_Existing_Task(){
        Long idNotExists = 99L;
        Mockito.when(taskRepository.existsById(idNotExists)).thenReturn(false);

        Assertions.assertThrows(IllegalArgumentException.class,()->{
            taskService.deleteTask(idNotExists);
        });
    }
    @Test
    void should_Update_Task_Successfully(){
        Long taskId = 1L;

        Task existingTask = new Task();
        existingTask.setId(taskId);
        existingTask.setTitle("العنوان القديم");
        existingTask.setCompleted(false);

        Task updatedData = new Task();
        updatedData.setTitle("العنوان الجديد المعدل");
        updatedData.setCompleted(true);

        Mockito.when(taskRepository.findById(taskId)).thenReturn(Optional.of(existingTask));
        Mockito.when(taskRepository.save(existingTask)).thenReturn(existingTask);

        Task result = taskService.updateTask(taskId,updatedData);

        Assertions.assertNotNull(result);
        Assertions.assertEquals("العنوان الجديد المعدل",result.getTitle());
        Assertions.assertTrue(result.isCompleted());
    }
    @Test
    void should_Throw_Exception_When_Updating_Non_Existing_Task(){
        Long idNotExists = 99L;
        Task anyData = new Task();
        Mockito.when(taskRepository.findById(idNotExists)).thenReturn(Optional.empty());

        Assertions.assertThrows(IllegalArgumentException.class,()->{
            taskService.updateTask(idNotExists,anyData);
        });
    }
}

