package org.codexdei.hibernateapp.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "cliente_detalles")
public class CustomerDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean prime;

    @Column(name = "puntos_acumulados")
    private Long accumulatedPoints;

    //Asociacion o relacion
    //Al tener el OneToOne muestra que es la dueña de la relacion
    @OneToOne
    @JoinColumn(name = "cliente_detalles_id")
    private Customer customer;

    //Constructores
    public CustomerDetail() {
    }

    public CustomerDetail(boolean prime, Long accumulatedPoints) {
        this.prime = prime;
        this.accumulatedPoints = accumulatedPoints;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isPrime() {
        return prime;
    }

    public void setPrime(boolean prime) {
        this.prime = prime;
    }

    public Long getAccumulatedPoints() {
        return accumulatedPoints;
    }

    public void setAccumulatedPoints(Long accumulatedPoints) {
        this.accumulatedPoints = accumulatedPoints;
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
                ", prime=" + prime +
                ", accumulatedPoints=" + accumulatedPoints +
                '}';
    }
}
