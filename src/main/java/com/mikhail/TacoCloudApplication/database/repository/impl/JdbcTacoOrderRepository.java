package com.mikhail.TacoCloudApplication.database.repository.impl;

import com.mikhail.TacoCloudApplication.database.repository.TacoOrderRepository;
import com.mikhail.TacoCloudApplication.exceptions.NoSuchIdException;
import com.mikhail.TacoCloudApplication.model.IngredientRef;
import com.mikhail.TacoCloudApplication.model.Taco;
import com.mikhail.TacoCloudApplication.model.TacoOrder;
import org.springframework.asm.Type;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Types;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class JdbcTacoOrderRepository implements TacoOrderRepository {

    private final JdbcOperations jdbcOperations;

    public JdbcTacoOrderRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    @Transactional
    public TacoOrder save(TacoOrder tacoOrder) {
        PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory(
                "INSERT INTO taco_order "
                        + "(\n"
                        + "delivery_name, delivery_street, delivery_city,\n"
                        + "delivery_state, delivery_zip, cc_number,\n"
                        + "cc_expiration, cc_cvv, placed_at\n"
                        + ")\n"
                        + "VALUES (?,?,?,?,?,?,?,?,?)",
                Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
                Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
                Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP
        );
        pscf.setReturnGeneratedKeys(true);

        tacoOrder.setPlacedAt(new Date());
        PreparedStatementCreator psc = pscf.newPreparedStatementCreator(
                Arrays.asList(
                        tacoOrder.getDeliveryName(),
                        tacoOrder.getDeliveryStreet(),
                        tacoOrder.getDeliveryCity(),
                        tacoOrder.getDeliveryState(),
                        tacoOrder.getDeliveryZip(),
                        tacoOrder.getCcNumber(),
                        tacoOrder.getCcExpiration(),
                        tacoOrder.getCcCVV(),
                        tacoOrder.getPlacedAt()
                )
        );

        Long orderId = getTacoOrderId(tacoOrder, psc);

        List<Taco> tacos = tacoOrder.getTacos();
        AtomicInteger orderKey = new AtomicInteger();
        tacos.forEach(taco -> saveTaco(orderId, orderKey.getAndIncrement(), taco));

        return tacoOrder;
    }

    private Long getTacoOrderId(TacoOrder tacoOrder, PreparedStatementCreator psc) {
        Long id = getId(psc);
        Optional.of(id).ifPresent(tacoOrder::setId);
        return id;
    }

    private void saveTaco(Long orderId, int orderKey, Taco taco) {
        taco.setCreatedAt(new Date());
        PreparedStatementCreatorFactory pscf =
                new PreparedStatementCreatorFactory(
                        "insert into Taco "
                                + "(name, created_at, taco_order, taco_order_key) "
                                + "values (?, ?, ?, ?)",
                        Types.VARCHAR, Types.TIMESTAMP, Type.LONG, Type.LONG
                );
        pscf.setReturnGeneratedKeys(true);

        PreparedStatementCreator psc = pscf.newPreparedStatementCreator(
                Arrays.asList(
                        taco.getName(),
                        taco.getCreatedAt(),
                        orderId,
                        orderKey
                )
        );

        Long tacoId = getTacoId(taco, psc);
        saveIngredientRef(tacoId, taco.getIngredientRefs());
    }

    private Long getTacoId(Taco taco, PreparedStatementCreator psc) {
        Long id = getId(psc);
        Optional.of(id).ifPresent(taco::setId);
        return id;
    }


    private void saveIngredientRef(Long tacoId, List<IngredientRef> ingredientRefs) {
        int key = 0;
        for (IngredientRef ingredientRef : ingredientRefs) {
            jdbcOperations.update(
                    "insert into ingredient_ref (ingredient, taco, taco_key)\n"
                            + "values (?, ?, ?)",
                    ingredientRef.getIngredient(), tacoId, key++);
        }
    }

    private Long getId(PreparedStatementCreator psc) {
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcOperations.update(psc, keyHolder);

        Map<String, Object> keyMap = Optional.of(keyHolder).map(GeneratedKeyHolder::getKeys).orElse(null);
        Object id = Optional.ofNullable(keyMap).map(map -> map.get("id")).orElseThrow(() -> new NoSuchIdException("No such id found"));
        Integer intId = (Integer) id;
        return intId.longValue();
    }

}
