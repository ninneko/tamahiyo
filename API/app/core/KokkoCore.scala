package core

import akka.actor.Props
import models.Tables._
import play.libs.Akka
import play.modules.rediscala.RedisPlugin
import play.api.Play.current

/**
 * Created by ROS on 2015/03/18.
 */

object KokkoTypes {
  type Player = MemberRow
}

object KokkoCore {

  implicit val system = Akka.system

  /* 定員 */
  val MAX_PLAYER = 8

  /* REDIS Client */
  val redis = RedisPlugin.client()

  /* REDIS SUBチャンネル */
  val subCh = Seq("recv_kokko_msg")

  /* REDIS PUBチャンネル  */
  val pubCh = "send_kokko_msg"

  //sub Actor
  val subscribeActor = Akka.system.actorOf(Props(classOf[PubSubWorker], subCh, Nil).withDispatcher("rediscala.rediscala-client-worker-dispatcher"))

  /* API Actor */
  val apiActor = Akka.system.actorOf(Props(classOf[APIActor]))

  /* 部屋管理アクター */
  val roomActor = Akka.system.actorOf(Props[KokkoRoom])


}

