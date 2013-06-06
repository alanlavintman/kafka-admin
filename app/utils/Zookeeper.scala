package utils

import org.I0Itec.zkclient.ZkClient
import kafka.utils.ZKStringSerializer
import play.api.mvc._
import play.api.Play.current

object Zookeeper {
  
  val config = play.api.Play.configuration
   
  val connString = config.getString("zookeeper.connection.string").get
  val sessionTimeOut = config.getInt("zookeeper.session.timeout").get
  val connectionTimeOut = config.getInt("zookeeper.connection.timeout").get
  
  val _client = new ZkClient(connString, sessionTimeOut, connectionTimeOut, ZKStringSerializer)
  
  def getInstance():ZkClient = _client
  
}