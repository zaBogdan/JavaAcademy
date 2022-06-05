package com.bnz.services.auth.respository;

import com.bnz.shared.models.Referral;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReferralRepository extends CrudRepository<Referral, String> {
    Referral findByReferral(String referral);
}
