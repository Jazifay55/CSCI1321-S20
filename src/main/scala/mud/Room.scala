package mud

class Room(val name: String, val desc: String, private var items: List[Item], private val exits: Array[Int]) {


  def description(): String = ???

  def getExit(dir: Int): Option[Room] = ???

  def getItem(itemName: String): Option[Item] = {
    items.find(_.name.toLowerCase == itemName.toLowerCase) match {
      case Some(item) =>
        items = items.filter(_ != item)
        Some(item)
      case None => None
    }
  }

  def dropItem(item: Item): Unit = items ::= item
}

object Room {
  val rooms = readRooms()

  def readRooms(): Array[Room] = {
    val source = scala.io.Source.fromFile("world.txt")
    val lines = source.getLines()
    val r = Array.fill(lines.next.toInt)(readRoom(lines))
    source.close()
    r
  }

  def readRoom(lines: Iterator[String]): Room = {
    val name = lines.next()
    val desc = lines.next()
    val items = List.fill(lines.next.toInt)(Item(lines.next(), lines.next()))
    val exits = lines.next().split(",").map(_.toInt)
    new Room(name, desc, items, exits)
  }
  
}