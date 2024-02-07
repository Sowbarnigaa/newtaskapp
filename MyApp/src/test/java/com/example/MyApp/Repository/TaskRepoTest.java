package  com.example.MyApp.Repository;

import com.example.MyApp.MyAppApplication;
import com.example.MyApp.repo.TaskRepo;
import org.springframework.boot.test.autoconfigure.data.elasticsearch.DataElasticsearchTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.example.MyApp.entity.Task;


import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.MyApp.entity.Subtask;
import com.example.MyApp.entity.Task;

import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@DataElasticsearchTest
public class TaskRepoTest{


   @Autowired
   private TaskRepo taskRepo;
   Task task;
   // @BeforeAll
   // public static void init(){
   // 	restTemplate = new RestTemplate();
   // }
   String id_t;
   @BeforeEach
   void setUp()
   {

       List<Subtask> subtaskList = new ArrayList<>();
       Subtask s=new Subtask("sub task1", "sub task desc", "in progress");
       subtaskList.add(s);
       Date specificDate = new Date(1612656000000L);
       Task task = new Task("HyLdgY0Bgwwv5qY8TU45","Task1", "Task desc1", "completed",specificDate,subtaskList);
       taskRepo.save(task);
       id_t=task.getId();
       System.out.println(id_t);
   }

   @AfterEach
   void TearDown()
   {
       task=null;
       if (this.taskRepo != null) {
           this.taskRepo.deleteAll(); // Ensure taskRepo is not null before accessing its methods
       }
   }



   @Test
   void testFindById_found()
   {
       Task t= taskRepo.getTaskById("HyLdgY0Bgwwv5qY8TU45");
       assertThat("HyLdgY0Bgwwv5qY8TU45").isEqualTo(t.getId());
       assertThat("Task1").isEqualTo(t.getName());
       assertThat("Task desc1").isEqualTo(t.getDescription());
       assertThat("completed").isEqualTo(t.getStatus());
       assertThat("sub task1").isEqualTo(t.getSubtasks().get(0).getName());
       assertThat("sub task desc").isEqualTo(t.getSubtasks().get(0).getDescription());
       assertThat("in progress").isEqualTo(t.getSubtasks().get(0).getStatus());
   }

   @Test
   void testFindById_notfound()
   {
       Task t= taskRepo.getTaskById("HyLdgY0Bgwwv5qY8TU46");
       assertThat(t).isNull();
   }



}




