package repo

import model.TicketStatus.Fixed
import model.{Bug, Issue, Ticket}

object TicketRepo {
  private var map: Map[TicketId, Ticket] = Map(
    1L -> Issue(1, "メインページを作る", Fixed),
    2L -> Issue(2, "サブページを作る"),
    3L -> Bug(3, "メインページの左上のアイコンが違う", "こちらで指定したものを貼り付けてください"),
    4L -> Bug(4, "Next Pageの綴りが違う", "Next PageとするべきところがNext Peijiになってるので直してください", Fixed)
  )

  def findAll(): Seq[Ticket] = {
    map.values.toSeq.sortBy(t => t.id)
  }
}
