package pay;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import bag.BagBook;

@Mapper
public interface PayMapper {
	
	@Select("SELECT ba.b_id, b.title, b.pic, ba.quantity, b.price FROM bag ba JOIN book b ON ba.b_id = b.b_id WHERE ba.u_id = #{userId}")
    List<BagBook> findBagItemsByUserId(@Param("userId") int userId);
	
	@Select("SELECT NVL(SUM(ba.quantity * b.price), 0) "
			+ "FROM bag ba "
			+ "JOIN book b ON ba.b_id = b.b_id "
			+ "WHERE ba.u_id = #{userId}")
    int totalPrice(@Param("userId") int userId);
	
	int getNextPaymentId();
	
	void insertPayment(Map<String, Object> paramMap);
	
	void insertPaymentItem (
			@Param("paymentId") int nextPaymentId,
            @Param("userId") int userId,
            @Param("item") BagBook item);
	
	List<Payment> getPaymentHistoryByUserId(@Param("userId") int userId);
	
	@Select("SELECT b_id, title, pic, price, 0 AS quantity FROM book WHERE b_id = #{b_id}")
    BagBook findBookById(@Param("b_id") int b_id);
	
	@Delete("DELETE FROM bag WHERE u_id = #{u_id}")
	void deleteFromBag(@Param("u_id") int userId);

	@Select("Select u_id From users where users.id = #{username}")
	Integer findUIdByUsername(String username);
}


