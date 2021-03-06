package scala.pickling.test.binary

import org.scalatest.FunSuite

import java.io.ByteArrayInputStream

import scala.pickling._
import binary._

case class Employee(name: String, age: Int)

class BinaryInputStreamReaderOutputTest extends FunSuite {
  test("unpickle two objects from stream") {
    val obj1 = Employee("James", 30)
    val obj2 = Employee("Jim", 40)

    val output = new ByteArrayBufferOutput
    obj1.pickleTo(output)
    obj2.pickleTo(output)

    val streamPickle = BinaryPickleStream(new ByteArrayInputStream(output.result))
    val readObj1     = streamPickle.unpickle[Employee]
    val readObj2     = streamPickle.unpickle[Employee]

    assert(obj1.toString == readObj1.toString)
    assert(obj2.toString == readObj2.toString)
  }
}
