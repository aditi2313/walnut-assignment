package org.acme;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class PersonService {

    @Inject
    EntityManager em;

    @Transactional
    public Person savePerson(String name) {
        Person person = new Person();
        person.setName(name);
        em.persist(person);
        return person;
    }

    @Transactional
    public List<String> getAllNames() {
        return em.createQuery("SELECT p.name FROM Person p", String.class)
                .getResultList();
    }
}