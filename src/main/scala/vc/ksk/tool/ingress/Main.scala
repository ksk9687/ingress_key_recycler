package vc.ksk.tool.ingress

import vc.ksk.tool.ingress.config.Config

object Main {

  def main(args: Array[String]) {
    if (args.length == 0) {
      sys.error("Arg: [num_keys_to_be_recylced]");
    }
    val num: Int = args(0).toInt

    if (!CommandUtils.hasAdb()) {
      sys.error("adb not found.");
    }
    if (!CommandUtils.canLogIn()) {
      sys.error("adb shell cannot be run.");
    }

    val deviceConfig = CommandUtils.getDeviceModel() match {
      case Some(m) => {
        Config.getDeviceConfig(m) match {
          case Some(conf) => conf
          case None => sys.error("Cannot get device config for " + m + ".")
        }
      }
      case None => sys.error("Cannot detect device model.")
    }
    validateDisplayRes(deviceConfig)

    if (!CommandUtils.isIngressFocused()) {
      bringUpIngress()
    }
    CommandUtils.wakeUp()

    recycleKeys(num, deviceConfig)
  }

  def bringUpIngress(): Unit = {
    // TODO: Remove and launch ingress.
    sys.error("Ingress not focused.")

    CommandUtils.throwIntent()
    while (!CommandUtils.isIngressFocused()) {
      Thread.sleep(1000)
    }
  }

  def validateDisplayRes(conf: Config) = {
    CommandUtils.getDisplayResolution() match {
      case Some((x, y)) => {
        if (x != conf.width || y != conf.height) {
          sys.error("Expected: Width: " + conf.width + ", Height:" + conf.height
              + ", but Actual: Width: " + x + ", Height: " + y)
        }
      }
      case _ => sys.error("Cannot get the display resolution.")
    }
  }

  def sleep(x: Int = 1): Unit = {
    Thread.sleep(100 * x)
  }

  def recycleKeys(n: Int, conf: Config): Unit = {
    sleep()
    CommandUtils.touch(conf.ops)
    sleep()
    CommandUtils.touch(conf.showItemList)
    sleep()
    CommandUtils.touch(conf.topElementInItemList)
    sleep()
    CommandUtils.touch(conf.showItemList)
    sleep()
    CommandUtils.touch(conf.portalKeysInItemList)
    sleep()
    CommandUtils.swipe(conf.scrollStart, conf.scrollEnd)

    for (i <- 0 until n) {
      CommandUtils.touch(conf.portalKey)
      sleep()
      CommandUtils.touch(conf.recycle)
      sleep()
      CommandUtils.touch(conf.confirm)
      sleep(20)
    }
  }
}
