package core.action

import akka.pattern.ask
import akka.util.Timeout
import core.{KokkoCore, KokkoRoom}
import play.api.db.slick._

import scala.concurrent.duration._


/**
 * 参加者取得
 * Created by ROS on 2015/03/27.
 */
case class Listing(player: String, gameNo: Int = 0) extends RequestProcessor {
  def execute()(implicit session: Session) = {
    implicit val timeout = Timeout(5 seconds)
    val listFuture = KokkoCore.roomActor ? KokkoRoom.Listing
    //Await.result(listFuture, timeout.duration).asInstanceOf[List[Room]]
    None
  }
}