package core.action

import core.{KokkoRoom, KokkoCore}
import core.KokkoTypes._
import models.Tables._
import play.api.Logger
import play.api.db.slick.Session
import models.Tables.profile.simple._

/**
 * 抜け
 * Created by ROS on 2015/03/27.
 */
case class Leave(player: String) extends RequestProcessor {
  def execute()(implicit session: Session) = {
    val member: Option[Player] = Member.filter(_.registerName === player).firstOption

    member match {
      case None =>
        Logger.info("leave:Unknown User" + this)
        None
      case Some(m) =>
        //join Room
        KokkoCore.roomActor ! KokkoRoom.Leave(m)
        None
    }
  }
}