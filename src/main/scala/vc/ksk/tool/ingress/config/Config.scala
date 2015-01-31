package vc.ksk.tool.ingress.config

trait Config {
  def width: Int
  def height: Int

  def ops: (Int, Int)
  def showItemList: (Int, Int)
  def topElementInItemList: (Int, Int)
  def portalKeysInItemList: (Int, Int)
  def portalKey: (Int, Int)
  def recycle: (Int, Int)
  def confirm: (Int, Int)
  def scrollStart: (Int, Int)
  def scrollEnd: (Int, Int)
}

object Config {
  lazy val deviceConfigs = Map[String, Config](
    "Nexus 6" -> new Nexus6()
  )

  def getDeviceConfig(modelName: String): Option[Config] = deviceConfigs.get(modelName)
}
