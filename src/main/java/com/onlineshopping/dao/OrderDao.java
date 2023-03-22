package com.onlineshopping.dao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.onlineshopping.model.Orders;
import com.onlineshopping.model.User;

@Repository
public interface OrderDao extends JpaRepository<Orders, Integer> {
	
//	default LocalDateTime parseDate(String dateString) {
//	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
//	    LocalDateTime dateTime = LocalDateTime.parse(dateString, formatter);
//	    return dateTime;
//	}
	default List<Object[]> getProductWiseSalesLast7Days(String sDate, String eDate) {
        String startDate = sDate;//parseDate(sDate).toString();
        String endDate = eDate;//parseDate(eDate).toString();
        return findSalesOfLastSevenDays(startDate, endDate);
    }
	
	@Query("SELECT o.product, SUM(o.quantity), FROM Orders o WHERE o.orderDate BETWEEN :startDate AND :endDate GROUP BY o.product")
	List<Object[]> findSalesOfLastSevenDays(String startDate, String endDate);
	
	List<Orders> findByUser_id(int userId);
	List<Orders> findByOrderId(String orderId);
	List<Orders> findByUser_idAndProduct_id(int userId, int productId);
	List<Orders> findByUser(User user);
	List<Orders> findByDeliveryPersonId(int deliveryPersonId);
	
	 
}
