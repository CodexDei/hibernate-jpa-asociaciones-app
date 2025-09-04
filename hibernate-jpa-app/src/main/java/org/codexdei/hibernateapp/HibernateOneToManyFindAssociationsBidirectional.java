package org.codexdei.hibernateapp;

import jakarta.persistence.EntityManager;
import org.codexdei.hibernateapp.entity.Customer;
import org.codexdei.hibernateapp.entity.Invoice;
import org.codexdei.hibernateapp.util.JpaUtil;

public class HibernateOneToManyFindAssociationsBidirectional {

    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();

        try{
            em.getTransaction().begin();

            Customer customer = em.find(Customer.class,4L);

            Invoice invoice1 = new Invoice("Tech shopping",1000l);
            Invoice invoice2 = new Invoice("Supermarket shopping",500L);

            //El siguiente metodo no solo agrega las factuaras, sino que tambien
            //agrega a las facturas el cliente, lo cual se DEBE hacer porque
            //es una relacion o asociacion bidimencional
            customer.addInvoice(invoice1)
                    .addInvoice(invoice2);

            //El merge es opcional, porque con el em.find() ya
            //entra en la persistencia y modificar el cliente
            //el sistema al hacer el commit lo edita por
            //ello comentamos el merge()
            //guardamos en la base de datos mysql
//            em.merge(customer);

            em.getTransaction().commit();

            System.out.println(customer);

        }catch (Exception e){
            em.getTransaction().rollback();
            e.printStackTrace();
        }finally {
            em.close();
        }
    }
}
