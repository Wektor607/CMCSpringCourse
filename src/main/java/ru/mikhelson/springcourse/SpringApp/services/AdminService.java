package ru.mikhelson.springcourse.SpringApp.services;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class AdminService
{
    // теперь при входе в данный метод будет проверка того, что пользователь имеет соответствующую роль
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void doAdminStuff()
    {
        System.out.println("Вход только для сотрудника");
    }
}
