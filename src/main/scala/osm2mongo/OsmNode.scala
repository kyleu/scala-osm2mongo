package osm2mongo

import com.mongodb.casbah.commons.{ MongoDBObject => Obj, MongoDBList => ObjList }
import com.mongodb.{ BasicDBList, BasicDBObject }
import com.mongodb.casbah.Imports._

object OsmNode {
  val empty = new OsmNode(0, new Point(0, 0))

  def apply(obj: Obj): OsmNode = {
    var loc = obj.as[BasicDBList]("loc")
    new OsmNode(obj.as[Int]("osmId"), Point(loc), Tags.load(obj))
  }
}

case class OsmNode(osmId: Int, loc: Point, tags: Map[String, String] = Map()) extends Tags
