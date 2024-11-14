package com.desacode.disruptor.transaction;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_transaction")
public class Transaction {
    @Id
    @SequenceGenerator(name = "trans_id", sequenceName = "trans_id", allocationSize = 1)
    @GeneratedValue(generator = "trans_id", strategy = GenerationType.SEQUENCE)
    private Long id;
    private String orderId;
    private String items;
    private int totalPrice;

}
