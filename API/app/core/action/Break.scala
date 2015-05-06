package core.action

import akka.pattern.ask
import akka.util.Timeout
import core.KokkoTypes._
import core.{KokkoCore, KokkoRoom, Room}
import models.Tables._
import models.Tables.profile.simple._
import play.api.Logger
import play.api.db.slick.Session

import scala.concurrent.Await
import scala.concurrent.duration._

/**
 * 解散
 * Created by ROS on 2015/03/27.
 */
case class Break(player: String) extends RequestProcessor {
  def execute()(implicit session: Session) = {
    val member: Option[Player] = Member.filter(_.registerName === player).firstOption

    member match {
      case None =>
        Logger.info("break:Unknown User" + this)
        None
      case Some(m) =>
        //create Room
        implicit val timeout = Timeout(5 seconds)
        val future = KokkoCore.roomActor ? KokkoRoom.Create("TODO", m)
        val result = Await.result(future, timeout.duration).asInstanceOf[Room]
        Logger.info(result.toString)
        None
    }
  }
}