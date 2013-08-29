using System;
using System.Collections.Generic;

public class FastPassCLI {
  public static void Main(string[] args){
    Dictionary<string, string> parms = new Dictionary<string, string>();
    parms.Add("foo", "bar");
    parms.Add("baz", "@@@!#");
    parms.Add("name", "ASDASD");
    var result = FastPass.script("xi2vaxgpp06m", "ly68der0hk8idfr5c73ozyq56jpwstd1", "scott@getsatisfaction.com", "Scott", "nullstyle", true, parms);
    Console.WriteLine(result);
  }
}