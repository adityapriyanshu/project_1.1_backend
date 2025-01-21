////package com.cts.main.entities;
////
////import java.util.List;
////
////import jakarta.persistence.Column;
////import jakarta.persistence.Entity;
////import jakarta.persistence.GeneratedValue;
////import jakarta.persistence.GenerationType;
////import jakarta.persistence.Id;
////import jakarta.persistence.JoinColumn;
////import jakarta.persistence.JoinTable;
////import jakarta.persistence.ManyToMany;
////import jakarta.persistence.Table;
////import lombok.Data;
////import lombok.NoArgsConstructor;
////
////@Entity
////@Data
////@Table(name = "customer_order")
////@NoArgsConstructor
////public class CustomerOrder {
////	@Id
////	@GeneratedValue(strategy = GenerationType.IDENTITY)
////	@Column
////	private Long id;
////
////	@Column(name = "customer_name")
////	private String customerName;
////
////	@Column(name = "customer_phone_number")
////	private String customerPhoneNumber;
////
////	@Column(name = "customer_table_number")
////	private String customerTableNumber;
////
////	@ManyToMany
////	@JoinTable(name = "customer_order_with_order_items", 
////	joinColumns = @JoinColumn(name = "customer_order_id"), 
////	inverseJoinColumns = @JoinColumn(name = "order_items_id"))
////	private List<MenuItem> orderItems;
////
////	@Column(name = "total_price")
////	private double totalPrice;
////
////	public CustomerOrder(String customerName, String customerPhoneNumber, String customerTableNumber,
////			List<MenuItem> orderItems, double totalPrice) {
////		super();
////		this.customerName = customerName;
////		this.customerPhoneNumber = customerPhoneNumber;
////		this.customerTableNumber = customerTableNumber;
////		this.orderItems = orderItems;
////		this.totalPrice = totalPrice;
////	}
////	
////	
////}
//
//
//
//package com.cts.main.entities;
//
//import java.util.List;
//
//import jakarta.persistence.*;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Entity
//@Data
//@Table(name = "customer_order")
//@NoArgsConstructor
//public class CustomerOrder {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(name = "customer_name")
//    private String customerName;
//
//    @Column(name = "customer_phone_number")
//    private String customerPhoneNumber;
//
//    @Column(name = "customer_table_number")
//    private String customerTableNumber;
//
//    @ManyToMany
//    @JoinTable(name = "customer_order_with_order_items", 
//               joinColumns = @JoinColumn(name = "customer_order_id"), 
//               inverseJoinColumns = @JoinColumn(name = "order_items_id"))
//    private List<MenuItem> orderItems;
//
//    @Column(name = "total_price")
//    private double totalPrice;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id")
//    private User user;
//
//    public CustomerOrder(String customerName, String customerPhoneNumber, String customerTableNumber,
//                         List<MenuItem> orderItems, double totalPrice, User user) {
//        this.customerName = customerName;
//        this.customerPhoneNumber = customerPhoneNumber;
//        this.customerTableNumber = customerTableNumber;
//        this.orderItems = orderItems;
//        this.totalPrice = totalPrice;
//        this.user = user;
//    }
//}
//


package com.cts.main.entities;

import java.util.List;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "customer_order")
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CustomerOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "customer_phone_number")
    private String customerPhoneNumber;

    @Column(name = "customer_address")
    private String customerAddress;

    @ManyToMany
    @JoinTable(name = "customer_order_with_order_items", 
               joinColumns = @JoinColumn(name = "customer_order_id"), 
               inverseJoinColumns = @JoinColumn(name = "order_items_id"))
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<MenuItem> orderItems;

    @Column(name = "total_price")
    private double totalPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private User user;

    public CustomerOrder(String customerName, String customerPhoneNumber, String customerAddress,
                         List<MenuItem> orderItems, double totalPrice) {
        this.customerName = customerName;
        this.customerPhoneNumber = customerPhoneNumber;
        this.customerAddress = customerAddress;
        this.orderItems = orderItems;
        this.totalPrice = totalPrice;
    }
}
