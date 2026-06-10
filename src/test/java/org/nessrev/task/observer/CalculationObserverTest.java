package org.nessrev.task.observer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.nessrev.task.entity.NumericArrayEntity;
import org.nessrev.task.enums.EventType;
import org.nessrev.task.observer.impl.CalculationObserver;
import org.nessrev.task.service.calculation.CalculationService;

import java.util.UUID;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CalculationObserverTest {

  @Mock
  private static CalculationService calculationService;

  @Mock
  private NumericArrayEntity<?> entity;

  @InjectMocks
  private CalculationObserver observer;

  @Test
  void shouldCallRecalculateWhenEventIsUpdate() {
    observer.update(entity, EventType.UPDATE);

    verify(calculationService, times(1)).recalculate(entity);
    verifyNoMoreInteractions(calculationService);
  }

  @Test
  void shouldCallRemoveDataWhenEventIsDelete() {
    var id = java.util.UUID.randomUUID();
    when(entity.getId()).thenReturn(id);

    observer.update(entity, EventType.DELETE);

    verify(calculationService, times(1)).removeDataFromStorage(id);
    verifyNoMoreInteractions(calculationService);
  }

  @Test
  void shouldNotCallRemoveDataWhenUpdate() {
    observer.update(entity, EventType.UPDATE);

    verify(calculationService, never()).removeDataFromStorage(any());
  }

  @Test
  void shouldCallRemoveDataWhenDelete() {
    var id = UUID.randomUUID();
    when(entity.getId()).thenReturn(id);

    observer.update(entity, EventType.DELETE);

    verify(calculationService).removeDataFromStorage(id);
  }
}