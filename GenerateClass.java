class GenerateClass {
static public String generate(ObjectValue val) {
String success = "Success";
try {
genHello(val);
} catch (Exception e) {
success = e.toString();
}
return success;
}

static private Hello genHello(ObjectValue val) throws Exception {
int arg0 = ((IntValue) val.get(0)).getVal();
String arg1 = ((StringValue) val.get(1)).getVal();
return new Hello(arg0, arg1);
}

static private Thing genThing(ObjectValue val) throws Exception {
int arg0 = ((IntValue) val.get(0)).getVal();
String arg1 = ((StringValue) val.get(1)).getVal();
double arg2 = ((DoubleValue) val.get(2)).getVal();
return new Thing(arg0, arg1, arg2);
}
}