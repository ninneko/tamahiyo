package models

// AUTO-GENERATED Slick data model
/** Stand-alone Slick data model for immediate use */
object Tables extends {
  val profile = scala.slick.driver.MySQLDriver
} with Tables

/** Slick data model trait for extension, choice of backend or usage in the cake pattern. (Make sure to initialize this late.) */
trait Tables {
  val profile: scala.slick.driver.JdbcProfile

  import profile.simple._
  import scala.slick.model.ForeignKeyAction

  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.

  import scala.slick.jdbc.{GetResult => GR}

  /** DDL for all tables. Call .create to execute. */
  lazy val ddl = GameAsign.ddl ++ GameHistory.ddl ++ GameHistoryChangeLog.ddl ++ Member.ddl ++ MemberAlias.ddl ++ MemberAttribute.ddl ++ MemberAttributeChangeLog.ddl ++ MemberChangeLog.ddl ++ MemberRate.ddl ++ MemberRateChangeLog.ddl ++ MemberSecurity.ddl

  /** Entity class storing rows of table GameAsign
    * @param gameAsign Database column GAME_ASIGN AutoInc, PrimaryKey
    * @param memberId Database column MEMBER_ID
    * @param gameHistoryId Database column GAME_HISTORY_ID
    * @param teamNumber Database column TEAM_NUMBER
    * @param isWin Database column IS_WIN
    * @param changeReate Database column CHANGE_REATE  */
  case class GameAsignRow(gameAsign: Long, memberId: Long, gameHistoryId: Long, teamNumber: Int, isWin: Boolean, changeReate: Int)

  /** GetResult implicit for fetching GameAsignRow objects using plain SQL queries */
  implicit def GetResultGameAsignRow(implicit e0: GR[Long], e1: GR[Int], e2: GR[Boolean]): GR[GameAsignRow] = GR {
    prs => import prs._
      GameAsignRow.tupled((<<[Long], <<[Long], <<[Long], <<[Int], <<[Boolean], <<[Int]))
  }

