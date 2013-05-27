import sbt._
import Keys._
import PlayProject._

object ApplicationBuild extends Build {

    val appName         = "kafka-admin"
    val appVersion      = "1.0-SNAPSHOT"

    val appDependencies = Seq(
    	//"kafka" % "kafka" % "0.7.1",
    	"com.github.sgroschupf"%"zkclient"%"0.1"
    )

    val main = PlayProject(appName, appVersion, appDependencies, mainLang = SCALA).settings(
      // Add your own project settings here 
      resolvers += Resolver.url("conduit-all", url("http://192.168.18.95:8081/artifactory/conduit-all/"))(Resolver.ivyStylePatterns),
      resolvers += Resolver.url("conduit", url("http://192.168.18.95:8081/artifactory/conduit/"))(Resolver.ivyStylePatterns)
      
    )

}
