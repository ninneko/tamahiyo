package core

import play.libs.Akka
import akka.actor._
import models.Tables._
/**
 * Created by ROS on 2015/03/18.
 */

object Types {
  type Player = MemberRow
}

object KokkoCore {
  /* 定員 */
  val MAX_PLAYER = 8

  /* 部屋管理アクター */
  val roomActor = Akka.system.actorOf(Props[KokkoRoom])


}

