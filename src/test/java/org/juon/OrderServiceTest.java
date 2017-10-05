package org.juon;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderServiceTest {

/*	@PersistenceContext
	EntityManager em;
	
	@Autowired OrderService orderService;
	@Autowired OrderRepository orderRepository;
	
	@Test
	public void order() throws Exception {
		// Given
		Member member = createMember();
		Item item = createBook("JPA BOOK", 10000, 10);
		int orderQuantity = 4;
		
		// When
		Long orderId = orderService.order(member.getId(), item.getId(), orderQuantity);
		
		// Then
		Order getOrder = orderRepository.findOne(orderId);
		
		assertEquals("상품 주문시 상태", OrderStatus.ORDERED, getOrder.getOrderStatus());
		assertEquals("주문한 상품 종류 수가 정확해야 한다.", 1, getOrder.getOrderItems().size());
		assertEquals("주문 가격은 가격 * 수량이다.", Double.valueOf(10000 * 4) , getOrder.getTotalPrice());
		assertEquals("주문 수량만큼 재고가 줄어야 한다.", 6, item.getStockQuantity());
	}
	
	@Test
	public void search() throws Exception {
		// Given
		Member member = createMember();
		Item item1 = createBook("JPA BOOK1", 10000, 10);
		int orderQuantity1 = 4;
		Item item2 = createBook("JPA BOOK2", 20000, 10);
		int orderQuantity2 = 4;
		
		Member member2 = new Member();
		member2.setName("member2");
		em.persist(member2);

		Item item3 = createBook("JPA BOOK3", 20000, 10);
		int orderQuantity3 = 1;
		
		// When
		orderService.order(member.getId(), item1.getId(), orderQuantity1);
		orderService.order(member.getId(), item2.getId(), orderQuantity2);
		orderService.order(member2.getId(), item3.getId(), orderQuantity3);
		
		// Then
		OrderSearch orderSearch = new OrderSearch();
		orderSearch.setMemberName("member1");
		orderSearch.setOrderStatus(OrderStatus.ORDERED);
		
		List<Order> getOrders = orderRepository.findAll(orderSearch);
		
		assertEquals("총 주문 건수가 정확해야 한다.", 2, getOrders.size());
	}
	
	@Test(expected = NotEnoughStockException.class)
	public void exceedStock() throws Exception {
		// Given
		Member member = createMember();
		Item item1 = createBook("JPA BOOK1", 10000, 10);
		int orderQuantity1 = 11;
		
		// When
		orderService.order(member.getId(), item1.getId(), orderQuantity1);
		
		// Then
		fail("Should occurr NotEnoughStockException!");
		
	}
	
	@Test
	public void cancelOrder() throws Exception {
		// Given
		Member member = createMember();
		Item item = createBook("JPA1", 10000, 10);
		int orderQuantity = 3;
		
		Long orderId = orderService.order(member.getId(), item.getId(), orderQuantity);
		
		// When
		orderService.cancelOrder(orderId);
		
		// Then
		Order getOrder = orderRepository.findOne(orderId);
		assertEquals("주문 취소시 상태값은 CANCEL이다.", OrderStatus.CANCELED, getOrder.getOrderStatus());
		assertEquals("주문 취소시 재고량은 원복되어야 한다.", 10, item.getStockQuantity());
	}
	
	private Member createMember() {
		Member member = new Member();
		member.setName("member1");
		member.setAddress(new Address("Seoul", "Guro", "123-123"));
		em.persist(member);
		
		return member;
	}
	
	private Book createBook(String name, double price, int stockQuantity) {
		Book book = new Book();
		book.setName(name);
		book.setStockQuantity(stockQuantity);
		book.setPrice(price);
		em.persist(book);
		
		return book;
	}
*/
}
