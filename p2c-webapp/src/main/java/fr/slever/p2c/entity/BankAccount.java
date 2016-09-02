package fr.slever.p2c.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Bank account o the user
 * 
 * @author sebastienlever
 *
 */
@Entity
public class BankAccount {

  @Id
  @GeneratedValue
  private Long id;

  @Column
  private BigDecimal amount;
}
