package org.codexdei.hibernateapp.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

//relaciona esta clase con la table en la DB o BD
@Entity
//Como el nombre de la clase es diferente al de la DB se
//Coloca esta anotacion y se indica el nombre que tiene en la base de datos
@Table(name = "clientes")
public class Customer {

    //las siguientes dos anotaciones conectan el id que es tambien una llave primaria con
    //esta clase
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //como los nombres de la columnas de la DB son diferentes se coloca la
    //siguiente anotacion con el nombre que tiene en la tabla
    @Column(name = "nombre")
    private String name;
    @Column(name = "apellido")
    private String lastName;
    @Column(name = "forma_pago")
    private String paymentMethod;
    //COn la siguiente anotacion puede utilizar los atributos de otra clase que tenga la anotacion "@Embeddable"
    @Embedded
    private Audit audit = new Audit();

    //cascadeType.ALL indica que todas las operaciones de la entidad padre se propagan en la hija
    //orphanRemoval = true significa que si el padre se elimina se elimina la direccion
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    //@JoinColumn(name = "id_cliente")
    //Tabla intermedia personalizada
    @JoinTable(name = "tbl_clientes_direcciones", joinColumns = @JoinColumn(name = "id_cliente"),
    inverseJoinColumns = @JoinColumn(name = "id_direccion"),
    uniqueConstraints = @UniqueConstraint(columnNames = {"id_direccion"})
    )
    private List<Addresses> addresses;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "customer")
    private List<Invoice> invoices;

    //al contener el OneToOne hace que esta clase sea el dueño de la relacion o asociacion
    //por tanto el que tiene la fk o llave foranea
    //CascadeType.ALL => cualquier operacion sobre el padre se aplicara sobre la entidad asociada
    //orphanRemoval = true => si se quita la relacion, en objeto asociado se borra porque quedo huerfano
    //mappedBy = inmediatamente indica que esta entidad no es el dueño de la relacion, por lo cual la FK esta
    //en la otra entidad
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "customer")
    //el que tiene el @JoinColumn(name = "cliente_detalles_id") es el dueño de la relacion, por ello fue
    // coemntado y solo dejado para explicar, en @JoinColumn(name = "cliente_detalles_id") esta activo
    //en la otra entidad, la cual es dueña de la relacion
//    @JoinColumn(name = "cliente_detalles_id")
    CustomerDetail detail;

    //siempre que se usa entity y se tenga un constructor con parametros se
    // tiene que implementar un constructor vacio, sino al crear un objeto tipo Cliente lanara un error
    public Customer() {

        invoices = new ArrayList<>();
        addresses = new ArrayList<>();
    }

    public Customer(String name, String lastName){
        this();
        this.name = name;
        this.lastName = lastName;
    }

    public Customer(Long id, String name, String lastName, String paymentMethod) {
        this();
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.paymentMethod = paymentMethod;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public List<Addresses> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Addresses> addresses) {
        this.addresses = addresses;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Audit getAudit() {
        return audit;
    }

    public void setAudit(Audit audit) {
        this.audit = audit;
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
    }

    public CustomerDetail getDetail() {
        return detail;
    }

    public void setDetail(CustomerDetail detail) {
        this.detail = detail;
    }

    public void addDetail(CustomerDetail detail) {
        this.detail = detail;
        detail.setCustomer(this);
    }

    public void removeDetail(CustomerDetail detail) {
        detail.setCustomer(null);
        this.detail = null;
    }

    public Customer addInvoice(Invoice invoice){

        this.invoices.add(invoice);
        invoice.setCustomer(this);
        return this;
    }



    @Override
    public String toString() {

        LocalDateTime created = this.audit != null ? audit.getCreatedIn() : null;
        LocalDateTime edited = this.audit != null ? audit.getEditedIn() : null;

        return  "{" +
                "idCustomer=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", payment_method=" + paymentMethod +
                ", cretedIn='" + created + '\'' +
                ", editedIn='" + edited + '\'' +
                ", addresses='" + addresses + '\'' +
                ", Invoices='" + invoices + '\'' +
                ", Details='" + detail + '\'' +
                '}';

    }


    public Customer removeInvoice(Invoice invoice) {

        this.invoices.remove(invoice);
        invoice.setCustomer(null);

        return this;
    }
}
