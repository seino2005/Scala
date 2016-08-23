
object OptionArg {

  val OptPattern = """-(\S+)\s?([^-]\S+)?""".r

  // -option argStringを解析してtupleにつめる
  // (Map[String,String], Seq[String])
  //   -[option] arg1:args2:... の場合にはMap
  //   それ以外の場合はListで戻す
  //   混在時は-optionのみ解析
  def parse( args:String ):(Map[String,String], Seq[String]) = {
    if (args.isEmpty) return (null, null)

    val opts = OptPattern.findAllIn(args).matchData.map{ m =>
      m.group(1) -> (if( m.group(2) == null ) "" else m.group(2)) }.toMap

    return if (opts.isEmpty) (null, args.split("[\\s]+")) else (opts, null)
  }

  // main
  def main(args:Array[String]): Unit = {
    val options = parse(args.mkString(" "))
    val opts = options._1
    val optsStrings = options._2

    println(options)
    if (opts != null)
    {
      opts.foreach { case(k,v) =>
        println(s"${k} -> ${v}")
      }
    }
    else if (optsStrings != null)
    {
      optsStrings.foreach(println)
    }
    else
    {
      println("no arggument")
    }
  }


}
