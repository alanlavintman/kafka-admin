import sbt._
import Keys._
import play.Project._
import com.github.play2war.plugin._

object ApplicationBuild extends Build {

  val appName         = "kafka-admin-api"
  val appVersion      = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    // Add your project dependencies here,
    jdbc,
    anorm,
    "kafka" % "kafka" % "0.7.1",
    "com.github.sgroschupf"%"zkclient"%"0.1"
  )

  val main = play.Project(appName, appVersion, appDependencies).settings(
    // Add your own project settings here 
      resolvers += Resolver.url("conduit-all", url("http://192.168.18.95:8081/artifactory/conduit-all/"))(Resolver.ivyStylePatterns),
      resolvers += Resolver.url("Oconduit", url("http://192.168.18.95:8081/artifactory/conduit/"))(Resolver.ivyStylePatterns)
      
  )
  
}
