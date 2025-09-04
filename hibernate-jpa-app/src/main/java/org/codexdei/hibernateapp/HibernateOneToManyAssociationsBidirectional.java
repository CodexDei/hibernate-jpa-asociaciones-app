package org.codexdei.hibernateapp;

import jakarta.persistence.EntityManager;
import org.codexdei.hibernateapp.entity.Customer;
import org.codexdei.hibernateapp.entity.Invoice;
import org.codexdei.hibernateapp.util.JpaUtil;

public class HibernateOneToManyAssociationsBidirectional {

    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();

        try{
            em.getTransaction().begin();

            Customer customer = new Customer("Clark", "Kent");
            customer.setPaymentMethod("debito");

            Invoice invoice1 = new Invoice("Tech shopping",1000l);
            Invoice invoice2 = new Invoice("Supermarket shopping",500L);

            //El siguiente metodo no solo agrega las factuaras, sino que tambien
            //agrega a las facturas el cliente, lo cual se DEBE hacer porque
            //es una relacion o asociacion bidimencional
            customer.addInvoice(invoice1)
                    .addInvoice(invoice2);

            //El siguiente codigo fue sustituido por el anterior es
            //decir se creo un metodo que agregaba las facturas y a su vez el cliente
            //Como es una relacion o asociacion bidireccional(OneToMany-ManyToOne) es
            //necesario tanto agregar las facturas al cliente como
            //agregar el cliente a las facturas
            customer.getInvoices().add(invoice1);
            customer.getInvoices().add(invoice2);
            //Ahora agregamos el cliente a las facturas
            invoice1.setCustomer(customer);
            invoice2.setCustomer(customer);

            //guardamos en la base de datos mysql
            em.persist(customer);

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
