organization := "com.grayt0r"

name := "bacteria"

version := "0.1.0-SNAPSHOT"

scalaVersion := "2.9.1"

libraryDependencies ++= Seq(
  "ch.qos.logback" % "logback-classic" % "1.0.0" % "runtime",
  "org.specs2" %% "specs2" % "1.8.2" % "test"
)

resolvers ++= Seq(
  "snapshots" at "http://oss.sonatype.org/content/repositories/snapshots",
  "releases"  at "http://oss.sonatype.org/content/repositories/releases"
)
