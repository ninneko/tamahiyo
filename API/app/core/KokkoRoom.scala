package core

import java.util.Date

import akka.actor.Actor
import core.KokkoTypes.Player
import play.api.Logger
//import core.Types._

/**
 * ゲーム部屋.
 * Created by ROS on 2015/03/18.
 */
case class Room(hash: String, id: Int, created: Date = new Date(), var member: List[Player] = List.empty) {
  lazy val creator = member.head

  def join(n: Player) = {
    if (member.size <= KokkoCore.MAX_PLAYER) {
      member = member :+ n
      Logger.info("Join:Joined" + n + this);
      true
    } else {
      Logger.info("Join:MaxPlayer" + this);
      false
    }
  }

  def leave(n: Player) = member = member.filterNot(_ == n)

  def list = member

}

object KokkoRoom {

  case class Create(n: String, p: Player)

  case class Join(r: Any, p: Player)

  case class Leave(p: Player)

  case class Break(p: Player)

  case class Listing()

}

/**
 * ゲーム部屋管理.
 * TODO:書き直す。REDISに保存するべき
 * Created by ROS on 2015/03/18.
 */
class KokkoRoom extends Actor {
  var room: List[Room] = List.empty

  def genId = {
    Stream.from(1).collectFirst { case n: Int if !room.map(_.id).contains(n) => n}
  }

  def findRoom(find: Any): Option[Room] = {
    find match {
      //find by hash
      case s:String => this.room.find(_.hash == find.asInstanceOf[String])
      //find by Id
      case s:Int => this.room.find(_.id == find.asInstanceOf[Int])
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
        case None =>
          sender ! false
        case Some(r) =>
          sender ! r.join(p)
      }

    case KokkoRoom.Leave(p: Player) =>
      val mRoom = this.room.find(_.member.exists(_==p))

      mRoom match {
        case None =>
          sender ! false
        case Some(r) =>
          sender ! r.leave(p)
      }

    case KokkoRoom.Break(p: Player) =>
      val mRoom = this.room.find(_.member.exists(_==p))
      mRoom match {
        case None =>
        case Some(r) if r.creator == p =>
          //ホストで部屋にはいってる
          room = room.filterNot(_==r) //部屋リストから除外(解散)
        case _=>
      }
      sender ! room
    case KokkoRoom.Listing =>
      sender ! room
    case _ =>
      Logger.info("Unrecognized Message : " + this);
  }
}
