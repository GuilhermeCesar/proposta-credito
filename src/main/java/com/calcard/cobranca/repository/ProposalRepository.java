package com.calcard.cobranca.repository;

import com.calcard.cobranca.model.Proposal;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ProposalRepository extends CrudRepository<Proposal,Long> {

    @Query("SELECT p FROM Proposal p INNER JOIN p.customer customer " +
            "WHERE customer.socialId LIKE CONCAT('%',UPPER(:socialId),'%')  ")
    public Iterable<Proposal> findProposalsByCustomer(@Param("socialId") String socialId);
}