  /** Table description of table game_asign. Objects of this class serve as prototypes for rows in queries. */
  class GameAsign(tag: Tag) extends Table[GameAsignRow](tag, "game_asign") {
    def * = (gameAsign, memberId, gameHistoryId, teamNumber, isWin, changeReate) <>(GameAsignRow.tupled, GameAsignRow.unapply)

    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (gameAsign.?, memberId.?, gameHistoryId.?, teamNumber.?, isWin.?, changeReate.?).shaped.<>({ r => import r._; _1.map(_ => GameAsignRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get)))}, (_: Any) => throw new Exception("Inserting into ? projection not supported."))

    /** Database column GAME_ASIGN AutoInc, PrimaryKey */
    val gameAsign: Column[Long] = column[Long]("GAME_ASIGN", O.AutoInc, O.PrimaryKey)
    /** Database column MEMBER_ID  */
    val memberId: Column[Long] = column[Long]("MEMBER_ID")
    /** Database column GAME_HISTORY_ID  */
    val gameHistoryId: Column[Long] = column[Long]("GAME_HISTORY_ID")
    /** Database column TEAM_NUMBER  */
    val teamNumber: Column[Int] = column[Int]("TEAM_NUMBER")
    /** Database column IS_WIN  */
    val isWin: Column[Boolean] = column[Boolean]("IS_WIN")
    /** Database column CHANGE_REATE  */
    val changeReate: Column[Int] = column[Int]("CHANGE_REATE")

    /** Foreign key referencing GameHistory (database name game_asign_ibfk_1) */
    lazy val gameHistoryFk = foreignKey("game_asign_ibfk_1", gameHistoryId, GameHistory)(r => r.gameHistoryId, onUpdate = ForeignKeyAction.NoAction, onDelete = ForeignKeyAction.NoAction)
    /** Foreign key referencing Member (database name game_asign_ibfk_2) */
    lazy val memberFk = foreignKey("game_asign_ibfk_2", memberId, Member)(r => r.memberId, onUpdate = ForeignKeyAction.NoAction, onDelete = ForeignKeyAction.NoAction)
  }

  /** Collection-like TableQuery object for table GameAsign */
  lazy val GameAsign = new TableQuery(tag => new GameAsign(tag))

  /** Entity class storing rows of table GameHistory
    * @param gameHistoryId Database column GAME_HISTORY_ID AutoInc, PrimaryKey
    * @param hostMeberId Database column HOST_MEBER_ID
    * @param gameName Database column GAME_NAME
    * @param naisenType Database column NAISEN_TYPE
    * @param gameType Database column GAME_TYPE
    * @param filledDatetime Database column FILLED_DATETIME
    * @param finishDatetime Database column FINISH_DATETIME
    * @param winTeamNumber Database column WIN_TEAM_NUMBER
    * @param endStatus Database column END_STATUS  */
  case class GameHistoryRow(gameHistoryId: Long, hostMeberId: Long, gameName: String, naisenType: String, gameType: String, filledDatetime: java.sql.Timestamp, finishDatetime: java.sql.Timestamp, winTeamNumber: Int, endStatus: String)

  /** GetResult implicit for fetching GameHistoryRow objects using plain SQL queries */
  implicit def GetResultGameHistoryRow(implicit e0: GR[Long], e1: GR[String], e2: GR[java.sql.Timestamp], e3: GR[Int]): GR[GameHistoryRow] = GR {
    prs => import prs._
      GameHistoryRow.tupled((<<[Long], <<[Long], <<[String], <<[String], <<[String], <<[java.sql.Timestamp], <<[java.sql.Timestamp], <<[Int], <<[String]))
  }

  /** Table description of table game_history. Objects of this class serve as prototypes for rows in queries. */
  class GameHistory(tag: Tag) extends Table[GameHistoryRow](tag, "game_history") {
    def * = (gameHistoryId, hostMeberId, gameName, naisenType, gameType, filledDatetime, finishDatetime, winTeamNumber, endStatus) <>(GameHistoryRow.tupled, GameHistoryRow.unapply)

    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (gameHistoryId.?, hostMeberId.?, gameName.?, naisenType.?, gameType.?, filledDatetime.?, finishDatetime.?, winTeamNumber.?, endStatus.?).shaped.<>({ r => import r._; _1.map(_ => GameHistoryRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get, _7.get, _8.get, _9.get)))}, (_: Any) => throw new Exception("Inserting into ? projection not supported."))

    /** Database column GAME_HISTORY_ID AutoInc, PrimaryKey */
    val gameHistoryId: Column[Long] = column[Long]("GAME_HISTORY_ID", O.AutoInc, O.PrimaryKey)
    /** Database column HOST_MEBER_ID  */
    val hostMeberId: Column[Long] = column[Long]("HOST_MEBER_ID")
    /** Database column GAME_NAME  */
    val gameName: Column[String] = column[String]("GAME_NAME")
    /** Database column NAISEN_TYPE  */
    val naisenType: Column[String] = column[String]("NAISEN_TYPE")
    /** Database column GAME_TYPE  */
    val gameType: Column[String] = column[String]("GAME_TYPE")
    /** Database column FILLED_DATETIME  */
    val filledDatetime: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("FILLED_DATETIME")
    /** Database column FINISH_DATETIME  */
    val finishDatetime: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("FINISH_DATETIME")
    /** Database column WIN_TEAM_NUMBER  */
    val winTeamNumber: Column[Int] = column[Int]("WIN_TEAM_NUMBER")
    /** Database column END_STATUS  */
    val endStatus: Column[String] = column[String]("END_STATUS")

    /** Foreign key referencing Member (database name game_history_ibfk_1) */
    lazy val memberFk = foreignKey("game_history_ibfk_1", hostMeberId, Member)(r => r.memberId, onUpdate = ForeignKeyAction.NoAction, onDelete = ForeignKeyAction.NoAction)
  }

  /** Collection-like TableQuery object for table GameHistory */
  lazy val GameHistory = new TableQuery(tag => new GameHistory(tag))

  /** Entity class storing rows of table GameHistoryChangeLog
    * @param gameHistoryChangeLog Database column GAME_HISTORY_CHANGE_LOG AutoInc, PrimaryKey
    * @param gameHistoryId Database column GAME_HISTORY_ID
    * @param hostMemberId Database column HOST_MEMBER_ID
    * @param gameName Database column GAME_NAME
    * @param naisenType Database column NAISEN_TYPE
    * @param gameType Database column GAME_TYPE
    * @param filledDatetime Database column FILLED_DATETIME
    * @param finishDatetime Database column FINISH_DATETIME
    * @param winTeamNumber Database column WIN_TEAM_NUMBER
    * @param endStatus Database column END_STATUS
    * @param changeDate Database column CHANGE_DATE
    * @param changeReason Database column CHANGE_REASON  */
  case class GameHistoryChangeLogRow(gameHistoryChangeLog: Long, gameHistoryId: Long, hostMemberId: Long, gameName: String, naisenType: String, gameType: String, filledDatetime: java.sql.Timestamp, finishDatetime: java.sql.Timestamp, winTeamNumber: Int, endStatus: String, changeDate: java.sql.Timestamp, changeReason: String)

  /** GetResult implicit for fetching GameHistoryChangeLogRow objects using plain SQL queries */
  implicit def GetResultGameHistoryChangeLogRow(implicit e0: GR[Long], e1: GR[String], e2: GR[java.sql.Timestamp], e3: GR[Int]): GR[GameHistoryChangeLogRow] = GR {
    prs => import prs._
      GameHistoryChangeLogRow.tupled((<<[Long], <<[Long], <<[Long], <<[String], <<[String], <<[String], <<[java.sql.Timestamp], <<[java.sql.Timestamp], <<[Int], <<[String], <<[java.sql.Timestamp], <<[String]))
  }

  /** Table description of table game_history_change_log. Objects of this class serve as prototypes for rows in queries. */
  class GameHistoryChangeLog(tag: Tag) extends Table[GameHistoryChangeLogRow](tag, "game_history_change_log") {
    def * = (gameHistoryChangeLog, gameHistoryId, hostMemberId, gameName, naisenType, gameType, filledDatetime, finishDatetime, winTeamNumber, endStatus, changeDate, changeReason) <>(GameHistoryChangeLogRow.tupled, GameHistoryChangeLogRow.unapply)

    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (gameHistoryChangeLog.?, gameHistoryId.?, hostMemberId.?, gameName.?, naisenType.?, gameType.?, filledDatetime.?, finishDatetime.?, winTeamNumber.?, endStatus.?, changeDate.?, changeReason.?).shaped.<>({ r => import r._; _1.map(_ => GameHistoryChangeLogRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get, _7.get, _8.get, _9.get, _10.get, _11.get, _12.get)))}, (_: Any) => throw new Exception("Inserting into ? projection not supported."))

    /** Database column GAME_HISTORY_CHANGE_LOG AutoInc, PrimaryKey */
    val gameHistoryChangeLog: Column[Long] = column[Long]("GAME_HISTORY_CHANGE_LOG", O.AutoInc, O.PrimaryKey)
    /** Database column GAME_HISTORY_ID  */
    val gameHistoryId: Column[Long] = column[Long]("GAME_HISTORY_ID")
    /** Database column HOST_MEMBER_ID  */
    val hostMemberId: Column[Long] = column[Long]("HOST_MEMBER_ID")
    /** Database column GAME_NAME  */
    val gameName: Column[String] = column[String]("GAME_NAME")
    /** Database column NAISEN_TYPE  */
    val naisenType: Column[String] = column[String]("NAISEN_TYPE")
    /** Database column GAME_TYPE  */
    val gameType: Column[String] = column[String]("GAME_TYPE")
    /** Database column FILLED_DATETIME  */
    val filledDatetime: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("FILLED_DATETIME")
    /** Database column FINISH_DATETIME  */
    val finishDatetime: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("FINISH_DATETIME")
    /** Database column WIN_TEAM_NUMBER  */
    val winTeamNumber: Column[Int] = column[Int]("WIN_TEAM_NUMBER")
    /** Database column END_STATUS  */
    val endStatus: Column[String] = column[String]("END_STATUS")
    /** Database column CHANGE_DATE  */
    val changeDate: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("CHANGE_DATE")
    /** Database column CHANGE_REASON  */
    val changeReason: Column[String] = column[String]("CHANGE_REASON")

    /** Foreign key referencing GameHistory (database name game_history_change_log_ibfk_1) */
    lazy val gameHistoryFk = foreignKey("game_history_change_log_ibfk_1", gameHistoryId, GameHistory)(r => r.gameHistoryId, onUpdate = ForeignKeyAction.NoAction, onDelete = ForeignKeyAction.NoAction)
  }

  /** Collection-like TableQuery object for table GameHistoryChangeLog */
  lazy val GameHistoryChangeLog = new TableQuery(tag => new GameHistoryChangeLog(tag))

  /** Entity class storing rows of table Member
    * @param memberId Database column MEMBER_ID AutoInc, PrimaryKey
    * @param registerName Database column REGISTER_NAME
    * @param memberStatus Database column MEMBER_STATUS  */
  case class MemberRow(memberId: Long, registerName: String, memberStatus: String)

  /** GetResult implicit for fetching MemberRow objects using plain SQL queries */
  implicit def GetResultMemberRow(implicit e0: GR[Long], e1: GR[String]): GR[MemberRow] = GR {
    prs => import prs._
      MemberRow.tupled((<<[Long], <<[String], <<[String]))
  }

  /** Table description of table member. Objects of this class serve as prototypes for rows in queries. */
  class Member(tag: Tag) extends Table[MemberRow](tag, "member") {
    def * = (memberId, registerName, memberStatus) <>(MemberRow.tupled, MemberRow.unapply)

    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (memberId.?, registerName.?, memberStatus.?).shaped.<>({ r => import r._; _1.map(_ => MemberRow.tupled((_1.get, _2.get, _3.get)))}, (_: Any) => throw new Exception("Inserting into ? projection not supported."))

    /** Database column MEMBER_ID AutoInc, PrimaryKey */
    val memberId: Column[Long] = column[Long]("MEMBER_ID", O.AutoInc, O.PrimaryKey)
    /** Database column REGISTER_NAME  */
    val registerName: Column[String] = column[String]("REGISTER_NAME")
    /** Database column MEMBER_STATUS  */
    val memberStatus: Column[String] = column[String]("MEMBER_STATUS")

    /** Uniqueness Index over (registerName) (database name REGISTER_NAME) */
    val index1 = index("REGISTER_NAME", registerName, unique = true)
  }

  /** Collection-like TableQuery object for table Member */
  lazy val Member = new TableQuery(tag => new Member(tag))

  /** Entity class storing rows of table MemberAlias
    * @param memberAliasId Database column MEMBER_ALIAS_ID AutoInc, PrimaryKey
    * @param memberId Database column MEMBER_ID
    * @param aliasName Database column ALIAS_NAME  */
  case class MemberAliasRow(memberAliasId: Long, memberId: Long, aliasName: String)

  /** GetResult implicit for fetching MemberAliasRow objects using plain SQL queries */
  implicit def GetResultMemberAliasRow(implicit e0: GR[Long], e1: GR[String]): GR[MemberAliasRow] = GR {
    prs => import prs._
      MemberAliasRow.tupled((<<[Long], <<[Long], <<[String]))
  }

  /** Table description of table member_alias. Objects of this class serve as prototypes for rows in queries. */
  class MemberAlias(tag: Tag) extends Table[MemberAliasRow](tag, "member_alias") {
    def * = (memberAliasId, memberId, aliasName) <>(MemberAliasRow.tupled, MemberAliasRow.unapply)

    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (memberAliasId.?, memberId.?, aliasName.?).shaped.<>({ r => import r._; _1.map(_ => MemberAliasRow.tupled((_1.get, _2.get, _3.get)))}, (_: Any) => throw new Exception("Inserting into ? projection not supported."))

    /** Database column MEMBER_ALIAS_ID AutoInc, PrimaryKey */
    val memberAliasId: Column[Long] = column[Long]("MEMBER_ALIAS_ID", O.AutoInc, O.PrimaryKey)
    /** Database column MEMBER_ID  */
    val memberId: Column[Long] = column[Long]("MEMBER_ID")
    /** Database column ALIAS_NAME  */
    val aliasName: Column[String] = column[String]("ALIAS_NAME")

    /** Foreign key referencing Member (database name member_alias_ibfk_1) */
    lazy val memberFk = foreignKey("member_alias_ibfk_1", memberId, Member)(r => r.memberId, onUpdate = ForeignKeyAction.NoAction, onDelete = ForeignKeyAction.NoAction)

    /** Uniqueness Index over (aliasName) (database name ALIAS_NAME) */
    val index1 = index("ALIAS_NAME", aliasName, unique = true)
    /** Uniqueness Index over (memberId) (database name MEMBER_ID) */
    val index2 = index("MEMBER_ID", memberId, unique = true)
  }

  /** Collection-like TableQuery object for table MemberAlias */
  lazy val MemberAlias = new TableQuery(tag => new MemberAlias(tag))

  /** Entity class storing rows of table MemberAttribute
    * @param memberAttributeId Database column MEMBER_ATTRIBUTE_ID AutoInc, PrimaryKey
    * @param memberId Database column MEMBER_ID  */
  case class MemberAttributeRow(memberAttributeId: Long, memberId: Long)

  /** GetResult implicit for fetching MemberAttributeRow objects using plain SQL queries */
  implicit def GetResultMemberAttributeRow(implicit e0: GR[Long]): GR[MemberAttributeRow] = GR {
    prs => import prs._
      MemberAttributeRow.tupled((<<[Long], <<[Long]))
  }

  /** Table description of table member_attribute. Objects of this class serve as prototypes for rows in queries. */
  class MemberAttribute(tag: Tag) extends Table[MemberAttributeRow](tag, "member_attribute") {
    def * = (memberAttributeId, memberId) <>(MemberAttributeRow.tupled, MemberAttributeRow.unapply)

    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (memberAttributeId.?, memberId.?).shaped.<>({ r => import r._; _1.map(_ => MemberAttributeRow.tupled((_1.get, _2.get)))}, (_: Any) => throw new Exception("Inserting into ? projection not supported."))

    /** Database column MEMBER_ATTRIBUTE_ID AutoInc, PrimaryKey */
    val memberAttributeId: Column[Long] = column[Long]("MEMBER_ATTRIBUTE_ID", O.AutoInc, O.PrimaryKey)
    /** Database column MEMBER_ID  */
    val memberId: Column[Long] = column[Long]("MEMBER_ID")

    /** Foreign key referencing Member (database name member_attribute_ibfk_1) */
    lazy val memberFk = foreignKey("member_attribute_ibfk_1", memberId, Member)(r => r.memberId, onUpdate = ForeignKeyAction.NoAction, onDelete = ForeignKeyAction.NoAction)

    /** Uniqueness Index over (memberId) (database name MEMBER_ID) */
    val index1 = index("MEMBER_ID", memberId, unique = true)
  }

  /** Collection-like TableQuery object for table MemberAttribute */
  lazy val MemberAttribute = new TableQuery(tag => new MemberAttribute(tag))

  /** Entity class storing rows of table MemberAttributeChangeLog
    * @param memberAttributeChangeLogId Database column MEMBER_ATTRIBUTE_CHANGE_LOG_ID AutoInc, PrimaryKey
    * @param memberAttributeId Database column MEMBER_ATTRIBUTE_ID
    * @param changeDate Database column CHANGE_DATE
    * @param changeReason Database column CHANGE_REASON  */
  case class MemberAttributeChangeLogRow(memberAttributeChangeLogId: Long, memberAttributeId: Long, changeDate: java.sql.Timestamp, changeReason: String)

  /** GetResult implicit for fetching MemberAttributeChangeLogRow objects using plain SQL queries */
  implicit def GetResultMemberAttributeChangeLogRow(implicit e0: GR[Long], e1: GR[java.sql.Timestamp], e2: GR[String]): GR[MemberAttributeChangeLogRow] = GR {
    prs => import prs._
      MemberAttributeChangeLogRow.tupled((<<[Long], <<[Long], <<[java.sql.Timestamp], <<[String]))
  }

  /** Table description of table member_attribute_change_log. Objects of this class serve as prototypes for rows in queries. */
  class MemberAttributeChangeLog(tag: Tag) extends Table[MemberAttributeChangeLogRow](tag, "member_attribute_change_log") {
    def * = (memberAttributeChangeLogId, memberAttributeId, changeDate, changeReason) <>(MemberAttributeChangeLogRow.tupled, MemberAttributeChangeLogRow.unapply)

    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (memberAttributeChangeLogId.?, memberAttributeId.?, changeDate.?, changeReason.?).shaped.<>({ r => import r._; _1.map(_ => MemberAttributeChangeLogRow.tupled((_1.get, _2.get, _3.get, _4.get)))}, (_: Any) => throw new Exception("Inserting into ? projection not supported."))

    /** Database column MEMBER_ATTRIBUTE_CHANGE_LOG_ID AutoInc, PrimaryKey */
    val memberAttributeChangeLogId: Column[Long] = column[Long]("MEMBER_ATTRIBUTE_CHANGE_LOG_ID", O.AutoInc, O.PrimaryKey)
    /** Database column MEMBER_ATTRIBUTE_ID  */
    val memberAttributeId: Column[Long] = column[Long]("MEMBER_ATTRIBUTE_ID")
    /** Database column CHANGE_DATE  */
    val changeDate: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("CHANGE_DATE")
    /** Database column CHANGE_REASON  */
    val changeReason: Column[String] = column[String]("CHANGE_REASON")

    /** Foreign key referencing MemberAttribute (database name member_attribute_change_log_ibfk_1) */
    lazy val memberAttributeFk = foreignKey("member_attribute_change_log_ibfk_1", memberAttributeId, MemberAttribute)(r => r.memberAttributeId, onUpdate = ForeignKeyAction.NoAction, onDelete = ForeignKeyAction.NoAction)
  }

  /** Collection-like TableQuery object for table MemberAttributeChangeLog */
  lazy val MemberAttributeChangeLog = new TableQuery(tag => new MemberAttributeChangeLog(tag))

  /** Entity class storing rows of table MemberChangeLog
    * @param memberChangeLog Database column MEMBER_CHANGE_LOG AutoInc, PrimaryKey
    * @param memberId Database column MEMBER_ID
    * @param registerName Database column REGISTER_NAME
    * @param memberStatus Database column MEMBER_STATUS
    * @param changeDate Database column CHANGE_DATE
    * @param changeReason Database column CHANGE_REASON  */
  case class MemberChangeLogRow(memberChangeLog: Long, memberId: Long, registerName: String, memberStatus: String, changeDate: java.sql.Timestamp, changeReason: String)

  /** GetResult implicit for fetching MemberChangeLogRow objects using plain SQL queries */
  implicit def GetResultMemberChangeLogRow(implicit e0: GR[Long], e1: GR[String], e2: GR[java.sql.Timestamp]): GR[MemberChangeLogRow] = GR {
    prs => import prs._
      MemberChangeLogRow.tupled((<<[Long], <<[Long], <<[String], <<[String], <<[java.sql.Timestamp], <<[String]))
  }

  /** Table description of table member_change_log. Objects of this class serve as prototypes for rows in queries. */
  class MemberChangeLog(tag: Tag) extends Table[MemberChangeLogRow](tag, "member_change_log") {
    def * = (memberChangeLog, memberId, registerName, memberStatus, changeDate, changeReason) <>(MemberChangeLogRow.tupled, MemberChangeLogRow.unapply)

    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (memberChangeLog.?, memberId.?, registerName.?, memberStatus.?, changeDate.?, changeReason.?).shaped.<>({ r => import r._; _1.map(_ => MemberChangeLogRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get)))}, (_: Any) => throw new Exception("Inserting into ? projection not supported."))

    /** Database column MEMBER_CHANGE_LOG AutoInc, PrimaryKey */
    val memberChangeLog: Column[Long] = column[Long]("MEMBER_CHANGE_LOG", O.AutoInc, O.PrimaryKey)
    /** Database column MEMBER_ID  */
    val memberId: Column[Long] = column[Long]("MEMBER_ID")
    /** Database column REGISTER_NAME  */
    val registerName: Column[String] = column[String]("REGISTER_NAME")
    /** Database column MEMBER_STATUS  */
    val memberStatus: Column[String] = column[String]("MEMBER_STATUS")
    /** Database column CHANGE_DATE  */
    val changeDate: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("CHANGE_DATE")
    /** Database column CHANGE_REASON  */
    val changeReason: Column[String] = column[String]("CHANGE_REASON")

    /** Foreign key referencing Member (database name member_change_log_ibfk_1) */
    lazy val memberFk = foreignKey("member_change_log_ibfk_1", memberId, Member)(r => r.memberId, onUpdate = ForeignKeyAction.NoAction, onDelete = ForeignKeyAction.NoAction)
  }

  /** Collection-like TableQuery object for table MemberChangeLog */
  lazy val MemberChangeLog = new TableQuery(tag => new MemberChangeLog(tag))

  /** Entity class storing rows of table MemberRate
    * @param memberRateId Database column MEMBER_RATE_ID AutoInc, PrimaryKey
    * @param memberId Database column MEMBER_ID
    * @param gameType Database column GAME_TYPE
    * @param rate Database column RATE  */
  case class MemberRateRow(memberRateId: Long, memberId: Long, gameType: String, rate: Long)

  /** GetResult implicit for fetching MemberRateRow objects using plain SQL queries */
  implicit def GetResultMemberRateRow(implicit e0: GR[Long], e1: GR[String]): GR[MemberRateRow] = GR {
    prs => import prs._
      MemberRateRow.tupled((<<[Long], <<[Long], <<[String], <<[Long]))
  }

  /** Table description of table member_rate. Objects of this class serve as prototypes for rows in queries. */
  class MemberRate(tag: Tag) extends Table[MemberRateRow](tag, "member_rate") {
    def * = (memberRateId, memberId, gameType, rate) <>(MemberRateRow.tupled, MemberRateRow.unapply)

    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (memberRateId.?, memberId.?, gameType.?, rate.?).shaped.<>({ r => import r._; _1.map(_ => MemberRateRow.tupled((_1.get, _2.get, _3.get, _4.get)))}, (_: Any) => throw new Exception("Inserting into ? projection not supported."))

    /** Database column MEMBER_RATE_ID AutoInc, PrimaryKey */
    val memberRateId: Column[Long] = column[Long]("MEMBER_RATE_ID", O.AutoInc, O.PrimaryKey)
    /** Database column MEMBER_ID  */
    val memberId: Column[Long] = column[Long]("MEMBER_ID")
    /** Database column GAME_TYPE  */
    val gameType: Column[String] = column[String]("GAME_TYPE")
    /** Database column RATE  */
    val rate: Column[Long] = column[Long]("RATE")

    /** Foreign key referencing Member (database name member_rate_ibfk_1) */
    lazy val memberFk = foreignKey("member_rate_ibfk_1", memberId, Member)(r => r.memberId, onUpdate = ForeignKeyAction.NoAction, onDelete = ForeignKeyAction.NoAction)

    /** Uniqueness Index over (memberId) (database name MEMBER_ID) */
    val index1 = index("MEMBER_ID", memberId, unique = true)
  }

  /** Collection-like TableQuery object for table MemberRate */
  lazy val MemberRate = new TableQuery(tag => new MemberRate(tag))

  /** Entity class storing rows of table MemberRateChangeLog
    * @param memberRateChangeLogId Database column MEMBER_RATE_CHANGE_LOG_ID AutoInc, PrimaryKey
    * @param memberRateId Database column MEMBER_RATE_ID
    * @param gameType Database column GAME_TYPE
    * @param rate Database column RATE
    * @param changeDate Database column CHANGE_DATE
    * @param changeReason Database column CHANGE_REASON  */
  case class MemberRateChangeLogRow(memberRateChangeLogId: Long, memberRateId: Long, gameType: String, rate: Long, changeDate: java.sql.Timestamp, changeReason: String)

  /** GetResult implicit for fetching MemberRateChangeLogRow objects using plain SQL queries */
  implicit def GetResultMemberRateChangeLogRow(implicit e0: GR[Long], e1: GR[String], e2: GR[java.sql.Timestamp]): GR[MemberRateChangeLogRow] = GR {
    prs => import prs._
      MemberRateChangeLogRow.tupled((<<[Long], <<[Long], <<[String], <<[Long], <<[java.sql.Timestamp], <<[String]))
  }

  /** Table description of table member_rate_change_log. Objects of this class serve as prototypes for rows in queries. */
  class MemberRateChangeLog(tag: Tag) extends Table[MemberRateChangeLogRow](tag, "member_rate_change_log") {
    def * = (memberRateChangeLogId, memberRateId, gameType, rate, changeDate, changeReason) <>(MemberRateChangeLogRow.tupled, MemberRateChangeLogRow.unapply)

    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (memberRateChangeLogId.?, memberRateId.?, gameType.?, rate.?, changeDate.?, changeReason.?).shaped.<>({ r => import r._; _1.map(_ => MemberRateChangeLogRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get)))}, (_: Any) => throw new Exception("Inserting into ? projection not supported."))

    /** Database column MEMBER_RATE_CHANGE_LOG_ID AutoInc, PrimaryKey */
    val memberRateChangeLogId: Column[Long] = column[Long]("MEMBER_RATE_CHANGE_LOG_ID", O.AutoInc, O.PrimaryKey)
    /** Database column MEMBER_RATE_ID  */
    val memberRateId: Column[Long] = column[Long]("MEMBER_RATE_ID")
    /** Database column GAME_TYPE  */
    val gameType: Column[String] = column[String]("GAME_TYPE")
    /** Database column RATE  */
    val rate: Column[Long] = column[Long]("RATE")
    /** Database column CHANGE_DATE  */
    val changeDate: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("CHANGE_DATE")
    /** Database column CHANGE_REASON  */
    val changeReason: Column[String] = column[String]("CHANGE_REASON")

    /** Foreign key referencing MemberRate (database name member_rate_change_log_ibfk_1) */
    lazy val memberRateFk = foreignKey("member_rate_change_log_ibfk_1", memberRateId, MemberRate)(r => r.memberRateId, onUpdate = ForeignKeyAction.NoAction, onDelete = ForeignKeyAction.NoAction)
  }

  /** Collection-like TableQuery object for table MemberRateChangeLog */
  lazy val MemberRateChangeLog = new TableQuery(tag => new MemberRateChangeLog(tag))

  /** Entity class storing rows of table MemberSecurity
    * @param memberSecurityId Database column MEMBER_SECURITY_ID AutoInc, PrimaryKey
    * @param memberId Database column MEMBER_ID
    * @param password Database column PASSWORD  */
  case class MemberSecurityRow(memberSecurityId: Long, memberId: Long, password: String)

  /** GetResult implicit for fetching MemberSecurityRow objects using plain SQL queries */
  implicit def GetResultMemberSecurityRow(implicit e0: GR[Long], e1: GR[String]): GR[MemberSecurityRow] = GR {
    prs => import prs._
      MemberSecurityRow.tupled((<<[Long], <<[Long], <<[String]))
  }

  /** Table description of table member_security. Objects of this class serve as prototypes for rows in queries. */
  class MemberSecurity(tag: Tag) extends Table[MemberSecurityRow](tag, "member_security") {
    def * = (memberSecurityId, memberId, password) <>(MemberSecurityRow.tupled, MemberSecurityRow.unapply)

    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (memberSecurityId.?, memberId.?, password.?).shaped.<>({ r => import r._; _1.map(_ => MemberSecurityRow.tupled((_1.get, _2.get, _3.get)))}, (_: Any) => throw new Exception("Inserting into ? projection not supported."))

    /** Database column MEMBER_SECURITY_ID AutoInc, PrimaryKey */
    val memberSecurityId: Column[Long] = column[Long]("MEMBER_SECURITY_ID", O.AutoInc, O.PrimaryKey)
    /** Database column MEMBER_ID  */
    val memberId: Column[Long] = column[Long]("MEMBER_ID")
    /** Database column PASSWORD  */
    val password: Column[String] = column[String]("PASSWORD")

    /** Foreign key referencing Member (database name member_security_ibfk_1) */
    lazy val memberFk = foreignKey("member_security_ibfk_1", memberId, Member)(r => r.memberId, onUpdate = ForeignKeyAction.NoAction, onDelete = ForeignKeyAction.NoAction)

    /** Uniqueness Index over (memberId) (database name MEMBER_ID) */
    val index1 = index("MEMBER_ID", memberId, unique = true)
  }

  /** Collection-like TableQuery object for table MemberSecurity */
  lazy val MemberSecurity = new TableQuery(tag => new MemberSecurity(tag))
}