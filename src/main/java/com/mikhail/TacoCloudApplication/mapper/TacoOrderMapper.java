//package com.mikhail.TacoCloudApplication.mapper;
//
//import com.mikhail.TacoCloudApplication.model.TacoOrder;
//import org.springframework.jdbc.core.RowMapper;
//import org.springframework.stereotype.Component;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//@Component
//public class TacoOrderMapper implements RowMapper<TacoOrder> {
//    @Override
//    public TacoOrder mapRow(ResultSet rs, int rowNum) throws SQLException {
//        return new TacoOrder(
//                rs.getLong("id"),
//                rs.getString("deliveryName"),
//                rs.getString("deliveryStreet"),
//                rs.getString("deliveryCity"),
//                rs.getString("deliveryState"),
//                rs.getString("deliveryZip"),
//                rs.getString("ccNumber"),
//                rs.getString("ccExpiration"),
//                rs.getString("ccCVV")
//        );
//    }
//}
