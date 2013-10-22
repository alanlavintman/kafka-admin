import sbt._
import Keys._
import PlayProject._
import com.github.play2war.plugin._

object ApplicationBuild extends Build {

    val appName         = "kafka-admin"
    val appVersion      = "1.0"

    val appDependencies = Seq(
    	//"kafka" % "kafka" % "0.7.1",
    	"com.github.sgroschupf"%"zkclient"%"0.1",
    	"com.codahale" % "jerkson_2.9.1" % "0.5.0",
        "com.typesafe.akka" % "akka-actor" % "2.0.2" ,
        "com.typesafe.akka" % "akka-remote" % "2.0.2" ,
        "com.typesafe.akka" % "akka-kernel" % "2.0.2"
    )

    val main = PlayProject(appName, appVersion, appDependencies, mainLang = SCALA).settings(
      resolvers += Resolver.url("repo.codahale.com", url("http://repo.codahale.com"))(Resolver.ivyStylePatterns)
    ).settings(
      Play2WarKeys.servletVersion := "3.0"
   ).settings(Play2WarPlugin.play2WarSettings: _*)

}
