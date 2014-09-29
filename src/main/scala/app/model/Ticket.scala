package model

trait Ticket {
  val id: Long
  val title: String
  val status: TicketStatus
}
