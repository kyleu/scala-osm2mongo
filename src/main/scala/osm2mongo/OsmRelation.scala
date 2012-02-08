package osm2mongo

import com.mongodb.casbah.commons.{ MongoDBObject => Obj, MongoDBList => ObjList }
import com.mongodb.{ BasicDBList, BasicDBObject }
import com.mongodb.casbah.Imports._

object OsmRelation {
  val empty = new OsmWay(0, Seq[Int](), Map[String, String]())

  def apply() = empty

  def apply(obj: Obj) = {
    val memberObjs: ObjList = obj.as[BasicDBList]("members")
    val members = memberObjs map { o =>
      val obj: Obj = o.asInstanceOf[BasicDBObject]
      new OsmRelationMember(obj.as[String]("type"), obj.as[Int]("ref"), obj.as[String]("role"))
    }
    new OsmRelation(obj.as[Int]("osmid"), members toSeq, Tags.load(obj))
  }
}

case class OsmRelationMember(relationType: String, ref: Int, role: String)

case class OsmRelation(osmId: Int, members: Seq[OsmRelationMember], tags: Map[String, String] = Map.empty) extends Tags {

}
