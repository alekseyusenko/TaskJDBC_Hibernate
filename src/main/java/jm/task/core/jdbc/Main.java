package jm.task.core.jdbc;
import jm.task.core.jdbc.service.UserServiceImpl;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserServiceImpl userServiceImpl = new UserServiceImpl();
        userServiceImpl.createUsersTable();
        userServiceImpl.saveUser("Ivan", "Petrov", (byte)40);
        userServiceImpl.saveUser("Oleg", "Ivanov", (byte)22);
        userServiceImpl.saveUser("Alexander", "Sidorov", (byte)56);
        userServiceImpl.saveUser("Nikolay", "Tarasov", (byte)33);
        List<User> users = userServiceImpl.getAllUsers();
        for (User user : users) {
            System.out.println(user);
        } //можно и стримом, но у темплейта версия 1.8, поэтому пускай будет так, вдруг чего сломается
        userServiceImpl.cleanUsersTable();
        userServiceImpl.dropUsersTable();
    }
}
