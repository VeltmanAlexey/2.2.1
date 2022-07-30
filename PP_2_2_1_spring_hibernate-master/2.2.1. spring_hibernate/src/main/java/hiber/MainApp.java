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
      AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User user1 = new User("Alexey", "Veltman", "alexejvaltman317@mail.ru");
      Car car1 = new Car("lamba", 2154365);

      user1.setCar(car1);
      car1.setUser(user1);

      User user2 = new User("Kristina", "Egorova", "egorova2@mail.ru");
      Car car2 = new Car("firari", 643134);

      user2.setCar(car2);
      car2.setUser(user2);

      userService.add(user1);
      userService.add(user2);

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car model = "+user.getCar().getModel());
         System.out.println("Car series = "+user.getCar().getSeries());
         System.out.println();
      }

      User userByCar1 = userService.userByModelAndSeries("lamba", 2154365);
      System.out.println(userByCar1);

      User userByCar2 = userService.userByModelAndSeries("firari", 643134);
      System.out.println(userByCar2);


      context.close();


   }
}
