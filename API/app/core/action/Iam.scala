package core.action

import core.KokkoTypes._
import models.Tables._
import models.Tables.profile.simple._
import play.api.Logger
import play.api.db.slick.Session
import play.api.libs.json.{JsString, JsValue, Json}

/**
 * エイリアス登録
 * Created by ROS on 2015/03/27.
 */

case class Iam(player: String, alias: String) extends RequestProcessor {
  def execute()(implicit session: Session) = {
    //Option[プレイヤー]
    val member: Option[Player] = Member.filter(_.registerName === player).firstOption

    member match {
      case None =>
        Logger.info("iam:Unknown User" + this)
        throw new MemberNotFoundException()
      case Some(m) =>
        /* 別名チェック */
        MemberAlias.filter(ma => (ma.memberId =!= m.memberId) && (ma.aliasName === alias)).exists.run match {
          case false =>
            /* エイリアスを登録 */
            val newAlias = MemberAliasRow(0, m.memberId, alias)
            MemberAlias.insertOrUpdate(newAlias)
            Logger.info("iam:Created alias " + this)
            val ret: JsValue = Json.toJson {
              Map("player" -> JsString(m.registerName), "alias" -> JsString(alias))
            }
            Some(ret)
          case _ =>
            /* 名前の競合 */
            Logger.info("iam:Duplicate name : " + this)
            throw new DuplicateNameException()
        }
    }
  }
}