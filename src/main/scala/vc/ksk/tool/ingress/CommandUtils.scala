package vc.ksk.tool.ingress

import java.io.File

import scala.language.postfixOps
import scala.sys.process._

object CommandUtils {
  private def silent(command:ProcessBuilder): ProcessBuilder = command #> new File("//dev/null")

  def hasAdb(): Boolean = silent("which adb").! == 0

  def canLogIn(): Boolean = "adb shell exit".! == 0

  def getDeviceModel(): Option[String] = {
    val ret = "adb shell cat /system/build.prop" #| "grep product.model" !!
    val r = """.+model=(.+)\s+""".r
    ret match {
      case r(m) => Some(m)
      case _ => None
    }
  }

  def getDisplayResolution(): Option[(Int, Int)] = {
    val ret = "adb shell wm size" !!
    val r = """Physical size: (\d+)x(\d+)\s+""".r
    ret match {
      case r(x, y) => Some((x.toInt, y.toInt))
      case _ => None
    }
  }

  def keyEvent(keyCode: Int): Unit = {
    silent("adb shell input keyevent " + keyCode) !!
  }

  def pushPowerButton(): Unit = keyEvent(Constants.KEYCODE_POWER)

  def pushHomeButton(): Unit = keyEvent(Constants.KEYCODE_HOME)

  def wakeUp(): Unit = keyEvent(Constants.KEYCODE_WAKEUP)

  def isIngressFocused(): Boolean = {
    silent("adb shell dumpsys window windows" #| "grep mCurrentFocus" #| "grep ingress").! == 0
  }
  def isIngressRunning(): Boolean = {
    silent("adb shell ps" #| "grep ingress").! == 0
  }

  def throwIntent(): Unit = {
    ("adb shell am start -n com.nianticproject.ingress/.NemesisActivity") !
  }

  def touch(p: (Int, Int)): Unit = {
    ("adb shell input touchscreen tap " + p._1 + " " + p._2) !
  }

  def swipe(from: (Int, Int), to: (Int, Int)): Unit = {
    ("adb shell input touchscreen swipe " + from._1 + " "
        + from._2 + " " + from._2 + " " + from._2) !
  }
}
