package org.codexdei.hibernateapp.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "facturas")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "descripcion")
    private String description;

    private Long total;

    //Esta es la relacion o asociacion: muchos a Uno
    //la primera parte hace referencia a la clase donde estamos: Many, entonces seria muchas facturas un
    //solo cliente, es  decir, muchas facturas pueden pertenecer a un cliente
    //ToOne  hace referencia a la otra clase con la cual se asociara, tiene que hacerse asi
    @ManyToOne
    @JoinColumn(name = "id_clientes")
    private Customer customer;

    //Constructores
    public Invoice(){
    }
    public Invoice(String description, Long total){

        this.description = description;
        this.total = total;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", total=" + total +
                '}';
    }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass()) return false;
        Invoice invoice = (Invoice) o;
        return Objects.equals(id, invoice.id) && Objects.equals(description, invoice.description) && Objects.equals(total, invoice.total);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, total);
    }
}
