package controller;


import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.CartDTO;
import dto.ModelAndView;
import dto.ProductDTO;
import service.CartService;
import service.ProductService;

public class CartController implements Controller {

   @Override
   public ModelAndView main(HttpServletRequest request, HttpServletResponse response) throws Exception {
      String userId = (String) request.getSession().getAttribute("userId");
      return new ModelAndView("cart/cart.jsp", false);
   }

   public ModelAndView selectAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
      HttpSession session = request.getSession();
      System.out.println("나는 셀렉트 성공");
      int userNo = (int) session.getAttribute("userNo");
      System.out.println("userNO: "+userNo);
      List<CartDTO> list = CartService.selectAll(userNo);
      request.setAttribute("list", list);
      return new ModelAndView("cart/cart.jsp", false);
   }

   public ModelAndView insert(HttpServletRequest request, HttpServletResponse response) throws Exception {
      HttpSession session = request.getSession();
      int userNo = (int) session.getAttribute("userNo");
      int cartQty = Integer.parseInt(request.getParameter("cartQty"));
      int productNo = Integer.parseInt(request.getParameter("productNo"));
      String productName = request.getParameter("productName");
      int result = CartService.insert(userNo, productNo, cartQty);
      
      List<ProductDTO> listName = ProductService.selectByName(productName);
		request.setAttribute("listName", listNa0me);
      
      request.setAttribute("cartQty", cartQty);
      request.setAttribute("productNo", productNo);
      request.setAttribute("result", result);
      
      ProductDTO product = ProductService.selectProduct(Integer.parseInt(request.getParameter("productNo")));
      
      request.setAttribute("product", product);
      
      return new ModelAndView("product/productDetail.jsp", false);
      
   }

}