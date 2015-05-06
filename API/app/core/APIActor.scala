package core

import java.net.InetSocketAddress

import akka.actor._
import akka.pattern.ask
import akka.util.Timeout
import core.action.{KokkoException, RequestDispatcher}
import play.api.Logger
import play.api.Play.current
import play.api.db.slick._
import play.api.libs.json._
import play.libs.Akka
import redis.actors.RedisSubscriberActor
import redis.api.pubsub.{Message, PMessage}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.concurrent.duration._

/**
 * Created by ROS on 2015/03/19.
 */
case class InEvent(id: String, method: String, channel: String, arguments: JsValue)
case class OutEvent(id: String, method: String, channel: String, data: Option[JsValue] = None, error: Option[KokkoException])

class PubSubWorker(channels: Seq[String] = Nil, patterns: Seq[String] = Nil) extends RedisSubscriberActor(new InetSocketAddress("localhost", 6379), channels, patterns) {
  def onMessage(message: Message) {
    //parse INEvent Frame
    implicit val exceptionFormats = Json.format[KokkoException];
    implicit val inEventFormat = Json.format[InEvent]
    implicit val outEventFormat = Json.format[OutEvent]
    implicit val timeout = Timeout(5 seconds)
    implicit val system = Akka.system

    try {
      val in = Json.parse(message.data).asOpt[InEvent]

      in match {
        case None =>
          Logger.error("Unrecognized message from redis : " + message.data)
        case Some(e) =>
          val future = (KokkoCore.apiActor ? e)

          future.asInstanceOf[Future[OutEvent]].map { t =>
            KokkoCore.redis.publish(KokkoCore.pubCh, Json.stringify(Json.toJson(t)))
          }
      }
    } catch {
      case e: Exception => Logger.error("Cannot parse message from redis", e)
    }

  }

  def onPMessage(pmessage: PMessage) {
    println(s"pattern message received: $pmessage")
    onMessage(new Message(pmessage.channel, pmessage.data))
  }
}

class APIActor() extends Actor {
  def receive = {
    case msg: InEvent =>
      //dispatch
      val action = RequestDispatcher.dispatchMethod(msg)

      val response = try {
        DB.withSession[OutEvent] { implicit session =>
          val result = action.execute()
          OutEvent(id = msg.id, method = msg.method, channel = msg.channel, data = result, error = None)
        }
      }
      catch {
        case e: KokkoException =>
          OutEvent(id = msg.id, method = msg.method, channel = msg.channel, data = None, error = Some(e))
        case e: Exception =>
          Logger.error("", e)
      }
      //create OutEventFrame
      sender ! response
    case _ =>
      println("cannot parse message:")
  }
}






