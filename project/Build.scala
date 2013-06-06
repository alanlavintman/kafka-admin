import sbt._
import Keys._
import PlayProject._
import com.github.play2war.plugin._

object ApplicationBuild extends Build {

    val appName         = "kafka-admin"
    val appVersion      = "1.0-SNAPSHOT"

    val appDependencies = Seq(
    	//"kafka" % "kafka" % "0.7.1",
    	"com.github.sgroschupf"%"zkclient"%"0.1",
    	"com.codahale" % "jerkson_2.9.1" % "0.5.0"
    )

    val main = PlayProject(appName, appVersion, appDependencies, mainLang = SCALA).settings(
      resolvers += Resolver.url("conduit-all", url("http://192.168.18.95:8081/artifactory/conduit-all/"))(Resolver.ivyStylePatterns),
      resolvers += Resolver.url("conduit", url("http://192.168.18.95:8081/artifactory/conduit/"))(Resolver.ivyStylePatterns),
      resolvers += Resolver.url("repo.codahale.com", url("http://repo.codahale.com"))(Resolver.ivyStylePatterns)
    ).settings(
      Play2WarKeys.servletVersion := "3.0"
   ).settings(Play2WarPlugin.play2WarSettings: _*)

}
