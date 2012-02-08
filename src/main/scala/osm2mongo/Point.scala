package osm2mongo

import com.mongodb.BasicDBList

object Point {
  val empty = new Point(0, 0)
  def apply(objList: BasicDBList): Point = {
    new Point(objList.get(0).asInstanceOf[Double], objList.get(1).asInstanceOf[Double])
  }
}

case class Point(x: Double, y: Double) {
  def lng = x
  def lat = y
  override def toString = "[%d, %d]" format (x, y)
}