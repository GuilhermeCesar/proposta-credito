package com.calcard.cobranca.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "proposal")
public class Proposal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date createdAt;
    private Date approvedAt;
    private Date disapprovedAt;
    private BigDecimal maxLimit;
    private BigDecimal minLimit;

    @Column(name = "reason_disaprodev")
    private String reasonDisaproved;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer")
    private Customer customer;

    private Proposal() {
        this.createdAt = new Date();
        status = Status.ANALYZING;
    }

    public Proposal(Customer customer) {
        this();
        this.customer = customer;
    }

    public Proposal(Date approvedAt, Customer customer) {
        this(customer);
        this.approvedAt = approvedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getApprovedAt() {
        return approvedAt;
    }

    public void setApprovedAt(Date approvedAt) {
        this.approvedAt = approvedAt;
    }

    public Date getDisapprovedAt() {
        return disapprovedAt;
    }

    public void setDisapprovedAt(Date disapprovedAt) {
        this.disapprovedAt = disapprovedAt;
    }

    public Status getStatus() {
        return status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getReasonDisaproved() {
        return reasonDisaproved;
    }

    public void setReasonDisaproved(String reasonDisaproved) {
        this.reasonDisaproved = reasonDisaproved;
    }

    public BigDecimal getMaxLimit() {
        return maxLimit;
    }

    public void setMaxLimit(BigDecimal maxLimit) {
        this.maxLimit = maxLimit;
    }

    public BigDecimal getMinLimit() {
        return minLimit;
    }

    public void setMinLimit(BigDecimal minLimit) {
        this.minLimit = minLimit;
    }
}
