package controllers

import core.KokkoCore
import core.KokkoRoom
import models.Tables._
import models.Tables.profile.simple._
import play.api.db.slick._
import play.api.libs.json._
import play.api.mvc._
import akka.util.Timeout
import scala.concurrent.{ Await, Future }
import scala.concurrent.duration._
/**
 * Created by ROS on 2015/03/17.
 */
object APIController extends Controller {

  implicit val timeout = Timeout(5 seconds)

  /* 内戦募集 */
  case class Host(player: String, gameType: Int, gameName: String)

  /* 解散 */
  case class Break(player: String)

  /* 挙手 */
  case class Join(player: String, gameNo: Int)

  /* 抜け */
  case class Leave(player: String)

  /* 内戦参加メンバー取得 */
  case class List(gameNo: Int = 0)

  /* うまり */
  case class BeFill(player: String)

  /* エイリアス登録 */
  case class Iam(player: String, alias: String)
  case class IamRes(result: Int)

  /* 内戦結果報告 */
  case class Report(player: String, isWin: Int)

  /* 内戦結果報告取り消し */
  case class Cancel(player: String)

  /**
   * 内戦募集
   * @return
   */
  implicit val hostReads = Json.reads[Host]

  def host = DBAction(parse.json[Host]) { implicit request =>
    val param = request.body
    val member: Option[MemberRow] = Member.filter(_.registerName === param.player).firstOption

    member match {
      case None =>
        Forbidden(Json.obj())
      case Some(m) =>
        //val future = KokkoCore.roomActor ? KokkoRoom.Create( "" , m )
        Ok()
    }
  }

  /**
   * エイリアス登録
   * @return
   */
  implicit val iamReads = Json.reads[Iam]

  def iam = DBAction(parse.json[Iam]) { implicit request =>
    val param = request.body
    val member: Option[MemberRow] = Member.filter(_.registerName === param.player).firstOption

    member match {
      case None =>
        Forbidden(Json.obj())
      case Some(m) =>
        /* 別名チェック */
        MemberAlias.filter( ma => (ma.memberId =!= m.memberId) && (ma.aliasName === param.alias)).exists.run match{
          case false=>
            /* エイリアスを登録 */
            val newAlias = MemberAliasRow(0, m.memberId, param.alias)
            MemberAlias.insertOrUpdate(newAlias)
            Ok(Json.obj())
          case _=>
            /* 名前の競合 */
            println("duplicate name : " + param.alias)
            Conflict(Json.obj())
        }
    }
  }

}
