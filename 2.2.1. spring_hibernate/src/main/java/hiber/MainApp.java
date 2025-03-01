package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User user4 = new User("User1", "Lastname1", "user1@mail.ru");
      User user1 = new User("User2", "Lastname2", "user2@mail.ru");
      User user2 = new User("User3", "Lastname3", "user3@mail.ru");
      User user3 = new User("User4", "Lastname4", "user4@mail.ru");
      Car car = new Car("Ferrari", 2024, user4);
      Car car1 = new Car("Chevrolet", 2025, user1);
      Car car2 = new Car("BMW", 2025, user2);
      Car car3 = new Car("Porsche", 2025, user3);

      userService.add(user4, car);
      userService.add(user1, car1);
      userService.add(user2, car2);
      userService.add(user3, car3);

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = "+user.getCar());
      }

      User userByCarModel = userService.getUserByCarModel("Ferrari", 2024);
      System.out.println(userByCarModel.toString());

      context.close();
   }
}
