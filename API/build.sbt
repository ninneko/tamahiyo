name := """tamahiyo-API"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.1"

resolvers += "play2-rediscala" at "http://dl.bintray.com/yorrick/maven"

resolvers += "rediscala" at "http://dl.bintray.com/etaty/maven"

libraryDependencies ++= Seq(
//  anorm,
  cache,
  jdbc,
  "com.typesafe.play" %% "play-slick" % "0.8.1",
  "mysql" % "mysql-connector-java" % "5.1.25",
  "fr.njin" %% "play2-rediscala" % "2.3.1.0",
  "org.sisioh" % "scala-dddbase-core_2.10" % "0.1.29"
)


fork in run := true
