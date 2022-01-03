package com.mikhail.TacoCloudApplication.database.repository;

import com.mikhail.TacoCloudApplication.model.TacoOrder;

public interface TacoOrderRepository {

    TacoOrder save(TacoOrder tacoOrder);

}
