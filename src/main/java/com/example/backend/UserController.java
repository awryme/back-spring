package com.example.backend;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    @ResponseBody
    // добавление юзера при регистрации
    public void addUser(@RequestBody User p, HttpServletResponse resp) {
        if (!UserStorage.authstorage.containsKey(p.getLogname())) {
            UserStorage.addUser(p);
            System.out.println("Got User");
            resp.setStatus(HttpServletResponse.SC_OK);
        }
        else {
            System.out.println("Already Exist");
            resp.setStatus(HttpServletResponse.SC_CONFLICT);
        }
    }
    // авторизация
    @RequestMapping(value = "/log", method = RequestMethod.POST)
    @ResponseBody
    public void logUser(@RequestBody User p, HttpServletResponse resp) {
        if (UserStorage.authstorage.get(p.getLogname()).equals(p)) {
            // **************** код входа *******************
            System.out.println("Entry Successful");
            resp.setStatus(HttpServletResponse.SC_OK);
        }
        else {
            // **************** неудачный вход *******************
            System.out.println("Entry Fail");
            resp.setStatus(HttpServletResponse.SC_CONFLICT);
        }
    }

}
