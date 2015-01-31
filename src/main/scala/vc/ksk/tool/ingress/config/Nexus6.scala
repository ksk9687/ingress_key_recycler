package vc.ksk.tool.ingress.config


class Nexus6 extends Config {
  override def width: Int = 1440
  override def height: Int = 2560

  override def ops: (Int, Int) = (1300, 230)

  override def scrollStart: (Int, Int) = (44, 1960)

  override def scrollEnd: (Int, Int) = (1396, 1960)

  override def recycle: (Int, Int) = (1260, 2300)

  override def confirm: (Int, Int) = (1030, 2300)

  override def topElementInItemList: (Int, Int) = (400, 1420)

  override def portalKeysInItemList: (Int, Int) = (400, 1730)

  override def portalKey: (Int, Int) = (600, 1300)

  override def showItemList: (Int, Int) = (400, 2170)
}
