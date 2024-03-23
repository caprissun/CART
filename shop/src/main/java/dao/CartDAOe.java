package dao;

/*
위의 코드는 CartDAO 인터페이스를 구현한 CartDAOI 클래스입니다. 이 클래스는 장바구니와 관련된 데이터베이스 작업을 수행합니다. 각 메서드는 다음과 같은 역할을 합니다.

selectAll(int userCode): 사용자 코드를 기반으로 모든 장바구니 정보를 가져오는 메서드입니다. 해당 사용자의 장바구니에 있는 모든 상품 정보를 조회하여 CartDTO 객체 리스트로 반환합니다.

insert(int userNo, int productNo, int cartQty): 새로운 장바구니 항목을 추가하는 메서드입니다. 사용자 번호, 상품 번호, 수량을 받아와 장바구니 테이블에 새로운 항목을 추가합니다.

update(int cartNo, int cartQty): 장바구니에 있는 특정 상품의 수량을 업데이트하는 메서드입니다. 장바구니 번호와 업데이트할 수량을 받아와 해당 장바구니 항목의 수량을 업데이트합니다.

delete(int cartNo): 장바구니에서 특정 항목을 삭제하는 메서드입니다. 장바구니 번호를 받아와 해당 장바구니 항목을 삭제합니다.

이렇게 구현된 CartDAOI 클래스를 통해 데이터베이스와의 상호작용을 통해 장바구니와 관련된 기능을 구현할 수 있습니다.*/



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.CartDTO;
import dto.ProductDTO;
import dto.UserDTO;
import util.DBManager;

public class CartDAOe implements CartDAO {

      @Override
      public List<CartDTO> selectAll(int userCode) {
         System.out.println(userCode);
           Connection con = null;
           PreparedStatement ps = null;
           ResultSet rs = null;
           String sql = "SELECT * FROM CART C INNER JOIN PRODUCT P ON c.product_no = p.product_no " + 
                 "LEFT OUTER JOIN USERDB U ON c.user_no = u.user_no where u.user_no = ?";
           List<CartDTO> list = new ArrayList<CartDTO>();
           try {
            con = DBManager.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, userCode);
            rs  = ps.executeQuery();
           
            while(rs.next()){
               int cartNo = rs.getInt("CART_NO");
               int productNo = rs.getInt("PRODUCT_NO");
               int cartQty = rs.getInt("CART_QTY");
               String category = rs.getString("PRODUCT_CATEGORY");
               String productName = rs.getString("PRODUCT_NAME");
               int price = rs.getInt("PRODUCT_PRICE");
               int stock = rs.getInt("PRODUCT_STOCK");
               String userPwd = rs.getString("USER_PWD");
               String userId = rs.getString("USER_ID");
               String userName = rs.getString("USER_NAME");
               String userBirth = rs.getString("USER_BIRTHDATE");
               int userPhone = rs.getInt("USER_PHONE");
               String userAddr = rs.getString("USER_ADDR");
               String userEmail = rs.getString("USER_EMAIL");
               String productDate = rs.getString("PRODUCT_DATE");
               UserDTO userDTO = new UserDTO();
               		userDTO.setUserNo(userCode);
               		userDTO.setUserId(userId);
               		userDTO.setUserPwd(userPwd);
               		userDTO.setUserName(userName);
               		userDTO.setUserPhone(userPhone);
               		userDTO.setUserBirth(userBirth);
               		userDTO.setUserEmail(userEmail);
               		userDTO.setUserAddr(userAddr);
               ProductDTO productDTO = new ProductDTO();
                  productDTO.setProductNo(productNo);
                  productDTO.setProductCategory(category);
                  productDTO.setProductName(productName);
                  productDTO.setProductPrice(price);
                  productDTO.setProductStock(stock);
                  productDTO.setProductDate(productDate);
               list.add(new CartDTO(cartNo, userDTO, productDTO, cartQty));
            }

           } catch (SQLException e) {
            e.printStackTrace();
           } finally {
            DBManager.dbClose(rs, ps, con);
           }
           return list;
           
      }


   @Override
   public int insert(int userNo, int productNo, int cartQty) throws Exception {
        Connection con = null;
        PreparedStatement ps = null;
        int result = 0;
        String sql = "INSERT INTO CART VALUES(CART_SEQ.NEXTVAL, ?, ?, ?)";
        
        try {
         con = DBManager.getConnection();
         ps = con.prepareStatement(sql);
         ps.setInt(1, userNo);
         ps.setInt(2, productNo);
         ps.setInt(3, cartQty);
         result  = ps.executeUpdate();
        } catch (SQLException e) {
         e.printStackTrace();
        } finally {
         DBManager.dbClose(ps, con);
        }
        return result;
   }

   @Override
   public int update(int cartNo, int cartQty) throws Exception{
        Connection con = null;
        PreparedStatement ps = null;
        int result = 0;
        String sql = "UPDATE CART SET cart_qty = ? WHERE CART_NO = ?";
        try {
         con = DBManager.getConnection();
         ps = con.prepareStatement(sql);
         ps.setInt(1, cartQty);
         ps.setInt(2, cartNo);
         result  = ps.executeUpdate();
        } catch (SQLException e) {
         e.printStackTrace();
        } finally {
         DBManager.dbClose(ps, con);
        }
        return result;

   }

   @Override
   public int delete(int cartNo) throws Exception{
        Connection con = null;
        PreparedStatement ps = null;
        int result = 0;
        String sql = "delete from cart where cart_no = ?";
        try {
         con = DBManager.getConnection();
         ps = con.prepareStatement(sql);
         ps.setInt(1, cartNo);
         result  = ps.executeUpdate();
        } catch (SQLException e) {
         e.printStackTrace();
        } finally {
         DBManager.dbClose(ps, con);
        }
        return result;
   }
   
   

}