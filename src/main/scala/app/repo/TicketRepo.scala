package repo

import model.TicketStatus.{Open, Fixed}
import model.{TicketStatus, Bug, Issue, Ticket}

object TicketRepo {
  private var map: Map[TicketId, Ticket] = Map(
    1L -> Issue(1, "メインページを作る", Fixed),
    2L -> Issue(2, "サブページを作る"),
    3L -> Bug(3, "メインページの左上のアイコンが違う", "こちらで指定したものを貼り付けてください"),
    4L -> Bug(4, "Next Pageの綴りが違う", "Next PageとするべきところがNext Peijiになってるので直してください", Fixed)
  )

  def findAll(): Seq[Ticket] = {
    map.values.toSeq.sortBy(_.id)
  }

  def findById(id: TicketId): Option[Ticket] = {
    map.get(id)
  }

  def createIssue(title: String): Issue = {
    val nextId: TicketId = map.keys.max + 1
    val issue: Issue = Issue(nextId, title)
    map = map  + (nextId -> issue)
    issue
  }

  def createBug(title: String, description: String): Bug = {
    val nextId: TicketId = map.keys.max + 1
    val bug: Bug = Bug(nextId, title, description)
    map = map + (nextId -> bug)
    bug
  }

  def findIssuesByStatus(status: String): Option[Seq[Issue]] = {
    val st: Option[TicketStatus] = TicketStatus.of(status)
    st.map(s => {
      map.values.toSeq collect {
        case x: Issue if x.status == s => x
      }
    })
  }

  def findBugByStatus(status: String): Option[Seq[Bug]] = {
    val st: Option[TicketStatus] = TicketStatus.of(status)
    st.map(s => {
      map.values.toSeq collect {
        case x: Bug if x.status == s => x
      }
    })
  }

  def fix(id: TicketId): Boolean = {
    val ticket: Option[Ticket] = map.values.find(t => t.id == id && t.status == Open)
    ticket.exists(t => {
      map = map.updated(t.id, t match {
        case x: Issue => Issue(x.id, x.title, Fixed)
        case x: Bug => Bug(x.id, x.title, x.description, Fixed)
      })
      true
    })
  }
}
