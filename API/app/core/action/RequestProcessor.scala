package core.action

import core.InEvent
import play.api.db.slick._
import play.api.libs.json.{Json, JsValue}

case class KokkoException(code: Int = 0) extends Exception
class MemberNotFoundException extends KokkoException(1)
class DuplicateNameException extends KokkoException(0)

/**
 * Request Processor
 * Created by ROS on 2015/03/27.
 */
trait RequestProcessor {
  def execute()(implicit session: Session): Option[JsValue]
}

object RequestProcessor {
  //request
  implicit val hostReads = Json.reads[Host]
  implicit val breakReads = Json.reads[Break]
  implicit val joinReads = Json.reads[Join]
  implicit val leaveReads = Json.reads[Leave]
  implicit val listReads = Json.reads[Listing]
  implicit val fillReads = Json.reads[Fill]
  implicit val iamReads = Json.reads[Iam]
  implicit val resultReads = Json.reads[Result]
  implicit val cancelReads = Json.reads[Cancel]

  //response
  implicit val exceptionFormats = Json.format[KokkoException];
}

/**
 * Request Dispatcher
 * Created by ROS on 2015/03/27.
 */
object RequestDispatcher {
  def dispatchMethod(in: InEvent): RequestProcessor = {
    in.method match {
      case "host" => in.arguments.as[Host]
      case "break" => in.arguments.as[Break]
      case "join" => in.arguments.as[Join]
      case "leave" => in.arguments.as[Leave]
      case "listing" => in.arguments.as[Listing]
      case "fill" => in.arguments.as[Fill]
      case "iam" => in.arguments.as[Iam]
      case "result" => in.arguments.as[Result]
      case "cancel" => in.arguments.as[Cancel]
    }
  }
}