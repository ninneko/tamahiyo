package core

import models.Tables._
import java.util.Date
import akka.actor.Actor
import core.Types._

/**
 * Created by ROS on 2015/03/18.
 */
case class Room(hash: String, id: Int, created: Date = new Date(), var member: List[Player] = List.empty) {


  def join(n: Player) = {
    if (member.size <= KokkoCore.MAX_PLAYER) {
      member = member :+ n
      println("Join " + this + ":" + n)
      true
    } else {
      println("Max Player")
      false
    }
  }
  def leave(n: Player) = member = member.filterNot(_ == n)
  def list = member
}

object KokkoRoom {
  case class Create(n: String, p: Player)
  case class Join(r: Any, p: Player)
  case class Leave(r: Any, p: Player)
  case class List()
}
class KokkoRoom extends Actor {
  var room: List[Room] = List.empty

  def genId = {
    Stream.from(1).collectFirst { case n: Int if !room.map(_.id).contains(n) => n }
  }

  def findRoom(find: Any) : Option[Room] = {
    find match {
      //find by hash
      case String=> this.room.find(_.hash == find.asInstanceOf[String] )
      //find by Id
      case Int =>  this.room.find(_.id == find.asInstanceOf[Int] )
      case _ => None
    }
  }

  def receive = {

    case KokkoRoom.Create(name: String, p: Player) =>
      val id = genId
      val hash = java.util.UUID.randomUUID.toString.replace("-", "")
      //create game room
      val r = Room(hash, id.get)
      var fut = r.join(p)
      this.room = this.room :+ r
      sender ! r

    case KokkoRoom.Join(find: Any, p: Player) =>
      findRoom(find) match {
        case None=>
          sender ! false
        case Some(r)=>
          sender ! r.join(p)
      }

    case KokkoRoom.Leave(find: Any, p: Player) =>
      findRoom(find) match {
        case None=>
          sender ! false
        case Some(r)=>
          sender ! r.leave(p)
      }

    case KokkoRoom.List =>
      sender ! room
    case _ =>
      println("unrecognized message!");
  }
}
