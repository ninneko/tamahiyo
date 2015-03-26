package core.action

import play.api.db.slick._

/**
 * 結果報告
 * Created by ROS on 2015/03/27.
 */
case class Result(player: String, isWin: Int) extends RequestProcessor {
  def execute()(implicit session: Session) = {
    None
  }
}