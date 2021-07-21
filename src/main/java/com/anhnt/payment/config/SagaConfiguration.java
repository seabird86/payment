package com.anhnt.payment.config;

import com.anhnt.payment.consumer.CustomerEventConsumer;
import com.anhnt.domain.event.OrderSnapshotEvent;
import com.anhnt.payment.repository.OrderRepository;
import com.anhnt.payment.repository.entity.OrderEntity;
import io.eventuate.tram.events.common.DomainEvent;
import io.eventuate.tram.events.subscriber.DomainEventDispatcher;
import io.eventuate.tram.events.subscriber.DomainEventDispatcherFactory;
import io.eventuate.tram.spring.optimisticlocking.OptimisticLockingDecoratorConfiguration;
import io.eventuate.tram.viewsupport.rebuild.DBLockService;
import io.eventuate.tram.viewsupport.rebuild.DomainEventWithEntityId;
import io.eventuate.tram.viewsupport.rebuild.DomainSnapshotExportService;
import io.eventuate.tram.viewsupport.rebuild.DomainSnapshotExportServiceFactory;
import io.eventuate.tram.viewsupport.rebuild.SnapshotConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories
@EnableAutoConfiguration
@Import({OptimisticLockingDecoratorConfiguration.class,
        SnapshotConfiguration.class})
public class SagaConfiguration {

  @Bean
  public DomainEventDispatcher domainEventDispatcher(CustomerEventConsumer customerEventConsumer, DomainEventDispatcherFactory domainEventDispatcherFactory) {
    return domainEventDispatcherFactory.make("customerServiceEvents", customerEventConsumer.domainEventHandlers());
  }

//    @Bean
//    public EventuateSchema eventuateSchema() {
//        return new EventuateSchema("payment");
//    }

  @Bean
  public DomainSnapshotExportService<OrderEntity> domainSnapshotExportService(OrderRepository orderRepository,
                                                                              DomainSnapshotExportServiceFactory<OrderEntity> domainSnapshotExportServiceFactory) {
    return domainSnapshotExportServiceFactory.make(
            OrderEntity.class,
            orderRepository,
            order -> {
              DomainEvent domainEvent = new OrderSnapshotEvent(order.getId(),
                      order.getCustomerId(),
                      order.getAmount(),
                      order.getState());

              return new DomainEventWithEntityId(order.getId(), domainEvent);
            },
            new DBLockService.TableSpec("orders", "order0_"),
            "MySqlReader");
  }
}
