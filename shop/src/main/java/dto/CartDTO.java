package dto;

import dto.NonUserDTO; // NonUserDTO 클래스를 import
import dto.UserDTO; // UserDTO 클래스를 import
import dto.ProductDTO; // ProductDTO 클래스를 import

public class CartDTO{
	private int cartNo; //장바구니 고유번호
	private ProductDTO productDTO; //상품 정보
	private int cartQty; // 장바구니에 담긴 특정 상품의 수량
	private UserDTO userDTO; //장바구니에 상품을 추가한 사용자 정보
	private NonUserDTO nonUserDTO; // 비회원 고유번호
	public CartDTO(int cartNo, UserDTO userDTO, ProductDTO productDTO,
			       int cartQty, NonUserDTO nonUserDTO) {
		super();
		this.cartNo = cartNo;  
		this.userDTO = userDTO; 
		this.productDTO = productDTO;
		this.cartQty = cartQty;
		this.nonUserDTO = nonUserDTO;
	}
	public CartDTO() {
		super();
	}
	public int getCartNo() {
		return cartNo;
	}
	public void setCartNo(int cartNo) {
		this.cartNo = cartNo;
	}
	public UserDTO getUserDTO() {
		return userDTO;
	}
	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}
	public ProductDTO getProductDTO() {
		return productDTO;
	}
	public void setProductDTO(ProductDTO productDTO) {
		this.productDTO = productDTO;
	}
	public int getCartQty() {
		return cartQty;
	}
	public void setCartQty(int cartQty) {
		this.cartQty = cartQty;
	}
    
    public NonUserDTO getNonUserDTO() {
        return nonUserDTO;
    }

    public void setNonUserDTO(NonUserDTO nonUserDTO) {
        this.nonUserDTO = nonUserDTO;
    }
}
