package designpatter

case class JrDeveleoper(val dev: Developer)

case class Developer(val link: Link)

case class Project(val name: String)

case class Link(val project: Project)

object test extends App {
  val proj = Project("CAD")
  val link = Link(proj)
  val dev = Developer(link)

  val jrdev = JrDeveleoper(dev)
  println(dev)
  println(jrdev)
  println(jrdev.dev.link.project.name)
}
