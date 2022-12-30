package ru.mikhelson.springcourse.SpringApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.mikhelson.springcourse.SpringApp.models.Person;
import ru.mikhelson.springcourse.SpringApp.repositories.PeopleRepository;
import ru.mikhelson.springcourse.SpringApp.security.PersonDetails;

import java.util.Optional;

@Service
public class PersonDetailsService implements UserDetailsService
{
    private final PeopleRepository peopleRepository;

    @Autowired
    public PersonDetailsService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<Person> person = peopleRepository.findByUsername(s);

        if(person.isEmpty())
        {
            throw new UsernameNotFoundException("Пользователь не найден!");
        }
        return new PersonDetails(person.get());
    }
}
