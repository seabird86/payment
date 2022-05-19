package com.anhnt.payment.repository.entity;

import com.anhnt.common.domain.payment.constant.TransactionStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import java.math.BigDecimal;

@Entity
@Table(name="transaction")
@Access(AccessType.FIELD)
@NoArgsConstructor
@Getter
@Setter
public class TransactionEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Enumerated(EnumType.STRING)
  private TransactionStatus status;
  private Long payerId;
  private Long payeeId;
  private BigDecimal amount;

  @Version
  private Long version;
}
