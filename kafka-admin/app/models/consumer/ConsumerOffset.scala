package models.consumer

case class ConsumerOffset (brokerId: String, partition: String, zkOffset: String, brokerOffset : String) {

}