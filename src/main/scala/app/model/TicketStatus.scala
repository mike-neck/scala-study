package model

trait TicketStatus {}
object TicketStatus {
  case object Open extends TicketStatus
  case object Fixed extends TicketStatus
}
