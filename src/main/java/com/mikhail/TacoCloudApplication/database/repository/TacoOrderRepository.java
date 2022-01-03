package com.mikhail.TacoCloudApplication.database.repository;

import com.mikhail.TacoCloudApplication.model.TacoOrder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TacoOrderRepository extends CrudRepository<TacoOrder, String> {

}
