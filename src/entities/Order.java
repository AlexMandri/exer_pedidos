package entities;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entities.enums.OrderStatus;

public class Order {
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	private static SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
	
	private Date moment;
	private OrderStatus status;
	private Client client;
	List<OrderItem> items = new ArrayList<>();
	
	
	public Order() {
		
	}

	public Order(Date moment, OrderStatus status, Client client) {
		super();
		this.moment = moment;
		this.status = status;
		this.client = client;
	}

	public Date getMoment() {
		return moment;
	}

	public void setMoment(Date moment) {
		this.moment = moment;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public List<OrderItem> getItems() {
		return items;
	}
	
	public void addItem(OrderItem item) {
		items.add(item);
	}
	
	public void removeItem(OrderItem item) {
		items.remove(item);
	}
	
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public double total() {
		double sum= 0;
		for(OrderItem o : items) {
			sum += o.subTotal();
		}
		return sum;
	}
	
	public String toString() {
		double sum = 0;
		StringBuilder sb = new StringBuilder();
			sb.append("ORDER SUMMARY:" + "\n");
			sb.append("Order moment: "+ sdf.format(moment)+"\n");
			sb.append("Order status: "+ status +"\n");
			sb.append("Client: ");
			sb.append(client.getName()+" ");
			sb.append("("+ sdf1.format(client.getBirthDate())+") ");
			sb.append("- "+client.getEmail()+"\n");
			sb.append("Order Items: \n");
			for(OrderItem orItem: items ) {
				sb.append(orItem.getProduct().getName()+", ");
				sb.append("$"+orItem.getPrice()+", ");
				sb.append("Quantity: "+ orItem.getQuantity()+", ");
				sb.append("Subtotal: $"+orItem.subTotal()+"\n");
				sum+= orItem.subTotal();
			}
			sb.append("Total price: $"+sum);
			
		return sb.toString();
	}
	
	/*ORDER SUMMARY:
Order moment: 20/04/2018 11:25:09
Order status: PROCESSING
Client: Alex Green (15/03/1985) - alex@gmail.com
Order items:
TV, $1000.00, Quantity: 1, Subtotal: $1000.00
Mouse, $40.00, Quantity: 2, Subtotal: $80.00
Total price: $1080.00*/
}
