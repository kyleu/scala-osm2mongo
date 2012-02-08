package osm2mongo

import com.mongodb.casbah.commons.{ MongoDBObject => Obj, MongoDBList => ObjList }
import com.mongodb.{ BasicDBList, BasicDBObject }
import com.mongodb.casbah.Imports._

object Tags {
  def load(node: Obj) = {
    val nodeTags: ObjList = node.as[BasicDBList]("tags")
    nodeTags map { nodeTag =>
      var nodeTagObj: Obj = nodeTag.asInstanceOf[BasicDBObject]
      (nodeTagObj.as[String]("k"), nodeTagObj.as[String]("v"))
    } toMap
  }
}

trait Tags {
  val tags: Map[String, String]
}