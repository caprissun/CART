package CartService;


import java.util.List;

import dao.CartDAO;
import dao.CartDAOe;
import dto.CartDTO;

public class CartService {
   private static CartDAO dao = new CartDAOe();
   
   
   public static List<CartDTO> selectAll(int userNo) throws Exception{
      return dao.selectAll(userNo);
   }
   
   
   public static int insert(int userNo, int productNo, int cartQty) throws Exception {
      int result = dao.insert(userNo, productNo, cartQty);
      return result;
   }
   
   public static int update(int cartNo, int cartQty) throws Exception {
      int result = dao.update(cartNo, cartQty);
      return result;
   }
   
   public static int delete(int cartNo ) throws Exception {
      int result = dao.delete(cartNo);
      return result;
   }
}

