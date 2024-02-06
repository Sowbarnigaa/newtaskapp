//package  com.example.MyApp.Repository;
//
//import com.example.MyApp.MyAppApplication;
//import com.example.MyApp.repo.TaskRepo;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
//
//import com.example.MyApp.entity.Task;
//
//
//import org.junit.FixMethodOrder;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.junit.runners.MethodSorters;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.example.MyApp.entity.Subtask;
//import com.example.MyApp.entity.Task;
//
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//
//
//public class TaskRepoTest{
//
//
//   @Autowired
//   private TaskRepo taskRepo;
//   Task task;
//   // @BeforeAll
//   // public static void init(){
//   // 	restTemplate = new RestTemplate();
//   // }
//   String id_t;
//   @BeforeEach
//   void setUp()
//   {
//
//       List<Subtask> subtaskList = new ArrayList<>();
//       Subtask s=new Subtask("ds", "f", "dsa");
//       subtaskList.add(s);
//       Date specificDate = new Date(1612656000000L);
//       Task task = new Task("Task1", "Task desc1", "completed",specificDate,subtaskList);
//       taskRepo.save(task);
//       id_t=task.getId();
//   }
//
//   @AfterEach
//   void TearDown()
//   {
//       task=null;
//       if (this.taskRepo != null) {
//           this.taskRepo.deleteAll(); // Ensure taskRepo is not null before accessing its methods
//       }
//   }
//
//
//
//   @Test
//   void testFindById_found()
//   {
//       Task t= taskRepo.getTaskById(id_t);
//       assertThat(id_t).isEqualTo(t.getId());
////		assertThat(cloudVendorList.get(0).getVendorAddress())
////				.isEqualTo(cloudVendor.getVendorAddress());
////		assertNotNull(task);
////        assertEquals("Task Name", task.getName());
////        assertEquals("Task Description", task.getDescription());
////        assertEquals("Task Status", task.getStatus());
////        assertNotNull(task.getDeadline());
////        assertEquals(1, task.getSubtasks().size());
////        assertEquals("Subtask Name", task.getSubtasks().get(0).getName());
////        assertEquals("Subtask Description", task.getSubtasks().get(0).getDescription());
////        assertEquals("Subtask Status", task.getSubtasks().get(0).getStatus());
//   }
//
//
//
//   // @Autowired
//   // private TestRepo testRepository;
//
//   // @Test
//   // public void addTask(){
//   // 	 List<Subtask> subtaskList = new ArrayList<>();
//   // 	Subtask s=new Subtask("ds", "f", "dsa");
//   // 	subtaskList.add(s);
//   // 	Date deadline = new Date();
//   // 	Task task = new Task("Task1", "Task desc1", "completed",deadline,subtaskList);
//   // 	Task response = restTemplate.postForEntity(baseUrl+"/insert", task, Task.class).getBody();
//   // 	System.out.println(response);
//   // 	assertNotNull(task.getId()); // Assuming ID is auto-generated
//   //     assertEquals("Task 1", task.getName());
//   //     assertEquals("Task desc1", task.getDescription());
//   //     assertEquals("completed	", task.getStatus());
//   //     assertEquals(deadline, task.getDeadline());
//   //     assertEquals(1, task.getSubtasks().size());
//   //     assertEquals(s, task.getSubtasks().get(0));
//
//   // }
//
//   // @Test
//   // @Sql(statements = "insert into table_product(id, name, price, department) values (1, 'Pen', '30', 'Stattionary')",executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
//   // @Sql(statements = "delete from table_product where name='Pen'", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
//   // public void getAllProducts(){
//   // 	//Product product = new Product("Book", "200", "Stationary");
//   // 	List<Product> response = restTemplate.getForObject(baseUrl+"/fetchAll", List.class);
//   // 	assertEquals(2, response.size());
//   // 	assertEquals(2, testRepository.findAll().size());
//
//   // }
//
//   // @Test
//   // @Sql(statements = "insert into table_product(id, name, price, department) values (2, 'Book2', '300', 'Stattionary')",executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
//   // @Sql(statements = "delete from table_product where name='Book2'", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
//   // public void getProductById(){
//   // 	//Product product = new Product("Book", "200", "Stationary");
//   // 	Product response = restTemplate.getForObject(baseUrl+"/fetch/{id}", Product.class, 2);
//   // 	//assertEquals(2, response.size());
//   // 	//assertEquals(2, testRepository.findAll().size());
//   // 	assertAll(
//   // 			()->assertNotNull(response),
//   // 			()->assertEquals(2, response.getId()),
//   // 			()-> assertEquals("Book2", response.getName())
//   // 	);
//
//   // }
//
//   // @Test
//   // @Sql(statements = "insert into table_product(id, name, price, department) values (3, 'Book3', '300', 'Stattionary')",executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
//   // @Sql(statements = "delete from table_product where name='Book3'", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
//   // public void getUpdateProductById(){
//   // 	Product product = new Product("Book3", "200", "Stationary");
//   // 	Product response = restTemplate.getForObject(baseUrl+"/fetch/{id}", Product.class, 3);
//   // 	restTemplate.put(baseUrl+"/update/{id}", product, 3);
//   // 	Product response1 = restTemplate.getForObject(baseUrl+"/fetch/{id}", Product.class, 3);
//   // 	assertAll(
//   // 			()->assertNotNull(response1),
//   // 			()->assertEquals(3, response.getId()),
//   // 			()-> assertEquals("Book3", response.getName())
//   // 	);
//
//   // }
//
//   // @Test
//   // @Sql(statements = "insert into table_product(id, name, price, department) values (5, 'Book4', '300', 'Stattionary')",executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
//   // public void deletteProductById(){
//   // 	List<Product> response = restTemplate.getForObject(baseUrl+"/fetchAll", List.class);
//   // 	assertEquals(2, testRepository.findAll().size());
//   // 	restTemplate.delete(baseUrl+"/delete/{id}", 5);
//   // 	assertEquals(1, testRepository.findAll().size());
//   // }
//
//
//}
//
//
//
//
