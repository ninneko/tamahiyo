package core.action

import play.api.db.slick._

/**
 * 結果報告取り消し
 * Created by ROS on 2015/03/27.
 */
case class Cancel(player: String) extends RequestProcessor {
  def execute()(implicit session: Session) = {

    None
  }
}