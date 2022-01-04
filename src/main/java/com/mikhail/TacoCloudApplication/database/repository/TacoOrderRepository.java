package com.mikhail.TacoCloudApplication.database.repository;

import com.mikhail.TacoCloudApplication.model.TacoOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TacoOrderRepository extends JpaRepository<TacoOrder, String> {

}
