package kafka.practice.api.entity;

public enum CreditStatus {
  CREATED,
  APPROVED,
  EXPIRED,
  NOT_APPROVED,
  NEED_HUMAN_APPROVE,
  NEED_COLLECTOR,
  SEND_TO_COLLECTOR
}
