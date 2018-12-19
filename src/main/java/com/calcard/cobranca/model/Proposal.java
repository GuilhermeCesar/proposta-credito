package com.calcard.cobranca.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "proposal")
public class Proposal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date createAt;
    private Date approvedAt;
    private Date disapprovedAt;
    @Enumerated(EnumType.STRING)
    private Status status;

    public Proposal() {
        this.createAt = new Date();
        status = Status.ANALYZING;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
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

    public void setStatus(Status status) {
        this.status = status;
    }
}
