package core.action

import play.api.db.slick._

/**
 * 埋まり.
 * Created by ROS on 2015/03/27.
 */
case class Fill(player: String) extends RequestProcessor {
  def execute()(implicit session: Session) = {

    None
  }
}