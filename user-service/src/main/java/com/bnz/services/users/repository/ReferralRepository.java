package com.bnz.services.users.repository;

import com.bnz.shared.models.Referral;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReferralRepository extends CrudRepository<Referral, String> {
    Referral getByUserId(String userId);
}
