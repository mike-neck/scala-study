package model

trait TicketStatus {}
object TicketStatus {
  case object Open extends TicketStatus
  case object Fixed extends TicketStatus

  def of(status: String): Option[TicketStatus] = {
    status.toLowerCase match {
      case "open" => Option(Open)
      case "fixed" => Option(Fixed)
      case _ => None
    }
  }
}
