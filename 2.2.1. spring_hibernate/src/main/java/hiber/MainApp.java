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

        userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
        userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
        userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
        userService.add(new User("User4", "Lastname4", "user4@mail.ru"));

        User nikolai = new User("Nikolai", "Torpeda", "kolyan@yandex.ru");
        Car subaru = new Car("subaru", 123);
        nikolai.setCar(subaru);
        subaru.setUser(nikolai);
        userService.add(nikolai);
        User kipelov = new User("Bespechnii", "Angel", "kipelov@mail.ru");
        Car lada = new Car("Lada", 0);
        kipelov.setCar(lada);
        lada.setUser(kipelov);
        userService.add(kipelov);
        User vasilii = new User("Vasilii", "Gonschik", "skufidon@mail.ru");
        Car jiguli = new Car("jiguli", 5);
        vasilii.setCar(jiguli);
        jiguli.setUser(vasilii);
        userService.add(vasilii);

        System.out.println("get User by car " + userService.getUserByCar("Lada", 0));
        System.out.println("get User by car " + userService.getUserByCar("subaru", 123));
        System.out.println("get User by car " + userService.getUserByCar("jiguli", 5));
        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println();
        }

        context.close();
    }
}
