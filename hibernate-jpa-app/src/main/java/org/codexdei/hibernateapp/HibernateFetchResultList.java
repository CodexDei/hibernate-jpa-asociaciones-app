package org.codexdei.hibernateapp;

import jakarta.persistence.EntityManager;
import org.codexdei.hibernateapp.entity.Customer;
import org.codexdei.hibernateapp.util.JpaUtil;

import java.util.List;

public class HibernateFetchResultList {

    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();
        //Si cambiamos en la entidad Customer el List a Set nos permitira hacer busqueda de varios bags en una consulta join y no mostrar el error:MultipleBagFetchException
        //se tendria que cambiar todo lo que diga List en Set, sino muestra error, pero
        //No es recomendable hacer esto, ya que aunque se ejecute sin error, afecta el rendimiento, entre mas grande la DB o BD(base de datos) mas lento se hace
        //Lo mejor es hacer crear varios consultas
        List<Customer> customers = em.createQuery("select distinct c from Customer c left outer join fetch c.addresses left outer join fetch c.detail", Customer.class).getResultList();
        List<Customer> customers2 = em.createQuery("select distinct c from Customer c left outer join fetch c.invoices", Customer.class).getResultList();

        customers.forEach(c -> System.out.println(c.getName() + ", address:" + c.getAddresses() /*+ ", Detail:" + c.getDetail()*/));

        em.close();
    }
}
