package core.action

import core.KokkoTypes._
import core.{KokkoCore, KokkoRoom}
import models.Tables._
import models.Tables.profile.simple._
import play.api.Logger
import play.api.db.slick.Session

/**
 * ゲーム参加
 * Created by ROS on 2015/03/27.
 */
case class Join(player: String, gameNo: Int) extends RequestProcessor {
  def execute()(implicit session: Session) = {
    val member: Option[Player] = Member.filter(_.registerName === player).firstOption

    member match {
      case None =>
        Logger.info("join:Unknown User" + this)
        None
      case Some(m) =>
        //join Room
        KokkoCore.roomActor ! KokkoRoom.Join(gameNo, m)
        None
    }
  }
}